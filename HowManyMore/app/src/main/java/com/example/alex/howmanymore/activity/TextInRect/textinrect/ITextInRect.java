package com.example.alex.howmanymore.activity.TextInRect.textinrect;

import android.graphics.Rect;
import android.text.StaticLayout;

import java.util.List;

/**
 * Created by alex on 31.01.18.
 */

public interface ITextInRect {
    Rect getRect();
    StaticLayout getStaticLayout();
    CoordinateText getCoordinateText();
}
