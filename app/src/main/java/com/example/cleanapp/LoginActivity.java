package com.example.cleanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    protected EditText emailTxt, passwordTxt;
    protected Button btnLogin, btnRegister;
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
        emailTxt = findViewById(R.id.emailEditText);
        passwordTxt = findViewById(R.id.passwordEditText);
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
                    Toast.makeText(LoginActivity.this, "LOGIN !",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,OwnerMainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "plz, Login ",Toast.LENGTH_SHORT).show();
                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    String mail = emailTxt.getText().toString();
                    String password = passwordTxt.getText().toString();

                    if( mail.isEmpty() )
                    {
                        emailTxt.setError("please enter an emailTextField");
                        emailTxt.requestFocus();
                    }
                    else if(password.isEmpty())
                    {
                        passwordTxt.setError("password missing");
                        passwordTxt.requestFocus();
                    }
                    else if(mail.isEmpty() && password.isEmpty())
                    {
                        Toast.makeText(LoginActivity.this,"field are empty",Toast.LENGTH_SHORT).show();
                    }
                    else if (!(mail.isEmpty() && password.isEmpty()))
                    {
                        myFirebaseAuth.signInWithEmailAndPassword(mail,password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Login Error",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent toHome = new Intent(LoginActivity.this,OwnerMainActivity.class);
                                startActivity(toHome);
                            }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                }

            });

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intoRegister = new Intent(LoginActivity.this,registerActivity.class);
                startActivity(intoRegister);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myFirebaseAuth.addAuthStateListener(myAuthStateListener);
    }
}

