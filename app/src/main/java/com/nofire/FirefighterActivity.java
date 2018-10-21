package com.nofire;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nofire.Item.Report;
import com.nofire.adapter.ReportsAdapter;

import java.util.ArrayList;

public class FirefighterActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<Report> mReports = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firefighter);
        mContext = this;

        RecyclerView mReportsRecyclerView = findViewById(R.id.reports_recycler_view);
        mReports.add(new Report("fire has endangered different animals in California jungles","https://cdn.cnn.com/cnnnext/dam/assets/180727105956-04-california-wildfire-0727-large-169.jpg"));
        mReports.add(new Report("In california and In Los Angles we have seen fire rises ","https://fireco.uk/wp-content/uploads/2017/06/stop-fire-spreading-fire-safety.jpg"));
        mReports.add(new Report("Los vegas major trade center is on fire",""));
        ReportsAdapter adapter = new ReportsAdapter(mContext,mReports);
        mReportsRecyclerView.setAdapter(adapter);
        mReportsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }
}
