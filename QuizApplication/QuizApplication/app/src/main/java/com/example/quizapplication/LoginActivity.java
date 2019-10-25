package com.example.quizapplication;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.sample);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.email_entry);
        mTextPassword = (EditText) findViewById(R.id.password_entry);
        mTextCnfPassword = (EditText) findViewById(R.id.passwordcnf_entry);
        mButtonRegister = (Button) findViewById(R.id.signup_button);
        mTextViewLogin = (TextView) findViewById(R.id.tvSignIn);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(LoginActivity.this, Login1Activity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if (user.equals("") || pwd.equals("") && cnf_pwd.equals("")) {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (pwd.equals(cnf_pwd)) {
                        Boolean checkUser = db.checkUser(user, pwd);
                        if (checkUser == true) {
                            Boolean addUser = db.addUser(user, pwd);
                            if (addUser == true) {
                                Toast.makeText(LoginActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                                Intent moveToLogin = new Intent(LoginActivity.this, Login1Activity.class);
                                startActivity(moveToLogin);
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "passwords did not matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}