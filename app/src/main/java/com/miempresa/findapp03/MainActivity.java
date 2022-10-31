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

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText TextUser, TextPassword;
    private String usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       buttonLogin = findViewById(R.id.buttonLogin);
       TextUser = findViewById(R.id.textUsername);
       TextPassword = findViewById(R.id.textPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                usuario = TextUser.getText().toString();
                password = TextPassword.getText().toString();

                if(usuario.equals("user") && password.equals("123")){
                    Snackbar snackbar = Snackbar.make(buttonLogin, "el usuario ingreso con exito", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    irRegistrarse(usuario, password);
                    Context context  = getApplicationContext();
                    CharSequence texto = "credenciales validas";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();
                }else{
                    Context context  = getApplicationContext();
                    CharSequence texto = "credenciales invalidas";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();
                } if(usuario.equals("admin") && password.equals("2010")){
                    Snackbar snackbar = Snackbar.make(buttonLogin,"el usuario ingreso con exito", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    irAdminBancos(usuario,password);
                }else{
                    Context context = getApplicationContext();
                    CharSequence texto = "credenciales invalidas";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();
                }
            }
        });
    }

    public void irRegistrarse(String usuario, String password){
        Intent i = new Intent(this, ListaBancosActivity.class
        );
        i.putExtra("usuario", usuario);
        i.putExtra("password", password);
        startActivity(i);
    }

    public void irAdminBancos(String usuario, String password){
        Intent i = new Intent(this, AdmActivity.class
        );
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