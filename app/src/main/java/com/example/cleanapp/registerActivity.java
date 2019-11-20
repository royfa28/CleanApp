package com.example.cleanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    public EditText editTextEmail,editTextPassword;
    public Button btnLogin, btnRegister;
    public TextView txtValrAcc;
    FirebaseAuth  myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myFirebaseAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        txtValrAcc = findViewById(R.id.textViewAlrAcc);

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mail = editTextEmail.getText().toString();
                String password = editTextEmail.getText().toString();

                if( mail.isEmpty() )
                {
                    editTextEmail.setError("please enter an email");
                    editTextEmail.requestFocus();
                }
                else if(password.isEmpty())
                {
                    editTextPassword.setError("password missing");
                    editTextPassword.requestFocus();
                }
                else if(mail.isEmpty() && password.isEmpty())
                {
                    Toast.makeText(registerActivity.this,"field are empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(mail.isEmpty() && password.isEmpty()))
                {
                    myFirebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(registerActivity.this, "sign up fail",Toast.LENGTH_SHORT).show();
                            }
                            else
                                {
                                    startActivity(new Intent(registerActivity.this,HomeActivity.class));
                                }
                        }
                    });
                }
                else
                    {
                        Toast.makeText(registerActivity.this, "Error",Toast.LENGTH_SHORT).show();
                    }
            }

        });

        txtValrAcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             Intent i = new Intent(registerActivity.this,MainActivity.class);
             startActivity(i);
            }
        });
    }
}

