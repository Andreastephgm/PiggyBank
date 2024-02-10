package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdmActivity extends AppCompatActivity {

    Button addBank;
    Button bankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        addBank = findViewById(R.id.addBankAdminButton);
        bankList = findViewById(R.id.bankListAdminButton);

        addBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmActivity.this, AgregarBanco.class);
                startActivity(intent);
            }
        });

        bankList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmActivity.this, activity_listar_bancos_adm.class);
                startActivity(intent);
            }
        });

    }

}