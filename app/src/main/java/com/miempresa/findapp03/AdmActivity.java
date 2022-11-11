package com.miempresa.findapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmActivity extends AppCompatActivity {

    Button buttonCrearBanco;
    Button ListaLB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        buttonCrearBanco = findViewById(R.id.buttonCrearBanco);
        ListaLB= findViewById(R.id.buttonLBANCOS);
        buttonCrearBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(AdmActivity.this, AgregarBanco.class);
                startActivity(i);

            }
        });
        ListaLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(AdmActivity.this, ListarBancos.class);
                startActivity(i);
            }
        });
    }
}