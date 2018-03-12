package com.example.alex.howmanymore.presentation.activity.textInRect;

import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by alex on 08.02.18.
 */

public class TextInRectBottom extends TextInRectBase {
    TextInRectBottom(Rect rect, int colorRect, String text) {
        super(rect, colorRect, text);
    }

    @Override
    protected int getColorText() {
        return Color.WHITE;
    }

    @Override
    float getTextYCoordinate(float textHeight, int numberOfTextLine) {
        return super.getRect().bottom - 32 - numberOfTextLine * textHeight;
    }
}
