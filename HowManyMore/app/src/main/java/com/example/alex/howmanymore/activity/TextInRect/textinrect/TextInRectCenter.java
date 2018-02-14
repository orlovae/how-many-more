package com.example.alex.howmanymore.activity.TextInRect.textinrect;

import android.graphics.Rect;

/**
 * Created by alex on 08.02.18.
 */

public class TextInRectCenter extends TextInRectBase {
    public TextInRectCenter(Rect rect, int colorRect, String text) {
        super(rect, colorRect, text);
    }

    @Override
    float getTextYCoordinate(float textHeight, int numberOfTextLine) {
        return super.getRect().exactCenterY() - ((numberOfTextLine * textHeight) / 2);
    }
}
