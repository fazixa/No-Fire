package com.nofire;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.behkha.progresstracker.ProgressTracker;
import com.nofire.Adapter.TwiterAdapter;
import com.nofire.Item.Twit_Item;

import java.util.ArrayList;

public class TwitActivity extends AppCompatActivity {
    public ArrayList<Twit_Item> arrayList=new ArrayList<Twit_Item>();
    private RecyclerView recycle ;
    private TwiterAdapter twitadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_twits);
        recycle = findViewById(R.id.rec_twit);

        Twit_Item twit1 = new Twit_Item();
        twit1.setText("fire fire we got here in new york");
        arrayList.add(twit1);
        Twit_Item twit2 = new Twit_Item();
        twit2.setText("we show fire in los vegas");

        twitadapter = new TwiterAdapter(arrayList);
        arrayList.add(twit2);
        recycle.setAdapter(twitadapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(TwitActivity.this);
        recycle.setLayoutManager(linearLayoutManager);
    }
}
