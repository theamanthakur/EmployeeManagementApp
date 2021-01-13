package com.ttl.ritz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class registerActivity extends AppCompatActivity {
    Button btnLogin, btnReg;
    EditText edtEmail, edtPass, edtDesig, edtName;
    FirebaseAuth mAuth;
    DatabaseReference mdatabase;
    String Desig,Email,Password, name;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtDesig = findViewById(R.id.textDesig);
        edtEmail = findViewById(R.id.textEmail);
        edtPass = findViewById(R.id.textPass);
        edtName = findViewById(R.id.textName);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnSignup);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this,loginActivity.class));
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegister();
            }
        });
        mdatabase = FirebaseDatabase.getInstance().getReference("Users");


    }

    private void UserRegister() {
        name = edtName.getText().toString().trim();
        Desig = edtDesig.getText().toString().trim();
        Email = edtEmail.getText().toString().trim();
        Password = edtPass.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            edtName.setError("Enter Valid Name");
            edtName.requestFocus();
            return;
        }else if (TextUtils.isEmpty(Email)){
            edtEmail.setError("Enter Valid Email");
            edtEmail.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            edtEmail.setError("Enter Valid Email");
            edtEmail.requestFocus();
        } else if (TextUtils.isEmpty(Password)){
            edtPass.setError("Enter Valid Password");
            edtPass.requestFocus();
            return;
        }else if (Password.length()<6){
            edtPass.setError("Enter more than 6 character");
            edtPass.requestFocus();
            return;
        }else if(TextUtils.isEmpty(Desig)){
            edtDesig.setError("Enter Designation");
            edtDesig.requestFocus();
        }
       progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(name,Email,Desig);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"User registered Successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registerActivity.this,MainActivity.class));
                                progressBar.setVisibility(View.GONE);
                            }else {
                                Toast.makeText(getApplicationContext(),"Error Sign Up",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Error Sign Up",Toast.LENGTH_SHORT).show();
                }
//                startActivity(new Intent(registerActivity.this,MainActivity.class));
            }
        });
    }
//

//    private void OnAuth(FirebaseUser user) {
//        createAnewUser(user.getUid());
//    }
//    private void createAnewUser(String uid) {
//        User user = BuildNewuser();
//        mdatabase.child(uid).setValue(user);
//    }
//
//
//    private User BuildNewuser(){
//        return new User(
//                getDesig(),
//                getUserEmail(),
//                new Date().getTime()
//        );
//    }

//    public String getDesig() {
//        return Desig;
//    }
//
//    public String getUserEmail() {
//        return Email;
//    }
}