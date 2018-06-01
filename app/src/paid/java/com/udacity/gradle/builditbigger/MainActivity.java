package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.android.androidlibrary.JokeDisplaying;

import butterknife.OnClick;

import static com.example.android.androidlibrary.JokeDisplaying.JOKE_KEY;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick
    public void tellJoke(View view) {
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndPointsAsyncTaskListener() {
            @Override
            public void onResultReceived(String result) {
                if (!TextUtils.isEmpty(result)) {
                    // Put it into intent and pass it into JokeDisplaying activity
                    sendJokeIntent(result);
                } else {
                    Toast.makeText(getBaseContext(), "Error in retrieving result", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();
    }

    private void sendJokeIntent(String joke) {
        Intent jokeIntent = new Intent(this, JokeDisplaying.class);
        jokeIntent.putExtra(JOKE_KEY, joke);
        startActivity(jokeIntent);

    }
}
