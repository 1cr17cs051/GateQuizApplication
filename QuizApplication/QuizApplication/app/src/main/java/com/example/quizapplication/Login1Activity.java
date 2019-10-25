package com.example.quizapplication;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login1Activity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    ViewGroup progressView;
    private long backPressedTime;
    protected boolean isProgressShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        /*Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        View v = this.getLayoutInflater().inflate(R.layout.progressbar, null);
        dialog.setContentView(v);
        dialog.show();*/

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.sample);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.email_entry);
        mTextPassword = (EditText) findViewById(R.id.password_entry);
        mButtonLogin = (Button) findViewById(R.id.login_button);
        mTextViewRegister = (TextView) findViewById(R.id.TextViewReg);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login1Activity.this, LoginActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                boolean res = db.checkUseratlogin(user,pwd);
                if (res == true) {
                    Intent HomePage = new Intent(Login1Activity.this, StartingScreenActivity.class);
                    startActivity(HomePage);
                } else {
                    Toast.makeText(Login1Activity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finish();
        }
        else{
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

/*
    public void showProgressingView() {

        if (!isProgressShowing) {
            View view=findViewById(R.id.progressBar1);
            view.bringToFront();
        }
    }

    public void hideProgressingView() {
        View v = this.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView(progressView);
        isProgressShowing = false;
    }
}
*/
}