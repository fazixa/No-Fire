package com.nofire.Item;

import android.view.View;
import android.widget.TextView;

import com.nofire.R;
import com.nofire.adapter.viewHolders.BaseViewHolder;

public class TweetViewHolder extends BaseViewHolder<Tweet> implements View.OnClickListener {
    private TextView tweetText;

    public TweetViewHolder(View itemView) {
        super(itemView);
        tweetText = itemView.findViewById(R.id.tweet_text);
    }

    @Override
    public void bind(Tweet item) {
        tweetText.setText(item.getText());
    }

    @Override
    public void onClick(View v) {

    }
}
