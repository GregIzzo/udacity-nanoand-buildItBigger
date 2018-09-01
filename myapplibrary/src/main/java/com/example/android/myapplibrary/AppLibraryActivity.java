package com.example.android.myapplibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AppLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_library);
        //get joke passed in as part of intent
        Intent intent = getIntent();
        String jokeString = "No Joke Today";
        if (intent.hasExtra("joke")){
            jokeString = intent.getStringExtra("joke");
        }
        TextView jokeTextView = findViewById(R.id.tv_joke_text);
        jokeTextView.setText(jokeString);
    }
}
