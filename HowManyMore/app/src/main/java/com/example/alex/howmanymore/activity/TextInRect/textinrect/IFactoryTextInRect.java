package com.example.alex.howmanymore.activity.TextInRect.textinrect;

import android.graphics.Rect;

import java.util.List;

/**
 * Created by alex on 22.01.18.
 */

public interface IFactoryTextInRect {
    TextInRectCenter createTextInRectCenter(Rect rect, int colorRect, String text);
    TextInRectBottom createTextInRectBottom(Rect rect, int colorRect, String text);
    TextInRectTop createTextInRectTop(Rect rect, int colorRect, String text);
}
