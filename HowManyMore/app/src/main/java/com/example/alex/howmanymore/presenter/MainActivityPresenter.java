package com.example.alex.howmanymore.presenter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.example.alex.howmanymore.constants.Keys;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.text.Factory;
import com.example.alex.howmanymore.model.text.IFactory;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.model.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private int mHeightBlackRect, mHeightWhiteRect;

    private User mUser;

    private Text mWhiteText, mBlackText;

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

        String recourcesWhiteText = mContext.getResources().getString(R.string.draw_lived);
        String recourcesBlackText = mContext.getResources().getString(R.string.draw_remained);

        IFactory factory = new Factory(mUser);

        mWhiteText = factory.createWhiteText(recourcesWhiteText);
        mBlackText = factory.createBlackText(recourcesBlackText);

        onDraw();
    }

    private void onDraw() {
        if (checkInputData()) {
            prepareOnDraw();

            double isHundred = 100 * (
                    new BigDecimal(mUser.getLifeLived() / mUser.getLifeExpectancy())
                            .setScale(4, RoundingMode.HALF_UP).doubleValue());

            //Если прожито больше чем продолжительность жизни
            if (isHundred >= 100) {
                getView().drawOneRect(
                        getRect(0, mWidthScreen, mHeightWhiteRect),
                        mWhiteText.getText()
                );
            } else {
                if (isHundred > 13 & isHundred < 87) {
                    getView().drawTwoRect(
                            getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                            getRect(0, mWidthScreen, mHeightBlackRect),
                            mWhiteText.getText(),
                            mBlackText.getText()
                    );
                }
                if (isHundred > 87) {
                    if (mHeightWhiteRect - mHeightBlackRect < 18) {
                        mHeightBlackRect = mHeightWhiteRect - 18;
                    }
                    getView().drawTwoRectTextInOneRect(
                            getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                            getRect(0, mWidthScreen, mHeightBlackRect),
                            mWhiteText.getText(),
                            mBlackText.getText(),
                            87
                    );
                }
                if (isHundred < 13) {
                    if (mHeightBlackRect < 2) {
                        mHeightBlackRect = 2;
                        Log.d(TAG, "mHeightBlackRect = " + mHeightBlackRect);
                    }
                    getView().drawTwoRectTextInOneRect(
                            getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                            getRect(0, mWidthScreen, mHeightBlackRect),
                            mWhiteText.getText(),
                            mBlackText.getText(),
                            13
                    );
                }
            }
        }
    }

    private boolean checkInputData() {
        if (mUser.getBirthday() != 0 && mUser.getCountryFlag() > 0 && mUser.getSex() != null) {
            return true;
        } else {
            getView().showMessage(R.string.input_screen_toast);
            return false;
        }
    }

    private void prepareOnDraw() {
        getScreenSize();
        setYearLifeExpectancy(getView().getUser());
        prepareSizeDraw();
    }

    private void setYearLifeExpectancy(User user) {
        user.setLifeExpectancy(mDatabaseAdapter.getLifeExpectancy(
                getCountryNameISO(user.getCountryFlag()),
                user.getSex()));
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
        int heightToolbar = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            heightToolbar = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().
                    getDisplayMetrics());
        }
        return heightToolbar;
    }

    private int getHeightNotificationBar(Context context){
        int heightNotificationBar = 0;
        int resourceId = context.getResources().getIdentifier(
                Keys.NAME_NOTIFICATION_BAR_1,
                Keys.NAME_NOTIFICATION_BAR_2,
                Keys.NAME_NOTIFICATION_BAR_3);
        if (resourceId > 0) {
            heightNotificationBar = context.getResources().getDimensionPixelSize(resourceId);
        }
        return heightNotificationBar;
    }

    private void prepareSizeDraw(){
        int heightAllDraw = mHeightScreen
                - getHeightNotificationBar(mContext)
                - getHeightToolbar(mContext);
        mHeightBlackRect = (int) ((getYearLived() * heightAllDraw)/mUser.getLifeExpectancy());
        mHeightWhiteRect = heightAllDraw;
    }

    private float getYearLived() {
        Calendar toDay = GregorianCalendar.getInstance();
        Calendar birthday = GregorianCalendar.getInstance();
        birthday.setTimeInMillis(mUser.getBirthday());

        return toDay.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    private Rect getRect(int top, int right, int bottom) {
        return new Rect(0, top, right, bottom);
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
