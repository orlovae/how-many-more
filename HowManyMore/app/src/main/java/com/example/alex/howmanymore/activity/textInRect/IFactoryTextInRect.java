package com.example.alex.howmanymore.activity.textInRect;

import android.graphics.Rect;

/**
 * Created by alex on 22.01.18.
 */

public interface IFactoryTextInRect {
    TextInRectCenter createTextInRectCenter(Rect rect, int colorRect, String text);
    TextInRectBottom createTextInRectBottom(Rect rect, int colorRect, String text);
    TextInRectTop createTextInRectTop(Rect rect, int colorRect, String text);
}
