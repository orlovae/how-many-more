package com.example.alex.howmanymore.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static com.example.alex.howmanymore.constants.Keys.BLACK;
import static com.example.alex.howmanymore.constants.Keys.WHITE;


/**
 * Created by alex on 02.07.17.
 */

public class DrawTwoRect extends View {
    private final String TAG = this.getClass().getSimpleName();

    private Paint mPaint = new Paint();

    private Rect mRectWhite, mRectBlack;

    private String mTextWhite, mTextBlack;

    public void setRectWhite(Rect rectWhite) {
        mRectWhite = rectWhite;
    }

    public void setRectBlack(Rect rectBlack) {
        mRectBlack = rectBlack;
    }

    public void setTextWhite(String textWhite) {
        mTextWhite = textWhite;
    }

    public void setTextBlack(String textBlack) {
        mTextBlack = textBlack;
    }

    public DrawTwoRect(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        init(canvas);

        if (mRectBlack != null && mRectWhite != null) {
            paintBlackRect(canvas);
        }

        if (mTextWhite != null && mTextBlack != null) {
            drawText(canvas, WHITE, mTextWhite, mRectBlack);
            drawText(canvas, BLACK, mTextBlack, mRectWhite);
        }
    }

    private void init(Canvas canvas){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);
    }

    private void paintBlackRect(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(mRectBlack, mPaint);
    }

    private void drawText(Canvas canvas, String key, String text, Rect rect){
        TextPaint textPaint = new TextPaint();

        switch (key) {
            case BLACK:
                textPaint.setColor(Color.BLACK);
                break;
            case WHITE:
                textPaint.setColor(Color.WHITE);
                break;
        }

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(32);
        textPaint.setTextAlign(Paint.Align.CENTER);

        StaticLayout sL = new StaticLayout(text, textPaint, rect.width(),
                Layout.Alignment.ALIGN_NORMAL, 1, 1, true);

        canvas.save();

        float textHeight = getTextHeight(text, textPaint);
        int numberOfTextLine = sL.getLineCount();
        float textXCoordinate = rect.exactCenterX();

        float textYCoordinate = rect.exactCenterY() - ((numberOfTextLine * textHeight) / 2);

        canvas.translate(textXCoordinate, textYCoordinate);
        sL.draw(canvas);
        canvas.restore();
    }

    private float getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);

        return rect.height();
    }
}
