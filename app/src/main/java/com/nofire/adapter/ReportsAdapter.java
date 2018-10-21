package com.nofire.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nofire.Item.Report;
import com.nofire.R;
import com.nofire.adapter.viewHolders.BaseViewHolder;
import com.nofire.adapter.viewHolders.ReportViewHolder;

import java.util.ArrayList;

public class ReportsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private ArrayList<Report> mReports;

    public ReportsAdapter(Context mContext, ArrayList<Report> mReports) {
        this.mContext = mContext;
        this.mReports = mReports;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReportViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.firefighter_list_item, parent, false),
                mContext
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mReports.get(position));
    }

    @Override
    public int getItemCount() {
        return mReports.size();
    }
}
