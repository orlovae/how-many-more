package com.example.alex.howmanymore.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.alex.howmanymore.R;


/**
 * Created by alex on 02.07.17.
 */

public class Draw extends View {
    private final String TAG = this.getClass().getSimpleName();

    private Paint mPaint = new Paint();

    private int mWidthScreen, mHeightBlackDraw, mHeightWhiteDraw, mWidthBlackLine;

    public void setWidthScreen(int widthScreen) {
        mWidthScreen = widthScreen;
    }

    public void setHeightBlackDraw(int heightBlackDraw) {
        mHeightBlackDraw = heightBlackDraw;
    }

    public void setHeightWhiteDraw(int heightWhiteDraw) {
        mHeightWhiteDraw = heightWhiteDraw;
    }

    public void setWidthBlackLine(int widthBlackLine) {
        mWidthBlackLine = widthBlackLine;
    }

    public Draw(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        init(canvas);

        paintBlackRect(canvas);

        paintBlackLine(canvas);

        paintText(canvas);
    }

    private void init(Canvas canvas){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);
    }

    private void paintBlackRect(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, mWidthScreen, mHeightBlackDraw, mPaint);
    }

    private void paintBlackLine(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, mHeightBlackDraw, mWidthBlackLine, mHeightBlackDraw + 20,
                mPaint);
    }

    private void paintText(Canvas canvas){
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(32);
        mPaint.setTextAlign(Paint.Align.CENTER);

        String textWhite = getResources().getString(R.string.draw_lived);

        String textBlack = getResources().getString(R.string.draw_remained);

        canvas.drawText(textWhite, mWidthScreen/2,
                mHeightBlackDraw/2, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawText(textBlack, mWidthScreen/2,
                mHeightBlackDraw + 1 + mHeightWhiteDraw/2, mPaint);
    }
}
