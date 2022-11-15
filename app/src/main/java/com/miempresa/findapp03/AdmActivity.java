package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
                Intent i  = new Intent(AdmActivity.this, activity_listar_bancos_adm.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.activity_menuadm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemSaliradm){
            Intent i = new Intent(AdmActivity.this, MainActivity.class);
            startActivity(i);
        } if(item.getItemId() == R.id.itemListaadm){
            Intent i = new Intent(AdmActivity.this, activity_listar_bancos_adm.class);
            startActivity(i);
        }if(item.getItemId() == R.id.itemVolveradm){
            Intent i = new Intent(AdmActivity.this, activity_welcome.class);
            startActivity(i);
        }
        return true;
    }
}