package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3200);
                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    Intent mainIntent = new Intent(SplashActivity.this, Login1Activity.class);
                    startActivity(mainIntent);
                }

            }
        };

        thread.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
