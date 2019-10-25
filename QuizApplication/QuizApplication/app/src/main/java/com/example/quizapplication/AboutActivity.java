package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {

    private TextView facebook;
    private  TextView linkedin;
    private TextView github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        facebook = findViewById(R.id.FacebookId);
        linkedin = findViewById(R.id.LinkedInId);
        github = findViewById(R.id.GitHubId);

        facebook.setText(R.string.facebookid);
        linkedin.setText(R.string.linkedinid);
        github.setText(R.string.githubid);


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bint=new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/hithesh.kumar.7549"));
                startActivity(bint);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bint=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/hithesh-kumar-cm-343bb0176"));
                startActivity(bint);
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bint=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/1cr17cs051"));
                startActivity(bint);
            }
        });




    }
}
