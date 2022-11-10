package com.miempresa.findapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarBanco extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Banco");

    Button agregar, agregarImagen;
    EditText etNombre, etNombreSucursal, etTipo, etDireccion, etHorario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_banco);

        agregar = findViewById(R.id.btnAgregar);
        etNombre = findViewById(R.id.etnombrein);
        etNombreSucursal = findViewById(R.id.etsurcursalin);
        etTipo = findViewById(R.id.ettipoin);
        etDireccion = findViewById(R.id.etdireccionin);
        etHorario = findViewById(R.id.ethorarioin);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String sucursal = etNombreSucursal.getText().toString();
                String tipo = etTipo.getText().toString();
                String direccion = etDireccion.getText().toString();
                String horario = etHorario.getText().toString();

                Banco b = new Banco();
                b.setNombre(nombre);
                b.setSurcursal(sucursal);
                b.setTipo(tipo);
                b.setDireccion(direccion);
                b.setHorario(horario);

                myRef.push().setValue(b);

                Toast.makeText(getApplicationContext(), "El banco se agrego con exito", Toast.LENGTH_LONG).show();
                etNombre.setText("");
                etNombreSucursal.setText("");
                etTipo.setText("");
                etDireccion.setText("");
                etHorario.setText("");

            }
        });

    }

}