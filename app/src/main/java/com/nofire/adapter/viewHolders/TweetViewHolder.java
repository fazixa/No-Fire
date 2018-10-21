package com.nofire.Item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nofire.R;

public class setViewHolder extends RecyclerView.ViewHolder  {
    private TextView txtname;
    public setViewHolder(View itemView) {
        super(itemView);
        txtname=(TextView)itemView.findViewById(R.id.twittext);

    }

    public TextView getTxtname() {
        return txtname;
    }
}
