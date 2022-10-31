package com.miempresa.findapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AdmActivity extends AppCompatActivity {

    Button buttonCrearBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        buttonCrearBanco = findViewById(R.id.buttonCrearBanco);
        buttonCrearBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AdmActivity.this, AgregarBanco.class);
                startActivity(i);
                Context context  = getApplicationContext();
                CharSequence texto = "Se agrego el banco";
                int duracion = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, texto, duracion);
                toast.show();
            }
        });
    }
}