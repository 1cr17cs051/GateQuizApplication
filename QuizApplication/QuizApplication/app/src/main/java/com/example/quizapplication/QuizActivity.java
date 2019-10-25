package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";

    public static final long COUNT_DOWN_IN_MILLIS1 = 60000;
    public static final long COUNT_DOWN_IN_MILLIS2 = 100000;
    public static final long COUNT_DOWN_IN_MILLIS3 = 150000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTiON_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewDifficulty;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private Button voiceEnableBtn;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecogniserIntent;
    private String keeper ="";
    private String mode = "ON";
    private Button voiceRecordBtn;
    private FloatingActionButton voice_mic;

    private TextToSpeech myTTS;

    private ColorStateList textColorDefaultRb;

    private ColorStateList getTextColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftINMillis;

    private ArrayList<Question> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.sample);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_activty);
        checkVoiceCommandPermission();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(QuizActivity.this);



        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.question_count);
        textViewCountDown = findViewById(R.id.text_view_question_count);
        textViewCategory = findViewById(R.id.text_view_category);
        textViewDifficulty = findViewById(R.id.text_view_difficulty);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        voice_mic = findViewById(R.id.voice_btn);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_cofirm_next);

        textColorDefaultRb = rb1.getTextColors();
        getTextColorDefaultCd = textViewCountDown.getTextColors();

        Intent intent  = getIntent();
        int categoryId = intent.getIntExtra(StartingScreenActivity.EXTRA_CATEGORY_ID,0);
        String categoryName = intent.getStringExtra(StartingScreenActivity.EXTRA_CATEGORY_NAME);

        String difficulty = intent.getStringExtra(StartingScreenActivity.EXTRA_DIFFICULTY);

        textViewCategory.setText("Category: " + categoryName);
        textViewDifficulty.setText("Difficulty: "+ difficulty);

        if(savedInstanceState == null){
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);

            questionList= dbHelper.getQuestions(categoryId,difficulty);

            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }




        else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTiON_LIST);
            if(questionList == null){
                finish();
            }

            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftINMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDown();
            }else{
                updateCountDownText();
                showSolution();
            }
        }








        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {

                ArrayList<String> matchesFound = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                keeper.toLowerCase();
                if(matchesFound != null) {

                    if (mode.equals("ON")) {

                        keeper = matchesFound.get(0);


                            if (keeper.indexOf("one") != -1 || keeper.indexOf("a") != -1) {

                                rb1.setChecked(true);
                                speak("You selected option a, please confirm your answer.");
                                Toast.makeText(QuizActivity.this, "You selected option a please confirm your answer by pressing confirm button", Toast.LENGTH_LONG).show();

                            } else if (keeper.indexOf("two") != -1 || keeper.indexOf("b") != -1) {

                                rb2.setChecked(true);
                                speak("You selected option b, please confirm your answer.");
                                Toast.makeText(QuizActivity.this, "You selected option b please confirm your answer by pressing confirm button", Toast.LENGTH_LONG).show();

                            } else if (keeper.indexOf("three") != -1 || keeper.indexOf("c") != -1) {

                                rb3.setChecked(true);
                                speak("You selected option c, please confirm your answer.");
                                Toast.makeText(QuizActivity.this, "You selected option c please confirm your answer by pressing confirm button", Toast.LENGTH_LONG).show();

                            } else if (keeper.indexOf("four") != -1 || keeper.indexOf("d") != -1) {

                                rb4.setChecked(true);
                                speak("You selected option d, please confirm your answer.");
                                Toast.makeText(QuizActivity.this, "You selected option d please confirm your answer by pressing confirm button", Toast.LENGTH_LONG).show();


                        }

                    }
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });


        voice_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speechRecogniserIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechRecogniserIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechRecogniserIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                speechRecognizer.startListening(speechRecogniserIntent);
                initializeTexttospeech();
            }
        });


        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp.start();
                        if (!answered) {
                            if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                                checkAnwser();
                            } else {
                                Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showNextQuestion();
                        }
                    }
                });

            }

            private void initializeTexttospeech(){
                myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(myTTS.getEngines().size()==0){
                            Toast.makeText(QuizActivity.this,"There is no TTS engine in your device",Toast.LENGTH_LONG).show();
                        }
                        else{
                            myTTS.setLanguage(Locale.US);
                            speak("tell the correct option");
                        }
                    }
                });


            }

            private void speak(String message){
                if(Build.VERSION.SDK_INT >= 21){
                    myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
                }
                else{
                    myTTS.speak(message,TextToSpeech.QUEUE_FLUSH,null);
                }
            }

            private void showNextQuestion() {


                Intent intent  = getIntent();
                int categoryId = intent.getIntExtra(StartingScreenActivity.EXTRA_CATEGORY_ID,0);
                String categoryName = intent.getStringExtra(StartingScreenActivity.EXTRA_CATEGORY_NAME);

                String difficulty = intent.getStringExtra(StartingScreenActivity.EXTRA_DIFFICULTY);

                rb1.setTextColor(textColorDefaultRb);
                rb2.setTextColor(textColorDefaultRb);
                rb3.setTextColor(textColorDefaultRb);
                rb4.setTextColor(textColorDefaultRb);
                rbGroup.clearCheck();

                if (questionCounter < questionCountTotal) {
                    currentQuestion = questionList.get(questionCounter);

                    textViewQuestion.setText(currentQuestion.getQuestion());

                    rb1.setText(currentQuestion.getOption1());
                    rb2.setText(currentQuestion.getOption2());
                    rb3.setText(currentQuestion.getOption3());
                    rb4.setText(currentQuestion.getOption4());

                    questionCounter++;
                    textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
                    answered = false;
                    buttonConfirmNext.setText("Confirm");

                    if(difficulty.contentEquals("Easy")){
                        timeLeftINMillis = COUNT_DOWN_IN_MILLIS1;
                        startCountDown();
                    }
                    else if(difficulty.contentEquals("Medium")){
                        timeLeftINMillis = COUNT_DOWN_IN_MILLIS2;
                        startCountDown();
                    }
                    else if(difficulty.contentEquals("Hard")){
                        timeLeftINMillis = COUNT_DOWN_IN_MILLIS3;
                        startCountDown();
                    }

                } else {
                    finishQuiz();

                }

            }

            private void startCountDown(){
                countDownTimer = new CountDownTimer(timeLeftINMillis,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeLeftINMillis = millisUntilFinished;
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                        timeLeftINMillis = 0;
                        updateCountDownText();
                        checkAnwser();
                    }
                }.start();
            }
            private void updateCountDownText(){

                int minutes = (int)(timeLeftINMillis/1000)/60;
                int seconds = (int) (timeLeftINMillis/1000)%60;

                String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

                textViewCountDown.setText(timeFormatted);

                if(timeLeftINMillis <10000){
                    textViewCountDown.setTextColor(Color.RED);
                }
                else{
                    textViewCountDown.setTextColor(getTextColorDefaultCd);
                }
            }

            private void checkAnwser() {
                answered = true;

                countDownTimer.cancel();

                RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());

                int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

                if (answerNr == currentQuestion.getAnswerNr()) {
                    score++;
                    textViewScore.setText("Score: " + score);
                }
                showSolution();
            }

            private void showSolution() {
                rb1.setTextColor(Color.RED);
                rb2.setTextColor(Color.RED);
                rb3.setTextColor(Color.RED);
                rb4.setTextColor(Color.RED);

                switch (currentQuestion.getAnswerNr()) {
                    case 1:
                        rb1.setTextColor(Color.GREEN);
                        textViewQuestion.setText("Answer a is correct");
                        break;
                    case 2:
                        rb2.setTextColor(Color.GREEN);
                        textViewQuestion.setText("Answer b is correct");
                        break;
                    case 3:
                        rb3.setTextColor(Color.GREEN);
                        textViewQuestion.setText("Answer c is correct");
                        break;
                    case 4:
                        rb4.setTextColor(Color.GREEN);
                        textViewQuestion.setText("Answer d is correct");
                        break;

                }

                if (questionCounter < questionCountTotal) {
                    buttonConfirmNext.setText("Next");

                } else {
                    buttonConfirmNext.setText("Finish");
                }
            }

            private void finishQuiz() {
                Intent resultIntent= new Intent();
                resultIntent.putExtra(EXTRA_SCORE,score);
                setResult(RESULT_OK,resultIntent);
                finish();
            }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }
        else{
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESTION_COUNT,questionCounter);
        outState.putLong(KEY_MILLIS_LEFT,timeLeftINMillis);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTiON_LIST,questionList);
    }

    private void checkVoiceCommandPermission(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!(ContextCompat.checkSelfPermission(QuizActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)){

                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName()));
                startActivity(intent);
                finish();

            }

        }

    }

    private void initTypeface(Context context ){
        Typeface typeface  = Typeface.createFromAsset(context.getAssets(),"jsMath-cmbsy10 (1).ttf");
        textViewQuestion.setTypeface(typeface);
    }


}
