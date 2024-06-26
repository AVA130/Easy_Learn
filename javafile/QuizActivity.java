package com.example.easylearn;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;



import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    private TextView questions;
    private TextView question;
    private AppCompatButton option1,option2,option3,option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int totalTimeinMins=1;
    private int seconds=0;
    private List <QuestionList>questionsLists=new ArrayList<>();
    private int currentQuestionPosition=0;
    private String selectedOptionByUser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        final ImageView backBtn=findViewById(R.id.backBtn);
        final TextView selectedTopicName = findViewById(R.id.topicName);
        //final TextView timer=findViewById(R.id.timer);
        questions=findViewById(R.id.questions);
        question=findViewById(R.id.question);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);

        nextBtn=findViewById(R.id.nextBtn);

        final String getTopicName=getIntent().getStringExtra("SelectedTopic");
        selectedTopicName.setText(getTopicName);
        questionsLists=QuestionBank.getQuestion(getTopicName);
        ProgressDialog progressDialog =new ProgressDialog(QuizActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.hide();

        questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());


      //  startTimer(timer);
     //  DatabaseReference databseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://quiz-c4c86-default-rtdb.firebaseio.com/");



      /*  databseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalTimeinMins=Integer.parseInt(snapshot.child("time").getValue(String.class));


                for (DataSnapshot dataSnapshot : snapshot.child(getTopicName).getChildren()){

                    final String getQuestion=dataSnapshot.child("ques").getValue(String.class);
                    final String getOption1=dataSnapshot.child("op1").getValue(String.class);
                    final String getOption2=dataSnapshot.child("op2").getValue(String.class);
                    final String getOption3=dataSnapshot.child("op3").getValue(String.class);
                    final String getOption4=dataSnapshot.child("op4").getValue(String.class);
                    final String getAnswer=dataSnapshot.child("ans").getValue(String.class);

                    QuestionList questionList=new QuestionList(getQuestion,getOption1,getOption2,getOption3,getOption4,getAnswer);
                    questionsLists.add(questionList);


                }
                progressDialog.hide();

                questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
                question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
                option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
                option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
                option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
                option4.setText(questionsLists.get(currentQuestionPosition).getOption4());

                startTimer(timer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        option1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.parseColor("#FFFFFF"));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);

                }

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.parseColor("#FFFFFF"));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);

                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.parseColor("#FFFFFF"));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);

                }

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.parseColor("#FFFFFF"));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);

                }

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this,"Please select",Toast.LENGTH_SHORT).show();
                }
                else{
changeNextQuestion();
                }

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quizTimer.purge();
                quizTimer.cancel();

               // startActivity(new Intent(QuizActivity.this,quizFirst.class));
                finish();
            }
        });
    }
    public void changeNextQuestion(){
        currentQuestionPosition++;
        if((currentQuestionPosition+1)==questionsLists.size()){
            nextBtn.setText("Submit Quiz");
        }
        if(currentQuestionPosition<questionsLists.size())
        {
            selectedOptionByUser="";
            option1.setBackgroundResource(R.drawable.round_back_whitestroke10);
            option1.setTextColor(Color.parseColor("#1F6BBB"));

            option2.setBackgroundResource(R.drawable.round_back_whitestroke10);
            option2.setTextColor(Color.parseColor("#1F6BBB"));

            option3.setBackgroundResource(R.drawable.round_back_whitestroke10);
            option3.setTextColor(Color.parseColor("#1F6BBB"));

            option4.setBackgroundResource(R.drawable.round_back_whitestroke10);
            option4.setTextColor(Color.parseColor("#1F6BBB"));

            questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());
        }
        else{
            Intent intent=new Intent(QuizActivity.this,QuizResults.class);
            intent.putExtra("correct",getCorrectAnswers());
            intent.putExtra("incorrect",getInCorrectAnswers());
            startActivity(intent);

            finish();
        }
    }
  /* public void startTimer(TextView timerTextView) {
        quizTimer=new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds==0){
                    totalTimeinMins--;
                    seconds=30;
                }
                else if(seconds==0 && totalTimeinMins==0){
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this,"Time Over",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(QuizActivity.this,QuizActivity.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorrect",getCorrectAnswers());
                    startActivity(intent);
                    finish();
                }
                else{
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes=String.valueOf(totalTimeinMins);
                        String finalSeconds=String.valueOf(seconds);
                        if(finalMinutes.length()==1){
                            finalMinutes = "0"+finalMinutes;
                        }
                        if(finalSeconds.length()==1){
                            finalSeconds = "0"+finalSeconds;
                        }
                        timerTextView.setText(finalMinutes+":"+finalSeconds);
                    }
                });
            }
        },1000,1000);
    }*/
    private int getCorrectAnswers(){
        int correctAnswers=0;
        for(int i=0;i<questionsLists.size();i++)
        {
            final String getUserSelectedAnswer=questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer=questionsLists.get(i).getAnswer();
            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    }
    private int getInCorrectAnswers(){
        int correctAnswers=0;
        for(int i=0;i<questionsLists.size();i++)
        {
            final String getUserSelectedAnswer=questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer=questionsLists.get(i).getAnswer();
            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    public void onBackpressed()
    {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this,quizFirst.class));
        finish();

    }
    private void revealAnswer(){
        final String getAnswer=questionsLists.get(currentQuestionPosition).getAnswer();
        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green20);
            option1.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green20);
            option2.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green20);
            option3.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.round_back_green20);
            option4.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}
