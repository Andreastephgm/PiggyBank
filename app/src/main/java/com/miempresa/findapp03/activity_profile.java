package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class activity_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSalir) {
            Intent i = new Intent(activity_profile.this, MainActivity.class);
            startActivity(i);

        }
        if (item.getItemId() == R.id.itemLista) {
            Intent i = new Intent(activity_profile.this, ListarBancos.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.Itemperfil) {
            Intent i = new Intent(activity_profile.this, activity_profile.class);
            startActivity(i);

        }
        if (item.getItemId() == R.id.Itemhome) {
            Intent i = new Intent(activity_profile.this, activity_welcome.class);
            startActivity(i);
        }
        return true;
    }
}