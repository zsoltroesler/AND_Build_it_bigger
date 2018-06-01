package com.example.android.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.android.androidlibrary.JokeDisplaying.JOKE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokeDisplayingFragment extends Fragment {

    private static final String LOG_TAG = JokeDisplayingFragment.class.getSimpleName();

    public JokeDisplayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_joke_displaying, container, false);

        final TextView jokeTextView = root.findViewById(R.id.tv_joke);
        final ProgressBar progressBar = root.findViewById(R.id.pb_loading_indicator);
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getActivity().getIntent();

        if (intent.hasExtra(JOKE_KEY)) {
            final String joke = intent.getStringExtra(JOKE_KEY);

            if (joke != null && joke.length() != 0) {
                // Delay setText(joke) in order to shown progressBar while the joke is being retrieved
                jokeTextView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jokeTextView.setVisibility(View.VISIBLE);
                        jokeTextView.setText(joke);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 3000);
            }
        }
        return root;
    }
}
