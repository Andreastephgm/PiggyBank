package com.miempresa.findapp03;

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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class activityNewUSer extends AppCompatActivity {
    Button buttonCrearNewUser;
    EditText  idName, idLastName, idEdad, idEmail, idPassword, idNickname;
    ImageView photoNewUser;
    Uri userImage;
    FirebaseAuth myAuth;
    FirebaseFirestore mFirestore;
    StorageReference storageReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

       myAuth = FirebaseAuth.getInstance();
       mFirestore = FirebaseFirestore.getInstance();
       storageReference = FirebaseStorage.getInstance().getReference();

       photoNewUser = findViewById(R.id.photoNewUserImageView);
       idNickname = findViewById(R.id.idNickname);
       idName= findViewById(R.id.idNombre);
       idLastName = findViewById(R.id.idApellido);
       idEdad = findViewById(R.id.idEdad);
       idEmail = findViewById(R.id.idCorreo);
       idPassword = findViewById(R.id.idPassword);

       buttonCrearNewUser = findViewById(R.id.NewUser);

         buttonCrearNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nickname = idNickname.getText().toString();
                String nameUser = idName.getText().toString();
                String lastNameUser = idLastName.getText().toString();
                String ageUser = idEdad.getText().toString();
                String emailUser = idEmail.getText().toString();
                String passwordUser = idPassword.getText().toString();
                upload();

                if(nickname.isEmpty()||nameUser.isEmpty()||lastNameUser.isEmpty()||ageUser.isEmpty()||emailUser.isEmpty()||passwordUser.isEmpty()){

                    Toast.makeText(activityNewUSer.this, "Empty fields", Toast.LENGTH_SHORT).show();
                }else{
                    checkCrednetials(nickname,nameUser,lastNameUser,ageUser, emailUser,passwordUser);
                    Toast.makeText(activityNewUSer.this, "Filled", Toast.LENGTH_SHORT).show();
                }
            }

        });

         photoNewUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 launchStorage.launch(intent);
             }
         });
    }


    ActivityResultLauncher<Intent>launchStorage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        userImage = o.getData().getData();
                        photoNewUser.setImageURI(userImage);
                    }
                }
    });

    private void upload() {
        StorageReference reference = storageReference.child("Userimages/" + UUID.randomUUID().toString());
        reference.putFile(userImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(activityNewUSer.this, "Great!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activityNewUSer.this, "Fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void checkCrednetials(String nickname,String nameUser, String lastNameUser,String ageUser, String emailUser, String passwordUser){

        myAuth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    String id = myAuth.getCurrentUser().getUid();
                    map.put("id", id);
                    map.put("nickname", nickname);
                    map.put("name", nameUser);
                    map.put("last name", lastNameUser);
                    map.put("age", ageUser);
                    map.put("email", emailUser);
                    map.put("password", passwordUser);

                    mFirestore.collection("User").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finish();
                            Intent intent = new Intent(activityNewUSer.this, activity_profile.class);
                            startActivity(intent);
                            Toast.makeText(activityNewUSer.this, "New user created", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activityNewUSer.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });
    }

}
