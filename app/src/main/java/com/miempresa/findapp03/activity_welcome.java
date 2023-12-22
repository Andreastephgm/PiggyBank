package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class activity_welcome extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView welcome;
    Button jobsButton;
    Button contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = findViewById(R.id.welcomeTextView);
        jobsButton = findViewById(R.id.jobsButton);
        contactButton = findViewById(R.id.contactButton);

        jobsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_welcome.this, Jobs.class);
                startActivity(intent);
            }
        });
        contactButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_welcome.this, contact.class);
                startActivity(intent);
            }
        });
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