package com.example.alex.howmanymore;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getScreenSize();
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
}
