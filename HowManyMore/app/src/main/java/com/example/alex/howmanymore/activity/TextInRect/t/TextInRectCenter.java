package com.example.alex.howmanymore.activity.TextInRect.t;

import android.graphics.Rect;

/**
 * Created by alex on 08.02.18.
 */

class TextInRectCenter extends TextInRectBase {
    TextInRectCenter(Rect rect, int colorRect, String text) {
        super(rect, colorRect, text);
    }

    @Override
    float getTextYCoordinate(float textHeight, int numberOfTextLine) {
        return super.getRect().exactCenterY() - ((numberOfTextLine * textHeight) / 2);
    }
}
