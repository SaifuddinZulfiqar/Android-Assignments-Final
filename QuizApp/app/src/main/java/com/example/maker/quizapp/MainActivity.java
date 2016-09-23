package com.example.maker.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private int currentQuestion;
    private String[] questions;
    private String[] answers;

    private Button answerButton;
    private Button questionButton;

    private TextView questionView;
    private TextView answerView;

    private EditText answerText;




          /*this method will run when you run your application*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init() {
        questions = new String[]{"What does SDK stands for ?", "What does IDE stands for ?"
                , "What does ADT stands for ?", "What does JDK stands for ?", "What does JRE stands for ?"};
        answers = new String[]{"Software Development Kit", "Integrated Development Environment", "Android Development Tool", "Java Development Kit", "Java Runtime Environment"};
        currentQuestion = -1;
        answerButton = (Button) findViewById(R.id.AnswerButton);
        questionButton = (Button) findViewById(R.id.QuestionButton);

        questionView = (TextView) findViewById(R.id.QuestionTextView);

        answerView = (TextView) findViewById(R.id.AnswerTextView);

        answerText = (EditText) findViewById(R.id.AnswerText);

        answerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer();

            }
        });

        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                showQuestion();

            }
        });

    }


    private void showQuestion() {
        currentQuestion++;
        if (currentQuestion == questions.length)
            currentQuestion = 0;
        questionView.setText(questions[currentQuestion]);
        answerView.setText("");
    }


    public boolean isCorrect(String answer) {
        return (answer.equalsIgnoreCase(answers[currentQuestion]));
    }

    public void checkAnswer() {
        String answer = answerText.getText().toString();
        if (isCorrect(answer))
            answerView.setText("Right Answer!! Good Job");
        else
            answerView.setText("      No The Answer is :      " + "\n" + "\n" + answers[currentQuestion]);

    }
}

