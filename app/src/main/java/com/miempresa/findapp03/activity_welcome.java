package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class activity_welcome extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemSalir){
            Intent i = new Intent(activity_welcome.this, MainActivity.class);
            mAuth.signOut();
            startActivity(i);
        } if(item.getItemId() == R.id.itemLista){
            Intent i = new Intent(activity_welcome.this, ListarBancos.class);
            startActivity(i);
        }if(item.getItemId() == R.id.itemPerfil){
            Intent i = new Intent(activity_welcome.this, activity_profile.class);
            startActivity(i);
        }if(item.getItemId() == R.id.itemHome){
            Intent i = new Intent(activity_welcome.this, activity_welcome.class);
            startActivity(i);
        }
        return true;
    }

}