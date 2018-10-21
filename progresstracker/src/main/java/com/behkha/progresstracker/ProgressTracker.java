package com.behkha.progresstracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class ProgressTracker extends View {

    private Paint mPaint;
    private int trackerColor = Color.RED;
    private int width = 10;
    private int height = 60;
    private int progress = 0;

    public ProgressTracker(Context context) {
        this(context,null);
    }

    public ProgressTracker(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ProgressTracker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressTracker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        mPaint = new Paint();
    }

    public void setTrackerColor(int color){
        if (trackerColor != color){
            this.trackerColor = color;
            invalidate();
            requestLayout();
        }
    }

    public void setWidth(int width){
        this.width = width;
        invalidate();
        requestLayout();
    }

    public void setHeight(int height){
        this.height = height;
        invalidate();
        requestLayout();
    }

    public void setProgress(int progress){
        if (this.progress == progress)
            return;
        this.progress = progress;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(trackerColor);
        mPaint.setStrokeWidth(5f);
        canvas.drawRect(this.progress,0,this.width + this.progress,this.height,mPaint);
    }
}
