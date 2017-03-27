package com.example.alex.howmanymore;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private Toolbar toolbar;

    private int toolbarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniView();

        getScreenSize();

        getHeightToolbar();
    }

    private void iniView(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void getScreenSize(){
        Log.d(LOG_TAG, "Start getScreenSize");
        Point size = new Point();
        WindowManager windowManager = getWindowManager();
        windowManager.getDefaultDisplay().getSize(size);
        int width = size.x;
        int height = size.y;

        Log.d(LOG_TAG, "screen width = " + width + ", screen height = " + height);
        Log.d(LOG_TAG, "End getScreenSize");
    }

    private void getHeightToolbar(){
        Log.d(LOG_TAG, "Start getWidthToolbar");

        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                toolbarHeight = toolbar.getHeight();
                Log.d(LOG_TAG, "toolbar toolbarHeight = " + toolbarHeight);
            }
        });
        Log.d(LOG_TAG, "End getWidthToolbar");
    }
}
