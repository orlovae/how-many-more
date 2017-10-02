package com.example.alex.howmanymore.activity.mainactivity;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.activity.Draw;
import com.example.alex.howmanymore.presenter.mainactivity.IMainActivity;
import com.example.alex.howmanymore.presenter.mainactivity.PresenterMainActivityImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private Toolbar toolbar;

    private IMainActivity presenter = new PresenterMainActivityImpl(this, this);

    private int widthScreen, heightScreen, heightToolbar, heightNotificationBar;

    private int heightAllDraw, heightBlackDraw, heightWhiteDraw, widthBlackLine;

    private float yearLifeExpectancy, yearLived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniToolbar();

        prepareInputDate();

        getScreenSize();

        getHeightToolbar(getApplicationContext());

        getHeightNotificationBar(getApplicationContext());

        prepareSizeDraw();

        initDraw();
    }

    private void prepareInputDate(){
        //Убрать метод либо в application либо в presenter
        String lifeExpectancy = "50.0";
        String birthday = "03.07.1992";
        /**это входные значения, их нужно поставить в аргументы**/

        yearLifeExpectancy = Float.parseFloat(lifeExpectancy);
        float dayLifeExpectancy = yearLifeExpectancy * Constants.ONE_YEAR;
        /**Вынести в отдельный метод prepareLifeExpectancy который будет возвращать int
         * dayLifeExpectancy**/

        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date dateTwo = null;
        Calendar toDay = GregorianCalendar.getInstance();

        try {
            dateTwo = format.parse(birthday);
            long difference = toDay.getTimeInMillis() - dateTwo.getTime();
            int daysLived = (int)(difference / (Constants.ONE_DAY_IN_MILLISECONDS));
            yearLived = daysLived/Constants.ONE_YEAR;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void getScreenSize(){
        Log.d(LOG_TAG, "Start getScreenSize");
        Point size = new Point();
        WindowManager windowManager = getWindowManager();
        windowManager.getDefaultDisplay().getSize(size);
        widthScreen = size.x;
        heightScreen = size.y;

        Log.d(LOG_TAG, "screen width = " + widthScreen + ", screen height = " + heightScreen);
        Log.d(LOG_TAG, "End getScreenSize");
    }

    private void getHeightToolbar(Context context){
        Log.d(LOG_TAG, "Start getWidthToolbar");

        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            heightToolbar = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().
                    getDisplayMetrics());
        }
        Log.d(LOG_TAG, "Toolbar height = " + heightToolbar);
    }

    private void getHeightNotificationBar(Context context){

        int resourceId = context.getResources().getIdentifier(
                Constants.NAME_NOTIFICATION_BAR_1,
                Constants.NAME_NOTIFICATION_BAR_2,
                Constants.NAME_NOTIFICATION_BAR_3);
        if (resourceId > 0) {
            heightNotificationBar = context.getResources().getDimensionPixelSize(resourceId);
        }

        Log.d(LOG_TAG, "NotificationBar height = " + heightNotificationBar);

    }

    private void prepareSizeDraw(){
        //Убрать метод либо в application либо в presenter
        heightAllDraw = heightScreen - heightNotificationBar - heightToolbar;
        heightBlackDraw = (int) ((yearLived * heightAllDraw)/yearLifeExpectancy)
                - Constants.SIZE_BLACK_LINE;
        heightWhiteDraw = heightAllDraw - heightBlackDraw
                - Constants.SIZE_FRACTIONAL_LINE;

        int percentBlackLine = (int) ((yearLived % 1) * 100);

        widthBlackLine = (percentBlackLine * widthScreen) / 100;

        Log.d(LOG_TAG, "heightAllDraw = " + heightAllDraw + "; heightBlackDraw = " +
                heightBlackDraw + "; heightWhiteDraw = " + heightWhiteDraw);

    }

    private void initDraw(){
        Draw draw = (Draw)findViewById(R.id.draw);
        draw.setWidthScreen(widthScreen);
        draw.setHeightBlackDraw(heightBlackDraw);
        draw.setHeightWhiteDraw(heightWhiteDraw);
        draw.setWidthBlackLine(widthBlackLine);
    }
}
