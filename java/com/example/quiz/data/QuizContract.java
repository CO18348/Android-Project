package com.example.quiz.data;

import android.provider.BaseColumns;

public class QuizContract {
    public static class MovieEntry implements BaseColumns{
        public static final String Table_Quest = "quest";

        public static final String KEY_ID = "id";
        public static final String KEY_QUES = "question";
        public static final String KEY_ANSWER = "answer";
        public static final String KEY_OPTA = "opta";
        public static final String KEY_OPTB = "optb";
        public static final String KEY_OPTC = "optc";
    }
}
