package com.miempresa.findapp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    private EditText TextUser, TextPassword;
    private String usuario, password;
    Button buttonNewUSer;
    //private FirebaseAuth miAutenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       buttonLogin = findViewById(R.id.buttonLogin);
       TextUser = findViewById(R.id.textUsername);
       TextPassword = findViewById(R.id.textPassword);
       buttonNewUSer = findViewById(R.id.buttonNewUser);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                usuario = TextUser.getText().toString();
                password = TextPassword.getText().toString();

                if(usuario.equals("user") && password.equals("123")) {
                    Context context  = getApplicationContext();
                    CharSequence texto = "El usuario ingreso con exito";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();
                    irLogin(usuario, password);
                }if(usuario.equals("admin") && password.equals("2010")){
                    Context context  = getApplicationContext();
                    CharSequence texto = "El administrador ingreso con exito";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();
                    irLoginAdm(usuario,password);
                }else{
                    Context context = getApplicationContext();
                    CharSequence texto = "credenciales invalidas";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();
                }
            }
        });

        buttonNewUSer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                irRegistrarse();
            }
        });
    }

    public void irLogin(String usuario, String password){
        Intent i = new Intent(this, ListaBancosActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("password", password);
        startActivity(i);
    }

    public void irRegistrarse(){
        Intent i = new Intent(this, activity_profile.class);
        startActivity(i);
    }

    public void irLoginAdm(String usuario, String password){
        Intent i = new Intent(this, AdmActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("password", password);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cicloVida","Estamos en estado onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("cicloVida","Estamos en estado onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("cicloVida","Estamos en estado onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cicloVida","Estamos en estado onDestroy");

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("cicloVida","Estamos en estado onRestart");

    }
}