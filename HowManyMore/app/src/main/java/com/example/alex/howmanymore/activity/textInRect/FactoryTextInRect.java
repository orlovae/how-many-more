package com.example.alex.howmanymore.activity.textInRect;

import android.graphics.Rect;

/**
 * Created by alex on 26.01.18.
 */

public class FactoryTextInRect implements IFactoryTextInRect {

    @Override
    public TextInRectCenter createTextInRectCenter(Rect rect, int colorRect, String text) {
        return new TextInRectCenter(rect, colorRect, text);
    }

    @Override
    public TextInRectBottom createTextInRectBottom(Rect rect, int colorRect, String text) {
        return new TextInRectBottom(rect, colorRect, text);
    }

    @Override
    public TextInRectTop createTextInRectTop(Rect rect, int colorRect, String text) {
        return new TextInRectTop(rect, colorRect, text);
    }
}
