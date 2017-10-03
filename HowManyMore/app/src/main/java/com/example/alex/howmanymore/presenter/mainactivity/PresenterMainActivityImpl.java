package com.example.alex.howmanymore.presenter.mainactivity;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.activity.mainactivity.IMainActivityView;
import com.example.alex.howmanymore.model.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by alex on 13.07.17.
 */

public class PresenterMainActivityImpl implements IMainActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private IMainActivityView view;
    private Context context;

    private int widthScreen, heightScreen;
    private int heightBlackDraw, heightWhiteDraw, widthBlackLine;
    private float yearLifeExpectancy, yearLived;

    public PresenterMainActivityImpl(IMainActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void preparationForPainting(Model model) {
        getScreenSize();
        getYearLived(model);
        prepareSizeDraw();
        view.draw(widthScreen, heightBlackDraw, heightWhiteDraw, widthBlackLine);
    }

    private void getYearLived(Model model) {
        String lifeExpectancy = "50.0";
        String birthday = "03.07.1992";
        /**это входные значения, их нужно поставить в аргументы**/

        yearLifeExpectancy = Float.parseFloat(lifeExpectancy);
        float dayLifeExpectancy = yearLifeExpectancy * Constants.ONE_YEAR;
        /**Вынести в отдельный метод prepareLifeExpectancy который будет возвращать int
         * dayLifeExpectancy**/

        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
        Calendar toDay = GregorianCalendar.getInstance();

        try {
            Date dateTwo = format.parse(birthday);
            long difference = toDay.getTimeInMillis() - dateTwo.getTime();
            int daysLived = (int)(difference / (Constants.ONE_DAY_IN_MILLISECONDS));
            yearLived = daysLived/Constants.ONE_YEAR;
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
    public void detachView() {
        view = null;
        context = null;
    }

    @Override
    public void destroy() {
    }
}
