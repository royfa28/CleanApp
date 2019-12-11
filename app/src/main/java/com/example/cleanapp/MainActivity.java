package com.example.cleanapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import com.google.android.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public EditText editTextMail,editTextPassword;
    public Button btnLogin, btnRegister;
    FirebaseAuth  myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        myFirebaseAuth = FirebaseAuth.getInstance();
        editTextMail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        myFirebaseAuth = FirebaseAuth.getInstance();

        myAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
                if(myFirebaseUser != null)// weak no password check
                {
                    //try
                    //get firebase auth user key
                    String firebaseAuthUserId = getUserKeyFireAuth();
                    Log.d("fireAuthKey : ",firebaseAuthUserId);

                    Toast.makeText(MainActivity.this, firebaseAuthUserId,Toast.LENGTH_SHORT).show();
                    //try
                    Toast.makeText(MainActivity.this, "LOGIN !",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,OwnerMainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "plz, Login ",Toast.LENGTH_SHORT).show();
                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener()
        {

                @Override
                public void onClick(View v)
                {
                    String mail = editTextMail.getText().toString();
                    String password = editTextPassword.getText().toString();

                    if( mail.isEmpty() )
                    {
                        editTextMail.setError("please enter an email");
                        editTextMail.requestFocus();
                    }
                    else if(password.isEmpty())
                    {
                        editTextPassword.setError("password missing");
                        editTextPassword.requestFocus();
                    }
                    else if(mail.isEmpty() && password.isEmpty())
                    {
                        Toast.makeText(MainActivity.this,"field are empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (!(mail.isEmpty() && password.isEmpty()))
                    {
                        myFirebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Login Error",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
//                                funcition userlvlUiRender
                                Intent toHome = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(toHome);
                            }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                }

            });

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intoRegister = new Intent(MainActivity.this,registerActivity.class);
                startActivity(intoRegister);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myFirebaseAuth.addAuthStateListener(myAuthStateListener);
    }

    protected String getUserKeyFireAuth()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fireAuthUserKey="";

        if (user != null) {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            fireAuthUserKey = user.getUid();
        }

        return fireAuthUserKey;
    }
}


