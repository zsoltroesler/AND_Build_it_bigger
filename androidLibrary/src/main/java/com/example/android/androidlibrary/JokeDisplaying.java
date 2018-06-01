package com.example.android.androidlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JokeDisplaying extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displaying);

        setTitle(getString(R.string.title));
    }
}
