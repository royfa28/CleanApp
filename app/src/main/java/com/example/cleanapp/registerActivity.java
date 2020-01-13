package com.example.cleanapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cleanapp.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {

    public EditText emailTxt,passwordTxt, editTextPhone;
    public Button btnLogin, btnRegister;
    public TextView txtValrAcc;
    protected RadioGroup userLvlradioGroup;
    protected RadioButton radioButtonTenant,radioButtonOwner;
    FirebaseAuth  myFirebaseAuth;
    UserModel myUser = new UserModel();
    DatabaseReference myDataBase= FirebaseDatabase.getInstance().getReference();
    DatabaseReference getUser = FirebaseDatabase.getInstance().getReference().child("User");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Register");

        myFirebaseAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        passwordTxt = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        txtValrAcc = findViewById(R.id.textViewAlrAcc);
        userLvlradioGroup = findViewById(R.id.radioGroup);
        radioButtonOwner = findViewById(R.id.radioButtonOwner);
        radioButtonTenant = findViewById(R.id.radioButtonTenant);



        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String mail = emailTxt.getText().toString();
                String password = passwordTxt.getText().toString();

                if (isValidEmail(mail) && password.length() > 7) {
                    myFirebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(registerActivity.this, "sign up fail", Toast.LENGTH_SHORT).show();
                            } else {
                                //get firebase auth user key
                                String firebaseAuthUserId = getUserKeyFireAuth();
                                Log.d("fireAuthKey : ", firebaseAuthUserId);

                                Toast.makeText(registerActivity.this, firebaseAuthUserId, Toast.LENGTH_SHORT).show();
                                createUserInDB();
                                getUser.setValue(myUser.getUserKey());

                                /*getUser.child(myUser.getUserKey()).addChildEventListener(getUser);*/
                                getUser.child(myUser.getUserKey()).child("user mail").setValue(myUser.getUserMail());
                                getUser.child(myUser.getUserKey()).child("user phone").setValue(myUser.getUserPhone());

                                if(!(radioButtonOwner.isChecked())&&!(radioButtonTenant.isChecked()))
                                {
                                    Toast.makeText(registerActivity.this, "owner or tenant?", Toast.LENGTH_SHORT).show();
                                }
                                else if((radioButtonOwner.isChecked())&&!(radioButtonTenant.isChecked()))
                                {
                                    myUser.setUserLvl(true);
                                    getUser.child(myUser.getUserKey()).child("user lvl").setValue(myUser.getUserLvl());
                                    startActivity(new Intent(registerActivity.this, OwnerMainActivity.class));
                                }
                                else if(!(radioButtonOwner.isChecked())&& (radioButtonTenant.isChecked()))
                                {
                                    myUser.setUserLvl(false);
                                    getUser.child(myUser.getUserKey()).child("user lvl").setValue(myUser.getUserLvl());
                                    startActivity(new Intent(registerActivity.this, OwnerMainActivity.class));//tenant screen
                                }
                            }
                        }
                    });

                }else{
                    Toast.makeText(registerActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }

        });

        txtValrAcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             Intent i = new Intent(registerActivity.this, LoginActivity.class);
             startActivity(i);
            }
        });
    }

    protected String getUserKeyFireAuth(){
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

    public final static boolean isValidEmail(CharSequence target){
        if (target == null)
            return false;

        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validatePassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.* [@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    protected void createUserInDB()
    {
        myUser.setUserMail(emailTxt.getText().toString());
        myUser.setUserPhone(editTextPhone.getText().toString());
        myUser.setUserKey(getUserKeyFireAuth());
    }


}


