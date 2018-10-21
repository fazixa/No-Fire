package com.nofire.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nofire.Item.Twit_Item;
import com.nofire.Item.setViewHolder;
import com.nofire.OnTapListener;
import com.nofire.R;

import java.util.Collections;
import java.util.List;

public class TwiterAdapter extends RecyclerView.Adapter <setViewHolder>  {

    List<Twit_Item> items= Collections.emptyList();
    private OnTapListener onTapListener;
    public TwiterAdapter(List<Twit_Item>items){
        this.items=items;

    }

    @Override
    public setViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.twit_format,parent,false);
        return new setViewHolder(view);

    }
    @Override
    public void onBindViewHolder(setViewHolder holder, final int position) {
        holder.getTxtname().setText(items.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTapListener!=null){
                    onTapListener.OnTapView(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setOnTapListener(OnTapListener onTapListener){
        this.onTapListener=onTapListener;

    }




}
