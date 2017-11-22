package com.example.alex.howmanymore.presenter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by alex on 13.07.17.
 */

public class MainActivityPresenter extends PresenterBase<MainActivityContract.View>
        implements MainActivityContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    @Inject
    protected Context mContext;

    @Inject
    protected DatabaseAdapter mDatabaseAdapter;

    @Inject
    protected List<Country> mCountries;

    private int mWidthScreen, mHeightScreen;
    private int mHeightBlackDraw, mHeightWhiteDraw, mWidthBlackLine;
    private float mYearLifeExpectancy, mYearLived, mYearLivedPercent;

    private User mUser;

    public MainActivityPresenter(){
        App.getComponent().injectsPresenter(this);
    }

    public List<Country> getCountries() {
        return mCountries;
    }

    @Override
    public void viewIsReady(Context context) {
        mContext = context;
        mUser = getView().getUser();
        onDraw();
    }

    private void onDraw() {
        if (checkInputData()) {
            prepareOnDraw();
            getView().draw(mWidthScreen, mHeightBlackDraw, mHeightWhiteDraw, mWidthBlackLine,
                    mYearLivedPercent);
        }
    }

    private boolean checkInputData() {
        if (mUser.getBirthday() > 0 && mUser.getCountryFlag() > 0 && mUser.getSex() != null) {
            return true;
        } else {
            getView().showMessage(R.string.input_screen_toast);
            return false;
        }
    }

    private void prepareOnDraw() {
        getScreenSize();
        getYearLived(getView().getUser());
        prepareSizeDraw();
    }

    private void getYearLived(User user) {

        mYearLifeExpectancy = mDatabaseAdapter.getYearLifeExpectancy(
                getCountryNameISO(user.getCountryFlag()),
                user.getSex());
        float dayLifeExpectancy = mYearLifeExpectancy * Constants.ONE_YEAR;
        Calendar toDay = GregorianCalendar.getInstance();

        try {
            long birthday = mUser.getBirthday();
            long difference = toDay.getTimeInMillis() - birthday;
            int daysLived = (int)(difference / (Constants.ONE_DAY_IN_MILLISECONDS));
            mYearLived = daysLived/Constants.ONE_YEAR;
            mYearLivedPercent = (mYearLived / mYearLifeExpectancy) * 100;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "getYearLived: mYearLived =" + mYearLived);
        Log.d(TAG, "getYearLived: mYearLifeExpectancy = " + mYearLifeExpectancy);
    }

    private String getCountryNameISO(int countryFlag) {
        String countryNameISO = null;
        for (Country item:mCountries
                ) {
            if (item.getFlag() == countryFlag) {
                countryNameISO = item.getNameISO();
            }
        }
        return countryNameISO;
    }

    private void getScreenSize() {
        Point size = new Point();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(size);
        mWidthScreen = size.x;
        mHeightScreen = size.y;
    }

    private int getHeightToolbar(Context context){
        Log.d(TAG, "Start getWidthToolbar");

        int heightToolbar = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            heightToolbar = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().
                    getDisplayMetrics());
        }
        Log.d(TAG, "Toolbar height = " + heightToolbar);
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

        Log.d(TAG, "NotificationBar height = " + heightNotificationBar);
        return heightNotificationBar;
    }

    private void prepareSizeDraw(){
        int heightAllDraw = mHeightScreen
                - getHeightNotificationBar(mContext)
                - getHeightToolbar(mContext);
        mHeightBlackDraw = (int) ((mYearLived * heightAllDraw)/mYearLifeExpectancy)
                - Constants.SIZE_BLACK_LINE;
        mHeightWhiteDraw = heightAllDraw - mHeightBlackDraw
                - Constants.SIZE_FRACTIONAL_LINE;

        int percentBlackLine = (int) ((mYearLived % 1) * 100);

        mWidthBlackLine = (percentBlackLine * mWidthScreen) / 100;

        Log.d(TAG, "heightAllDraw = " + heightAllDraw + "; heightBlackDraw = " +
                mHeightBlackDraw + "; heightWhiteDraw = " + mHeightWhiteDraw);
    }

    @Override
    public void setBirthday(long birthday) {
        mUser.setBirthday(birthday);
        onDraw();
    }

    @Override
    public void setCountryFlag(int countryFlag) {
        mUser.setCountryFlag(countryFlag);
        onDraw();
    }

    @Override
    public void setSex(String sex) {
        mUser.setSex(sex);
        onDraw();
    }
}
