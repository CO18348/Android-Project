package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.data.DbHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda,rdb,rdc;
    Button butNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        DbHelper db=new DbHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                if (grp.getCheckedRadioButtonId() == -1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Select an option!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    grp.clearCheck();
                    Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
                    if (currentQ.getANSWER().contentEquals(answer.getText())) {
                        score++;
                        Log.d("score", "Your Score" + score);
                    }
                    if (qid < 5) {
                        currentQ = quesList.get(qid);
                        setQuestionView();
                    } else {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score", score);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    private void setQuestionView(){
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }


}
