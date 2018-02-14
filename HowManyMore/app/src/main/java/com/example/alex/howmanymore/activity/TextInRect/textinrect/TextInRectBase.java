package com.example.alex.howmanymore.activity.TextInRect.textinrect;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import static com.example.alex.howmanymore.constants.Keys.TEXT_SIZE;

/**
 * Created by alex on 22.01.18.
 */

public abstract class TextInRectBase implements ITextInRect{
    private Rect mRect;
    private int mColorRect;

    private String mText;
    private StaticLayout mSL;

    private CoordinateText mCoordinateText;

    public TextInRectBase(Rect rect, int colorRect, String text) {
        mRect = rect;
        mColorRect = colorRect;
        mText = text;
        mCoordinateText = new CoordinateText();
        setStaticLayout(text);
        getCoordinateText();
    }

    @Override
    public String toString() {
        return "TextInRectBase{" + "\n" +
                "mRect=" + mRect + ", \n" +
                "mColorRect=" + mColorRect + ", \n" +
                "mText=" + mText + ", \n" +
                "colorText=" + getColorText() + ", \n" +
                "mCoordinateText[x,y]=" + "[" + mCoordinateText.getX() + ", " + mCoordinateText.getY() + "]" +
                '}';
    }

    @Override
    public Rect getRect() {
        return mRect;
    }

    @Override
    public StaticLayout getStaticLayout() {
        return mSL;
    }

    @Override
    public CoordinateText getCoordinateText() {
        setCoordText();
        return mCoordinateText;
    }

    private void setCoordText() {
        float textHeight =  getTextHeight(mText);
        int numberOfTextLine = mSL.getLineCount();

        mCoordinateText.setX(getTextXCoordinate());
        mCoordinateText.setY(getTextYCoordinate(textHeight, numberOfTextLine));
    }

    protected int getColorText() {
        return mColorRect == Color.BLACK ? Color.WHITE : Color.BLACK;
    }

    public int getColorRect() {
        return mColorRect;
    }

    private float getTextXCoordinate() {
        return mRect.exactCenterX();
    }

    abstract float getTextYCoordinate(float textHeight, int numberOfTextLine);

    private float getTextHeight(String string) {
        Rect rect = new Rect();
        Paint paint = new Paint();
        paint.getTextBounds(string, 0, string.length(), rect);

        return rect.height();
    }

    private void setStaticLayout(String text) {
        TextPaint textPaint = getTextPaint();

        mSL = new StaticLayout(text, textPaint, mRect.width(),
                        Layout.Alignment.ALIGN_NORMAL, 1, 1, true);
    }

    private TextPaint getTextPaint() {
        TextPaint textPaint = new TextPaint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(getColorText());

        return textPaint;
    }
}
