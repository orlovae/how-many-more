package com.example.alex.howmanymore;

import android.app.Notification;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.view.Window;
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

        getHeightToolbar(getApplicationContext());

        getHeightNotificationBar(getApplicationContext());
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

    private void getHeightToolbar(Context context){
        Log.d(LOG_TAG, "Start getWidthToolbar");

        int heightToolbar = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            heightToolbar = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().
                    getDisplayMetrics());
        }
        Log.d(LOG_TAG, "Toolbar height = " + heightToolbar);
    }

    private void getHeightNotificationBar(Context context){
        int heightNotificationBar = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            heightNotificationBar = context.getResources().getDimensionPixelSize(resourceId);
        }

        Log.d(LOG_TAG, "NotificationBar height = " + heightNotificationBar);

    }
}
