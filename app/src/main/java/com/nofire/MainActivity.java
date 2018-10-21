package com.nofire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ProgressBar;
import com.nofire.Item.Tweet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CardView mActivitiesCardView;
    private Button mReportFireButton;
    private RecyclerView mActivitiesRecyclerView;
    private ArrayList<Tweet> mTweets = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivitiesCardView = findViewById(R.id.cardview_twits);
        mReportFireButton = findViewById(R.id.alertButton);

        ProgressBar progressBar =  findViewById(R.id.probability_progress_bar);
        mActivitiesCardView.setOnClickListener(v -> {
          Intent intent = new Intent(MainActivity.this,TwitActivity.class);
          MainActivity.this.startActivity(intent);
        });
        mReportFireButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,AlertActivity.class);
            MainActivity.this.startActivity(intent);
        });
        progressBar.setOnClickListener( v -> progressBar.setProgress(progressBar.getProgress() + 5) );

        mActivitiesRecyclerView = findViewById(R.id.activities_recycler_view);
        mTweets.add(new Tweet("activity one activity one activity one activity one activity one activity one activity one activity one activity one activity one vactivity one activity one vactivity one activity one activity one"));
        mTweets.add(new Tweet("activity two"));
        com.nofire.adapter.TweetsAdapter tweetsAdapter = new com.nofire.adapter.TweetsAdapter(mTweets);
        mActivitiesRecyclerView.setAdapter(tweetsAdapter);
        mActivitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

    }
}
