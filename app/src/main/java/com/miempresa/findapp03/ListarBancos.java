package com.miempresa.findapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarBancos extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("bancos");
    RecyclerView reciclerBancos;
    AdaptadorBancos adaptadorBancos;
    List<Banco> listaBancos;
    ArrayList<Banco> bancos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_bancos);
        reciclerBancos = findViewById(R.id.reciclerbancos);
        listaBancos = new ArrayList<>();
        myRef.addChildEventListener(childEventListener);
        adaptadorBancos = new AdaptadorBancos(listaBancos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        reciclerBancos.setLayoutManager(mLayoutManager);
        reciclerBancos.setItemAnimator(new DefaultItemAnimator());
        reciclerBancos.setAdapter(adaptadorBancos);
        refrescarLista();

        reciclerBancos.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), reciclerBancos, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(ListarBancos.this)
                        .setPositiveButton("Si",  (dialog ,which)->{
                            Banco b = listaBancos.get(position);
                            Intent i = new Intent(ListarBancos.this, EditarBanco.class);
                            i.putExtra("id", b.getId());
                            i.putExtra("nombre", b.getNombre());
                            i.putExtra("surcursal", b.getSurcursal());
                            i.putExtra("tipo", b.getTipo());
                            i.putExtra("direccion", b.getDireccion());
                            i.putExtra("horario", b.getHorario());
                            startActivity(i);

                        } )
                        .setNegativeButton("No",  (dialog ,which)->{
                            dialog.dismiss();
                        })
                        .setTitle("Prueba click")
                        .setMessage("prueba click corto")
                        .create();
                dialogo.show();
            }

            @Override
            public void onLongClick(View view, int position) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(ListarBancos.this)
                        .setPositiveButton("Si",  (dialog ,which)->{
                            //Agregar metodo borrar al controlador
                            Banco b = listaBancos.get(position);
                            String nombre = b.getNombre();
                            //Log.d("prueba", ""+ m.getId());
                            delete(position);
                            borrarBanco(b);
                            refrescarLista();

                            Toast.makeText(getApplicationContext(), "El banco se elimino con exito", Toast.LENGTH_LONG).show();


                        } )
                        .setNegativeButton("No",  (dialog ,which)->{
                            dialog.dismiss();
                        })
                        .setTitle("Confirmacion para eliminar")
                        .setMessage("Desea eliminar la mascota")
                        .create();
                dialogo.show();

            }
        }));

    }


    private void delete(int position) {
        // creating a variable for our Database
        // Reference for Firebase.
        //DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("DataValue");
        // we are use add listerner
        // for event listener method
        // which is called with query.
        final String nombre=listaBancos.get(position).getNombre();
        Query applesQuery = myRef.child(nombre);



        //sQuery query = myRef.child(name);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // remove the value at reference
                dataSnapshot.getRef().removeValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refrescarLista();
    }

    public void borrarBanco(Banco b){
        bancos.remove(b);
    }

    public void agregarMascota(Banco b){
        bancos.add(b);
        refrescarLista();
    }

    public void refrescarLista(){
        if(adaptadorBancos == null) return;
        listaBancos = bancos;
        adaptadorBancos.setListaBancos(listaBancos);
        adaptadorBancos.notifyDataSetChanged();


    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Banco b = snapshot.getValue(Banco.class);
            if(b!= null) agregarMascota(b);

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Banco b = snapshot.getValue(Banco.class);
            if(b!= null) agregarMascota(b);

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            String bancoid = snapshot.getKey();
            bancos.remove(snapshot);
            refrescarLista();

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            Banco b = snapshot.getValue(Banco.class);
            if(b!= null) agregarMascota(b);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



}