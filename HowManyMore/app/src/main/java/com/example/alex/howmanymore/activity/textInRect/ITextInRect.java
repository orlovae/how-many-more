package com.example.alex.howmanymore.activity.textInRect;

import android.graphics.Rect;
import android.text.StaticLayout;

/**
 * Created by alex on 31.01.18.
 */

public interface ITextInRect {
    Rect getRect();
    StaticLayout getStaticLayout();
    CoordinateText getCoordinateText();
}
