package com.example.android.quiz;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;


public class MainActivity extends AppCompatActivity {
    Boolean[] answers = new Boolean[7]; //boolean table of if answer is correct (true)
    int score = 0;
    //all question should be answered to submit
    boolean flag = true; //true if till then all questions are answered (if false we pop a toast message and we don't let user to submit)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the submit button is clicked.
     * In order to show score, user should answer all questions and give his name
     */
    void submitted(View view) {
        Button resetButton = (Button) findViewById(R.id.scoreButton);
        CharSequence textButton = resetButton.getText();
        if (textButton == getString(R.string.resetButton)) {  //when the button is for reset just reset quiz
            Reset();
            return;
        }
        //toast initialization
        Context context = getApplicationContext();
        int duration;
        CharSequence text;
        Toast toast;
        //name variable (required) (in name the input name)
        EditText nameView = (EditText) findViewById(R.id.name);
        String name = nameView.getText().toString();
        if (name.equals("")) {
            duration = Toast.LENGTH_SHORT;
            text = getString(R.string.errorName);
            toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        flag = true; //if false, user didn't answer one or more question(s)
        //question 1 correct answer true
        RadioButton correctButton1 = (RadioButton) findViewById(R.id.trueButton1);
        RadioGroup radio1 = (RadioGroup) findViewById(R.id.radio1);
        oneCorrectAnswer(correctButton1.getId(), radio1.getCheckedRadioButtonId(), 1);
        //question 2 correct answer true
        RadioButton correctButton2 = (RadioButton) findViewById(R.id.trueButton2);
        RadioGroup radio2 = (RadioGroup) findViewById(R.id.radio2);
        oneCorrectAnswer(correctButton2.getId(), radio2.getCheckedRadioButtonId(), 2);
        //question 3 correct answer true
        RadioButton correctButton3 = (RadioButton) findViewById(R.id.trueButton3);
        RadioGroup radio3 = (RadioGroup) findViewById(R.id.radio3);
        oneCorrectAnswer(correctButton3.getId(), radio3.getCheckedRadioButtonId(), 3);
        //question 4 correct answer question4_3
        RadioButton correctButton4 = (RadioButton) findViewById(R.id.question4_3);
        RadioGroup radio4 = (RadioGroup) findViewById(R.id.radio4);
        oneCorrectAnswer(correctButton4.getId(), radio4.getCheckedRadioButtonId(), 4);
        //question 5 correct answer question5_3
        RadioButton correctButton5 = (RadioButton) findViewById(R.id.question5_3);
        RadioGroup radio5 = (RadioGroup) findViewById(R.id.radio5);
        oneCorrectAnswer(correctButton5.getId(), radio5.getCheckedRadioButtonId(), 5);
        //2 correct asnwers (ANSWER IS CORRECT ONLY IF USER FIND 2/2 )
        boolean flag2 = true; //true if user found correct answers
        int notAnswered = 0; //if 4 then not answered (count how many checkboxes are checked)
        //question 6 correct answers question 6_2, 6_4 (Jupiter Saturn)
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.question6_1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.question6_2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.question6_3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.question6_4);
        if (checkBox1.isChecked()) { //Should'nt have been checked
            flag2 = false;   //user is wrong
        } else {
            notAnswered++;  //One checkbox isnt checked
        }
        if (!(checkBox2.isChecked())) {  //Should have been checked
            flag2 = false;
            notAnswered++;
        }
        if (checkBox3.isChecked()) {
            flag2 = false;
        } else {
            notAnswered++;
        }
        if (!(checkBox4.isChecked())) {  //Should have been checked
            flag2 = false;
            notAnswered++;
        }
        if (flag2) {  //answer is correct
            answers[6 - 1] = true;
            score++;
        }
        else {
            answers[6 - 1] = false;
        }
        if (notAnswered == 4) {
            flag = false; //this question is not answered at all
            text = getString(R.string.errorMessage1);
            duration = Toast.LENGTH_SHORT;
            toast = Toast.makeText(context, text, duration);
            toast.show();
            score = 0;
        }
        flag2 = true;
        notAnswered = 0; //if 4 then not answered
        //question 7 correct asnwers question 7_1, 7_2 ()
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.question7_1);
        CheckBox checkBox6 = (CheckBox) findViewById(R.id.question7_2);
        CheckBox checkBox7 = (CheckBox) findViewById(R.id.question7_3);
        CheckBox checkBox8 = (CheckBox) findViewById(R.id.question7_4);
        if (checkBox8.isChecked()) {
            flag2 = false;
        } else {
            notAnswered++;
        }
        if (!(checkBox5.isChecked())) { //Should have been checked (1/2 correct checkbox)
            flag2 = false;
            notAnswered++;
        }
        if (checkBox7.isChecked()) {
            flag2 = false;
        } else {
            notAnswered++;
        }
        if (!(checkBox6.isChecked())) {  //Should have been checked (1/2 correct checkbox)
            flag2 = false;
            notAnswered++;
        }
        if (flag2) {
            answers[7 - 1] = true;
            score++; //User is correct at this point only if he got 2/2 correct checkbox
        }
        else {
            answers[7 - 1] = false;
        }
        if (notAnswered == 4) {
            flag = false; //this question is not answered at all
            text = getString(R.string.errorMessage1);
            duration = Toast.LENGTH_SHORT;
            toast = Toast.makeText(context, text, duration);
            toast.show();
            score = 0;
        }
        if (flag) {  //all questions are answered by user
            showResult(name);
        }


    }


    /**
     * This method resets the quiz. It clears all fields.
     */
    void Reset() {
        setContentView(R.layout.activity_main);
        score = 0;
    }

    /**
     * Shows the score in a toast message.
     *
     * @param name is the name of the User
     */
    void showResult(CharSequence name) {
        //show result
        Context context = getApplicationContext();
        CharSequence text;
        if (score > 4) {
            text = "Congratulations " + name + "!\nYou answered " + String.valueOf(score) + " out of 7 questions correctly! \n" + "Press RESET button";
        } else {
            text = name + ", You answered " + String.valueOf(score) + " out of 7 questions correctly! \n" + "Press RESET button";
        }

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER,0,0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (score > 4) {
            v.setTextColor(Color.GREEN);
        } else {
            v.setTextColor(Color.RED);
        }
        toast.show();
        //reset button appears
        Button resetButton = (Button) findViewById(R.id.scoreButton);
        resetButton.setText(getString(R.string.resetButton));
        //change question colour
        TextView myQuestion = (TextView) findViewById(R.id.question1);
        if (!answers[0]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }
        myQuestion = (TextView) findViewById(R.id.question2);
        if (!answers[1]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }
        myQuestion = (TextView) findViewById(R.id.question3);
        if (!answers[2]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }
        myQuestion = (TextView) findViewById(R.id.question4);
        if (!answers[3]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }
        myQuestion = (TextView) findViewById(R.id.question5);
        if (!answers[4]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }
        myQuestion = (TextView) findViewById(R.id.question6);
        if (!answers[5]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }
        myQuestion = (TextView) findViewById(R.id.question8);
        if (!answers[6]) {
            myQuestion.setTextColor(Color.RED);
        } else {
            myQuestion.setTextColor(Color.GREEN);
        }


    }

    /**
     * Updates score. Applicable only for questions with only one correct answer (RadioBox).
     *
     * @param correctId is the id of the correct RadioButton.
     * @param answerId  is the id of the clicked RadioButton.
     * @return total price
     */
    void oneCorrectAnswer(int correctId, int answerId, int questionNumber) {
        if (answerId == correctId) {
            score++;
            answers[questionNumber - 1] = true;
        } else if (answerId == -1) { //found a question that is not answered.
            flag = false;
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.errorMessage1);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            score = 0;
        }
        else {
            answers[questionNumber - 1] = false;
        }

    }


}
