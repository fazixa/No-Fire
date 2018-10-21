package com.nofire.Item;

public class Tweet {
    private String text;

    public Tweet(String text) {
        this.text = text;
    }

    public void setText(String text){
    this.text = text;
}
public String getText(){
    return text;
}
}