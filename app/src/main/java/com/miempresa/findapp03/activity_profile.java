package com.miempresa.findapp03;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class activity_profile extends AppCompatActivity {

    ImageView profilePhoto;
    Uri profileImage;
    TextView userID, nickname;
    EditText name;
    EditText lastName;
    EditText dateOfBirth;
    EditText email, password;
    Button updateProfile;
    Button changePassword;
    FirebaseAuth auth;
    FirebaseFirestore fireStore;
    StorageReference storage;
    DatabaseReference databaseReference;
    private String idUser;
    private String passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        fireStore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference("Userimages/");
        databaseReference = FirebaseDatabase.getInstance().getReference("Userimages/");
        idUser = auth.getCurrentUser().getUid();

        profilePhoto = findViewById(R.id.profileImageView);
        nickname = findViewById(R.id.nicknameTextView);
        userID= findViewById(R.id.userIDTextView);
        name = findViewById(R.id.nameProfileTextView);
        lastName = findViewById(R.id.lastnameProfileTextView);
        dateOfBirth= findViewById(R.id.dateofbirthTextView);
        email = findViewById(R.id.emailProfileTextView);
        password = findViewById(R.id.passwordProfileTextView);

        updateProfile = findViewById(R.id.addBankAdminButton);
        changePassword = findViewById(R.id.changePasswordButton);
/*
        Bundle checkData = getIntent().getExtras();
        String datas = checkData.getString("Keys");
        name.setText(datas);
        String datas1 = checkData.getString("Keys1");
        lastName.setText(datas1);
        String imageUriString = checkData.getString("ImageUri");
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            profilePhoto.setImageURI(imageUri);

*/

        Log.d("Exist", auth.getCurrentUser().getUid());
        email.setText(user.getEmail());
        userID.setText("Id:" + user.getUid());

        DocumentReference documentReference = fireStore.collection("User").document(idUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        nickname.setText(value.getString("nickname"));
                        name.setText(value.getString("name"));
                        lastName.setText(value.getString("last name"));
                        dateOfBirth.setText(value.getString("age"));
                        email.setText(value.getString("email"));
                    }
                });

        Glide.with(this).load(storage.child("UserImages/")).into(profilePhoto);

        }

/*
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launchStorage.launch(new Intent(MediaStore.ACTION_PICK_IMAGES));
            }
        });
*/

/*
    ActivityResultLauncher<Intent> launchStorage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == RESULT_OK) {
                        profileImage = o.getData().getData();
                        profilePhoto.setImageURI(profileImage);
                    }
                }
            });

*/
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemProfile) {
            Intent i = new Intent(activity_profile.this,activity_profile.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itemList) {
            Intent i = new Intent(activity_profile.this, ListarBancos.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itemHome) {
            Intent i = new Intent(activity_profile.this, activity_welcome.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itemExit) {
            Intent i = new Intent(activity_profile.this, MainActivity.class);
            startActivity(i);
        }

        return true;
    }

}