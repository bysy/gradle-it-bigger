/*
 * Copyright (c) 2016. Benjamin Schulz (github.com/bysy)
 */

package com.example.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "JOKE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpJoke();
    }

    private void setUpJoke() {
        Intent i = getIntent();
        if (i==null) {
            return;
        }
        Bundle extras = i.getExtras();
        if (extras==null) {
            return;
        }
        String joke = extras.getString(JOKE_EXTRA);
        if (joke==null) {
            joke = getString(R.string.no_joke_message);
        }
        TextView jokeTextView = (TextView) findViewById(R.id.jokeTextView);
        if (jokeTextView==null) {
            return;
        }
        jokeTextView.setText(joke);
    }
}
