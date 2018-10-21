package com.nofire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nofire.adapter.TweetsAdapter;
import com.nofire.Item.Tweet;

import java.util.ArrayList;

public class TwitActivity extends AppCompatActivity {
    public ArrayList<Tweet> arrayList=new ArrayList<>();
    private RecyclerView recycle ;
    private TweetsAdapter twitadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twits);
        recycle = findViewById(R.id.rec_twit);

        Tweet twit1 = new Tweet("fire fire we got here in new york");
        arrayList.add(twit1);
        Tweet twit2 = new Tweet("we show fire in los vegas");

        twitadapter = new TweetsAdapter(arrayList);
        arrayList.add(twit2);
        recycle.setAdapter(twitadapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(TwitActivity.this);
        recycle.setLayoutManager(linearLayoutManager);
    }
}
