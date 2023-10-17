package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


//import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    private EditText TextUser, TextPassword;
    private String email, password;
    Button buttonNewUSer;
   private FirebaseAuth miAutenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

       buttonLogin = findViewById(R.id.buttonLogin);
       TextUser = findViewById(R.id.textUsername);
       TextPassword = findViewById(R.id.textPassword);
       buttonNewUSer = findViewById(R.id.buttonNewUser);
      miAutenticacion = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                email = TextUser.getText().toString();
                password = TextPassword.getText().toString();
                ingresar(email,password);

                if(email.equals("admin") && password.equals("2010")){
                    Context context  = getApplicationContext();
                    CharSequence texto = "El administrador ingreso con exito nuevo";
                    int duracion = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, texto, duracion);
                    toast.show();

                   irLoginAdm(email, password);
                }
            }
        });

        buttonNewUSer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activityNewUSer.class);
                startActivity(intent);

                irRegistrarse();
            }
        });
    }

    public void ingresar(String email, String password){

        miAutenticacion.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this, "El ingreso ha sido exitoso", Toast.LENGTH_LONG).show();
                            FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
                            actualizarUI(usuarioActual);


                        }else{
                            Toast.makeText(MainActivity.this, "Los datos son incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
       actualizarUI(usuarioActual);

    }

    public void actualizarUI(FirebaseUser usuarioActual){
        if(usuarioActual != null){
            Intent i = new Intent(MainActivity.this, activity_welcome.class);
            startActivity(i);
        }else{
            Toast.makeText(MainActivity.this, "Ingrese sus datos de acceso", Toast.LENGTH_LONG).show();

        }

    }


    public void irLogin(String usuario, String password){
        Intent i = new Intent(this, ListarBancos.class);
        i.putExtra("usuario", usuario);
        i.putExtra("password", password);
        startActivity(i);
    }

    public void irRegistrarse(){
        Intent i = new Intent(MainActivity.this , activityNewUSer.class);
        startActivity(i);
    }

    public void irLoginAdm(String email, String password){
        Intent i = new Intent(this, AdmActivity.class);
        i.putExtra("usuario", email);
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