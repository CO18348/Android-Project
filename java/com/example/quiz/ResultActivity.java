package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        TextView t = (TextView) findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        bar.setRating(score);
        switch (score) {
            case 0:
                t.setText("You scored 0%, keep learning");
                break;
            case 1:
                t.setText("You have scored 20%, study better");
                break;
            case 2:
                t.setText("You have scored 40%, keep learning");
                break;
            case 3:
                t.setText("You have scored 60%, good attempt");
                break;
            case 4:
                t.setText("You have scored 80%,Hmmm....Maybe you have been working very hard");
                break;
            case 5:
                t.setText("Wonderful! You have scored 100%, good attempt");
                break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            Intent settingsIntent = new Intent(this, MainActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


