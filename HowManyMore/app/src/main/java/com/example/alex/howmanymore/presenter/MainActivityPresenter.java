package com.example.alex.howmanymore.presenter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.model.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by alex on 13.07.17.
 */

public class MainActivityPresenter extends PresenterBase<MainActivityContract.View>
        implements MainActivityContract.Presenter {
    private final String LOG_TAG = this.getClass().getSimpleName();

    @Inject
    protected Context context;

    private int widthScreen, heightScreen;
    private int heightBlackDraw, heightWhiteDraw, widthBlackLine;
    private float yearLifeExpectancy, yearLived;

    private long birthday;

    public MainActivityPresenter(){
        App.getComponent().injectsPresenter(this);
    }

    @Override
    public void viewIsReady(Context context) {
        this.context = context;
        prepareOnDraw();
        getView().draw(widthScreen, heightBlackDraw, heightWhiteDraw, widthBlackLine);
    }

    private void prepareOnDraw() {
        getScreenSize();
        getYearLived(getView().getModel());
        prepareSizeDraw();
    }

    private void getYearLived(Model model) {
        yearLifeExpectancy = model.getYearLifeExpectancy();
        float dayLifeExpectancy = yearLifeExpectancy * Constants.ONE_YEAR;
        Calendar toDay = GregorianCalendar.getInstance();

        try {
            long birthday = this.birthday;
            long difference = toDay.getTimeInMillis() - birthday;
            int daysLived = (int)(difference / (Constants.ONE_DAY_IN_MILLISECONDS));
            yearLived = daysLived/Constants.ONE_YEAR;
            Log.d(LOG_TAG, "yearLived = " + yearLived);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getScreenSize() {
        Point size = new Point();
        WindowManager windowManager = (WindowManager)context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(size);
        widthScreen = size.x;
        heightScreen = size.y;
    }

    private int getHeightToolbar(Context context){
        Log.d(LOG_TAG, "Start getWidthToolbar");

        int heightToolbar = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            heightToolbar = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().
                    getDisplayMetrics());
        }
        Log.d(LOG_TAG, "Toolbar height = " + heightToolbar);
        return heightToolbar;
    }

    private int getHeightNotificationBar(Context context){
        int heightNotificationBar = 0;
        int resourceId = context.getResources().getIdentifier(
                Constants.NAME_NOTIFICATION_BAR_1,
                Constants.NAME_NOTIFICATION_BAR_2,
                Constants.NAME_NOTIFICATION_BAR_3);
        if (resourceId > 0) {
            heightNotificationBar = context.getResources().getDimensionPixelSize(resourceId);
        }

        Log.d(LOG_TAG, "NotificationBar height = " + heightNotificationBar);
        return heightNotificationBar;
    }

    private void prepareSizeDraw(){
        int heightAllDraw = heightScreen
                - getHeightNotificationBar(context)
                - getHeightToolbar(context);
        heightBlackDraw = (int) ((yearLived * heightAllDraw)/yearLifeExpectancy)
                - Constants.SIZE_BLACK_LINE;
        heightWhiteDraw = heightAllDraw - heightBlackDraw
                - Constants.SIZE_FRACTIONAL_LINE;

        int percentBlackLine = (int) ((yearLived % 1) * 100);

        widthBlackLine = (percentBlackLine * widthScreen) / 100;

        Log.d(LOG_TAG, "heightAllDraw = " + heightAllDraw + "; heightBlackDraw = " +
                heightBlackDraw + "; heightWhiteDraw = " + heightWhiteDraw);
    }

    @Override
    public void setBirthday(long birthday) {
        this.birthday = birthday;
        prepareOnDraw();
        getView().draw(widthScreen, heightBlackDraw, heightWhiteDraw, widthBlackLine);
    }
}
