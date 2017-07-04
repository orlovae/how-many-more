package com.example.alex.howmanymore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by alex on 02.07.17.
 */

public class Draw extends View {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private Paint mPaint = new Paint();

    private int widthScreen, heightBlackDraw, heightWhiteDraw, widthBlackLine;

    public void setWidthScreen(int widthScreen) {
        this.widthScreen = widthScreen;
    }

    public void setHeightBlackDraw(int heightBlackDraw) {
        this.heightBlackDraw = heightBlackDraw;
    }

    public void setHeightWhiteDraw(int heightWhiteDraw) {
        this.heightWhiteDraw = heightWhiteDraw;
    }

    public void setWidthBlackLine(int widthBlackLine) {
        this.widthBlackLine = widthBlackLine;
    }

    public Draw(Context context, AttributeSet attrs){
        super(context, attrs);
        Log.d(LOG_TAG, "Start constructor Draw");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(LOG_TAG, "Start onDraw");

        init(canvas);

        paintBlackRect(canvas);

        paintBlackLine(canvas);

        paintText(canvas);

    }

    private void init(Canvas canvas){
        Log.d(LOG_TAG, "Start init");
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);
    }

    private void paintBlackRect(Canvas canvas){
        Log.d(LOG_TAG, "Start paintBlackRect");
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, widthScreen, heightBlackDraw, mPaint);
        Log.d(LOG_TAG, "widthScreen = " + widthScreen);
    }

    private void paintBlackLine(Canvas canvas){
        Log.d(LOG_TAG, "Start paintBlackLine");
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, heightBlackDraw, widthBlackLine, heightBlackDraw + 20, mPaint);
    }

    private void paintText(Canvas canvas){
        Log.d(LOG_TAG, "Start paintText");
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(32);
        mPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("ПРОЖИТО", widthScreen/2, heightBlackDraw/2, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawText("ОСТАЛОСЬ", widthScreen/2, heightBlackDraw + 1 + heightWhiteDraw/2, mPaint);
    }

}
