package com.udacity.gradle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StageActivity extends AppCompatActivity {

    public static final String CONTENT_TO_SHOW = "com.udacity.gradle.ContentToShow";

    public static void launch(String s, Context from) {
        Intent i = new Intent();
        i.putExtra(CONTENT_TO_SHOW, s);
        i.setClass(from, StageActivity.class);
        from.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        if (getIntent().hasExtra(CONTENT_TO_SHOW)) {
            String contentStr = getIntent().getStringExtra(CONTENT_TO_SHOW);
            ((TextView)findViewById(R.id.contentToShow)).setText(contentStr);
        }
    }
}
