package com.miempresa.findapp03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorBancos extends RecyclerView.Adapter<AdaptadorBancos.CardBanco>{

    private List<Banco> listaBancos;

    public AdaptadorBancos(List<Banco> listaBancos){
        this.listaBancos = listaBancos;
    }

    public void setListaBancos(List<Banco> listaBancos){
        this.listaBancos=listaBancos;
    }

    @NonNull
    @Override
    public CardBanco onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.banco,parent,false);
        return new CardBanco(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CardBanco holder, int position) {

        Banco b = listaBancos.get(position);
        String nombre = b.getNombre();
        String surcursal = b.getSurcursal();
        String tipo = b.getTipo();
        String direccion = b.getDireccion();
        String horario  = b.getHorario();

        holder.twnombre.setText(nombre);
        holder.twsurcursal.setText(surcursal);
        holder.twtipo.setText(tipo);
        holder.twdireccion.setText(direccion);
        holder.twhorario.setText(horario);

    }

    @Override
    public int getItemCount() {
        return listaBancos.size();
    }

    class CardBanco extends RecyclerView.ViewHolder{
        TextView twnombre, twsurcursal, twtipo, twdireccion, twhorario;

        public CardBanco(@NonNull View itemView) {
            super(itemView);
            this.twnombre = itemView.findViewById(R.id.etnombre);
            this.twsurcursal = itemView.findViewById(R.id.etsurcursal);
            this.twtipo = itemView.findViewById(R.id.ettipo);
            this.twdireccion = itemView.findViewById(R.id.etdireccion);
            this.twhorario = itemView.findViewById(R.id.ethorario);

        }
    }

}
