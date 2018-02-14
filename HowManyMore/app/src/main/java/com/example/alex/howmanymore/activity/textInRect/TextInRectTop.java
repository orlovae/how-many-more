package com.example.alex.howmanymore.activity.textInRect;

import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by alex on 08.02.18.
 */

public class TextInRectTop extends TextInRectBase {
    TextInRectTop(Rect rect, int colorRect, String text) {
        super(rect, colorRect, text);
    }

    @Override
    protected int getColorText() {
        return Color.BLACK;
    }

    @Override
    float getTextYCoordinate(float textHeight, int numberOfTextLine) {
        return super.getRect().top + 16 - numberOfTextLine * textHeight;
    }
}
