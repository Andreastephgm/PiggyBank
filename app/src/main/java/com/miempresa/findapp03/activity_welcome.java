package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class activity_welcome extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore fireStore;
    TextView welcome, helloWelcome;
    Button jobsButton;
    Button contactButton;

    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();
        idUser = mAuth.getCurrentUser().getUid();
        welcome = findViewById(R.id.welcomeTextView);
        helloWelcome = findViewById(R.id.helloWelcome);
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


        DocumentReference documentReference = fireStore.collection("User").document(idUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                helloWelcome.setText("Hello,"+ value.getString("nickname") +"!");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemProfile) {
            Intent i = new Intent(activity_welcome.this, activity_profile.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itemList) {
            Intent i = new Intent(activity_welcome.this, ListarBancos.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itemHome) {
            Intent i = new Intent(activity_welcome.this, activity_welcome.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itemExit) {
            Intent i = new Intent(activity_welcome.this,  MainActivity.class);
            startActivity(i);
            mAuth.signOut();
        }

            return true;
        }
}