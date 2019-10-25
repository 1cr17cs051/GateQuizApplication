package com.example.quizapplication;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class StartingScreenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ=1;

    public static final String EXTRA_CATEGORY_ID = "extraCategoryId";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String KEY_HIGHSCORE = "KeyHighscore";

    private TextView textViewHighscore;
    private TextView aboutTextView;

    private Spinner SpinnerDifficulty;
    private Spinner SpinnerCategory;
    private Spinner SpinnerExam;

    private Button examButton;


    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_screen_activity);

        textViewHighscore = findViewById(R.id.text_view_highcsore);
        loadHighScore();

        Button buttononStartQuiz = findViewById(R.id.StartButton);

        SpinnerDifficulty = findViewById(R.id.spinner_difficulty);

        SpinnerCategory = findViewById(R.id.spinner_category);

        aboutTextView = findViewById(R.id.text_view_about);

        SpinnerExam = findViewById(R.id.examSpinner);

        examButton = findViewById(R.id.exambutton);

        loadCategories();
        loadDifficultyLevels();

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.sample);


        buttononStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startQuiz();
            }
        });

        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(StartingScreenActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartingScreenActivity.this,ExamActivity.class);
                startActivity(intent);
            }
        });


    }





    private void startQuiz(){

        Category selectedCategory = (Category) SpinnerCategory.getSelectedItem();
        int categroyID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();
        String difficulty = SpinnerDifficulty.getSelectedItem().toString();


        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,categroyID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        intent.putExtra(EXTRA_DIFFICULTY,difficulty);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode==RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if(score> highScore){
                    updateHighScore(score);
                }
            }
        }

    }

    private void loadCategories(){
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerCategory.setAdapter(adapterCategories);

    }

    private void loadDifficultyLevels(){

        String[] difficultyLevels = Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerDifficulty.setAdapter(adapterDifficulty);

    }

    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighscore.setText("Highscore : "+highScore);
    }

    private void updateHighScore(int highScoreNew){
        highScore = highScoreNew;
        textViewHighscore.setText("Highscore : " + highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore);
        editor.apply();
    }
}
