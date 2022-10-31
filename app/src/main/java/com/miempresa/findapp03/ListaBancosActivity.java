package com.miempresa.findapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaBancosActivity extends AppCompatActivity {

    private Button buttonSeleccionar1;
    private Button buttonSeleccionar2;
    private Button buttonSeleccionar3;
    private Button buttonSeleccionar4;
    private Button buttonSeleccionar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bancos);

        buttonSeleccionar1 = findViewById(R.id.buttonSeleccionar1);
        buttonSeleccionar2 = findViewById(R.id.buttonSeleccionar2);
        buttonSeleccionar3 = findViewById(R.id.buttonSeleccionar3);
        buttonSeleccionar4 = findViewById(R.id.buttonSeleccionar4);
        buttonSeleccionar5 = findViewById(R.id.buttonSeleccionar5);

        buttonSeleccionar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaBancosActivity.this, Banco.class);
                startActivity(i);
            }
        });
    }
}