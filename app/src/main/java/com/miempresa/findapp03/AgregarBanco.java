package com.miempresa.findapp03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;


public class AgregarBanco extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Banco");

    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 22;
    Button agregar, agregarImagen;
    EditText etNombre, etNombreSucursal, etTipo, etDireccion, etHorario;
    ImageView imagenBanco;
    private Uri ubicacionImagen;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            ubicacionImagen = data.getData();

            try{
                Bitmap imagen = MediaStore.Images.Media.getBitmap(getContentResolver(), ubicacionImagen);
                imagenBanco.setImageBitmap(imagen);

            }catch(IOException e){
                e.printStackTrace();

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_banco);

        agregar = findViewById(R.id.btnAgregar);
        etNombre = findViewById(R.id.etnombrein);
        etNombreSucursal = findViewById(R.id.etsurcursalin);
        etTipo = findViewById(R.id.ettipoin);
        etDireccion = findViewById(R.id.etdireccionin);
        etHorario = findViewById(R.id.ethorarioin);
        agregarImagen = findViewById(R.id.btnAgregarImagen);
        imagenBanco = findViewById(R.id.imageView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        agregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cargarImagen();
            }
        });

        imagenBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarImagen();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String sucursal = etNombreSucursal.getText().toString();
                String tipo = etTipo.getText().toString();
                String direccion = etDireccion.getText().toString();
                String horario = etHorario.getText().toString();

                Banco b = new Banco();
                b.setNombre(nombre);
                b.setSurcursal(sucursal);
                b.setTipo(tipo);
                b.setDireccion(direccion);
                b.setHorario(horario);

                myRef.push().setValue(b);

                Toast.makeText(getApplicationContext(), "El banco se agrego con exito", Toast.LENGTH_LONG).show();
                etNombre.setText("");
                etNombreSucursal.setText("");
                etTipo.setText("");
                etDireccion.setText("");
                etHorario.setText("");

            }
        });

    }
    public void seleccionarImagen(){

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Seleccione la imagen"), PICK_IMAGE_REQUEST);

    }

    public void cargarImagen () {

        if (ubicacionImagen != null) {

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(ubicacionImagen).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AgregarBanco.this, "Se cargo la imagen", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
            );


        }
    }
}