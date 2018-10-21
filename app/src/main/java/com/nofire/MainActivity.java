package com.nofire;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import com.behkha.progresstracker.ProgressTracker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar =  findViewById(R.id.probability_progress_bar);
        ProgressTracker progressTracker = findViewById(R.id.progress_tracker);
        progressTracker.setTrackerColor(Color.BLACK);
        progressBar.setOnClickListener( v -> {
            progressBar.setProgress(progressBar.getProgress() + 5);
            progressTracker.setProgress(progressBar.getProgress());
        });

    }
}
