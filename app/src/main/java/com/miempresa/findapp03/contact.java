package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class contact extends AppCompatActivity {
    EditText contactEmail;
    EditText message;
    Button sendButton;
    FirebaseFirestore contactFirestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactFirestore = FirebaseFirestore.getInstance();

        contactEmail = findViewById(R.id.emailContactTextField);
        message= findViewById(R.id.messageTextField);
        sendButton = findViewById(R.id.sendContactButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = contactEmail.getText().toString();
                String messages = message.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("email", email);
                map.put("message", messages);

                contactFirestore.collection("message").document(email).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(contact.this, "Message sent!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(contact.this, "Fail sent", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}