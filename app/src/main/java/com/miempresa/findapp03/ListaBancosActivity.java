package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListaBancosActivity extends AppCompatActivity {

    Button btnIrMain;
    private FirebaseAuth miAutenticacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bancos);
        miAutenticacion =FirebaseAuth.getInstance();
        }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
        if(usuarioActual == null){
            Intent i = new Intent(ListaBancosActivity.this, MainActivity.class );
            startActivity(i);
        }
    }


}
