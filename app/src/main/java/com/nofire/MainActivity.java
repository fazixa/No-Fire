package com.nofire;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;

import com.behkha.progresstracker.ProgressTracker;

public class MainActivity extends AppCompatActivity {
    private CardView cardview;
    private Button alertButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        cardview = findViewById(R.id.cardview_twits);
        alertButton = findViewById(R.id.alertButton);



        ProgressBar progressBar =  findViewById(R.id.probability_progress_bar);
        ProgressTracker progressTracker = findViewById(R.id.progress_tracker);
        progressTracker.setTrackerColor(Color.BLACK);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this,TwitActivity.class);
              MainActivity.this.startActivity(intent);
            }
        });
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlertActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        progressBar.setOnClickListener( v -> {
            progressBar.setProgress(progressBar.getProgress() + 5);
            progressTracker.setProgress(progressBar.getProgress());
        });

    }
}
