package com.miempresa.findapp03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class activity_profile extends AppCompatActivity {

    ImageView profilePhoto;
    EditText name;
    EditText lastName;
    EditText dateOfBirth;
    EditText address;
    EditText phone;
    EditText email;
    Button updateProfile;
    Button changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePhoto = findViewById(R.id.profileImageView);
        name = findViewById(R.id.nameProfileTextView);
        lastName = findViewById(R.id.lastnameProfileTextView);
        dateOfBirth= findViewById(R.id.dateofbirthTextView);
        address = findViewById(R.id.addressProfileTextView);
        phone = findViewById(R.id.phoneProfileTextView);
        email = findViewById(R.id.emailProfileTextView);
        updateProfile = findViewById(R.id.updateProfileButton);
        changePassword = findViewById(R.id.changePasswordButton);

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchStorage.launch(new Intent(MediaStore.ACTION_PICK_IMAGES));
            }
        });

    }

    ActivityResultLauncher<Intent> launchStorage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == RESULT_OK) {
                        Bundle extras = o.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        profilePhoto.setImageBitmap(imgBitmap);
                    }
                }
            });


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemSalir){
            Intent i = new Intent(activity_profile.this, MainActivity.class);
            startActivity(i);
        } if(item.getItemId() == R.id.itemLista){
            Intent i = new Intent(activity_profile.this, ListarBancos.class);
            startActivity(i);
        }if(item.getItemId() == R.id.itemHome){
            Intent i = new Intent(activity_profile.this, activity_welcome.class);
            startActivity(i);
        }
        return true;
    }

}