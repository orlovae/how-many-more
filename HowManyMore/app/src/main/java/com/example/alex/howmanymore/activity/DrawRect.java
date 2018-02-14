package com.example.alex.howmanymore.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.alex.howmanymore.activity.textInRect.TextInRectBase;

import java.util.List;

/**
 * Created by alex on 15.12.17.
 */

public class DrawRect extends View implements IView {
    private Paint mPaint = new Paint();
    protected List<TextInRectBase> mTextInRectBaseList;

    public DrawRect(Context context, List<TextInRectBase> textInRectBaseList) {
        super(context);
        mTextInRectBaseList = textInRectBaseList;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!mTextInRectBaseList.isEmpty()) {
            drawTextInRect(canvas);
        }
    }

    private void drawTextInRect(Canvas canvas) {
        canvas.save();

        for (TextInRectBase item:mTextInRectBaseList
             ) {
            if (item != null) {
                drawRect(canvas, item);
                drawText(canvas, item);
                canvas.restore();
            }
        }
    }

    private void drawRect(Canvas canvas, TextInRectBase item) {
        mPaint.setColor(item.getColorRect());
        canvas.drawRect(item.getRect(), mPaint);
    }

    private void drawText(Canvas canvas, TextInRectBase item) {
        canvas.translate(item.getCoordinateText().getX(),
                item.getCoordinateText().getY());

        item.getStaticLayout().draw(canvas);
    }
}
