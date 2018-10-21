package com.nofire.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nofire.Item.Tweet;
import com.nofire.R;
import com.nofire.adapter.viewHolders.BaseViewHolder;
import com.nofire.adapter.viewHolders.TweetViewHolder;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter <BaseViewHolder>  {

    private List<Tweet> mTweets;

    public TweetsAdapter(List<Tweet> mTweets){
        this.mTweets = mTweets;

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.twit_format,parent,false);
        return new TweetViewHolder(view);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        holder.bind(mTweets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }





}
