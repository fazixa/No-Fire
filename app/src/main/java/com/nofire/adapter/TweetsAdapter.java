package com.nofire.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nofire.Item.Tweet;
import com.nofire.Item.TweetViewHolder;
import com.nofire.R;
import com.nofire.adapter.viewHolders.BaseViewHolder;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter <BaseViewHolder>  {

    List<Tweet> items;

    public TweetsAdapter(List<Tweet>items){
        this.items=items;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.twit_format,parent,false);
        return new TweetViewHolder(view);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }





}
