package com.example.alex.howmanymore.activity;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by alex on 15.12.17.
 */

public abstract class DrawRects extends View {
    private Paint mPaint = new Paint();

    private Rect mRectWhite;

    private String mTextBlack;

    public void setRectWhite(Rect rectWhite) {
        mRectWhite = rectWhite;
    }

    public void setTextBlack(String textBlack) {
        mTextBlack = textBlack;
    }

    public DrawRects(Context context) {
        super(context);
    }


}
