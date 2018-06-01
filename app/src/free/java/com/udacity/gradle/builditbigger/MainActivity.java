package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.androidlibrary.JokeDisplaying;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import butterknife.OnClick;

import static com.example.android.androidlibrary.JokeDisplaying.JOKE_KEY;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,
                getString(R.string.interstitial_id));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @OnClick
    public void tellJoke(View view) {
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndPointsAsyncTaskListener() {
            @Override
            public void onResultReceived(String result) {
                if (!TextUtils.isEmpty(result)) {
                    // Put it an intent and pass it into JokeDisplaying activity
                    sendJokeIntent(result);
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d(LOG_TAG, getString(R.string.interstitial_loading));
                    }
                } else {
                    Toast.makeText(getBaseContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
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
