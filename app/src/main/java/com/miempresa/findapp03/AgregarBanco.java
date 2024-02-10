package com.miempresa.findapp03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AgregarBanco extends AppCompatActivity {
    ImageView bankImage;
    Uri image;
    EditText bankName;
    EditText address;
    EditText schedule;
    EditText latitude;
    EditText longitude;
    EditText type;
    Button location;
    Button addRegister;

    FirebaseFirestore storeFirestore;
    StorageReference storeStorage;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_banco);

        storeFirestore = FirebaseFirestore.getInstance();
        storeStorage = FirebaseStorage.getInstance().getReference();

        bankImage = findViewById(R.id.addBankImageView);
        bankName = findViewById(R.id.nameBankText);
        address = findViewById(R.id.addressText);
        schedule = findViewById(R.id.scheduleText);
        latitude = findViewById(R.id.latitudeText);
        longitude = findViewById(R.id.longuitudeText);
        type = findViewById(R.id.typeText);
        addRegister = findViewById(R.id.addBankAdminButton);
        location = findViewById(R.id.bankListAdminButton);


        addRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bank = bankName.getText().toString();
                String bankAddress = address.getText().toString();
                String bankSchedule = schedule.getText().toString();
                String bankLatitude = latitude.getText().toString();
                String bankLongitude = longitude.getText().toString();
                String bankType = type.getText().toString();
                upload();

                Map<String, Object> map = new HashMap<>();
                map.put("bank", bank);
                map.put("Address", bankAddress);
                map.put("Schedule", bankSchedule);
                map.put("Latitude", bankLatitude);
                map.put("Longitude", bankLongitude);
                map.put("Type", bankType);

                storeFirestore.collection("Banks").document(bank).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AgregarBanco.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AgregarBanco.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        bankImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launchStorage.launch(intent);
            }
        });
    }


    ActivityResultLauncher<Intent> launchStorage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        image = o.getData().getData();
                        bankImage.setImageURI(image);
                    }
                }
            });

    private void upload(){
        StorageReference reference = storeStorage.child("images/" + UUID.randomUUID().toString());
        reference.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AgregarBanco.this,"Great!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AgregarBanco.this,"Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
