package com.nofire;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nofire.Item.Tweet;
import com.nofire.network.RequestManager;

import java.util.ArrayList;

public class TwitActivity extends AppCompatActivity {

    private Context mContext;
    private static final String TAG = TwitActivity.class.getSimpleName();
    private RequestManager mRequestManager;

    private ArrayList<Tweet> mTweets = new ArrayList<>();
    private RecyclerView mTweetsRecyclerView ;
    private com.nofire.adapter.TweetsAdapter mTweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twits);
        mContext = getApplicationContext();

        mTweetsRecyclerView = findViewById(R.id.rec_twit);

        Tweet twit1 = new Tweet("fire fire we got here in new york");
        mTweets.add(twit1);
        Tweet twit2 = new Tweet("we show fire in los vegas");

        mTweetsAdapter = new com.nofire.adapter.TweetsAdapter(mTweets);
        mTweets.add(twit2);
        mTweetsRecyclerView.setAdapter(mTweetsAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(TwitActivity.this);
        mTweetsRecyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestManager != null)
            mRequestManager.cancelAll(TAG);
    }

    @Override
    protected void onDestroy() {
        if (mContext != null) mContext = null;
        if (mRequestManager != null) mRequestManager = null;
        super.onDestroy();
    }
}
