package com.example.quiz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quiz.Question;

import java.util.ArrayList;
import java.util.List;

import static com.example.quiz.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.quiz.data.QuizContract.MovieEntry.KEY_ID;
import static com.example.quiz.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.quiz.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.quiz.data.QuizContract.MovieEntry.KEY_OPTC;
import static com.example.quiz.data.QuizContract.MovieEntry.KEY_QUES;
import static com.example.quiz.data.QuizContract.MovieEntry.Table_Quest;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 2;

    private static final String DATABASE_NAME = "triviaQuiz";

    private SQLiteDatabase dbase;


    public DbHelper(Context context){super(context , DATABASE_NAME ,null , DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db){
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + Table_Quest +" ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                +" TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA +" TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestions();
    }
    private void addQuestions(){
        Question q1=new Question(" Which operator is having the right to left associativity in the following?" ,"Function Call","Addition and Subtraction","Type Cast","Type Cast");
        this.addQuestion(q1);
        Question q2=new Question("Which operator has the highest precedence?","Shift","Unary","Postfix","Postfix");
        this.addQuestion(q2);
        Question q3=new Question(" What is this operator called ?:?","Conditional","Relational","Casting Operator","Conditional");
        this.addQuestion(q3);
        Question q4=new Question("What is the use of dynamic_cast operator?","It converts the virtual base object to derived objects","It converts virtual base class to derived class","It will convert the operator based on precedence","It converts virtual base class to derived class");
        this.addQuestion(q4);
        Question q5=new Question("Android is?","NetworkInfo","GooglePlay","Linux Based","Linux Based");
        this.addQuestion(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Quest);
        onCreate(db);
    }
    public void addQuestion(Question quest){
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER,quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        dbase.insert(Table_Quest,null,values);
    }
    public List<Question> getAllQuestions(){
        List<Question> quesList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+ Table_Quest;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Question quest= new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            }while (cursor.moveToNext());
        }
        return quesList;
    }

    public int rowcount(){
        int row=0;
        String selectQuery = "SELECT * FROM "+ Table_Quest;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery,null);
        row=cursor.getCount();
        return row;
    }
}
