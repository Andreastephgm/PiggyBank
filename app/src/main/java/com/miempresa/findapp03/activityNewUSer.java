package com.miempresa.findapp03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activityNewUSer extends AppCompatActivity {
    Button buttonCrearNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        buttonCrearNewUser = findViewById(R.id.NewUser);
        buttonCrearNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence texto = "Se creo un nuevo usuario";
                int duracion = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, texto, duracion);
                toast.show();
            }
        });
    }
}
