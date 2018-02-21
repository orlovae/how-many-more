package com.example.alex.howmanymore.presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.WindowManager;

import com.example.alex.howmanymore.activity.textInRect.FactoryTextInRect;
import com.example.alex.howmanymore.activity.textInRect.IFactoryTextInRect;
import com.example.alex.howmanymore.activity.textInRect.TextInRectBase;
import com.example.alex.howmanymore.constants.Keys;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.text.IFactoryText;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.model.text.Text;
import com.example.alex.howmanymore.model.text.FactoryText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import static android.content.Context.WINDOW_SERVICE;
import static com.example.alex.howmanymore.constants.Keys.HUNDRED_PERCENT;
import static com.example.alex.howmanymore.constants.Keys.MIN_HEIGHT_RECT;
import static com.example.alex.howmanymore.constants.Keys.RECT_BLACK_NOT_CONTAIN_TEXT;
import static com.example.alex.howmanymore.constants.Keys.RECT_WHITE_NOT_CONTAIN_TEXT;

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

    private List<TextInRectBase> mTextInRectList;

    private int mWidthScreen, mHeightScreen;
    private int mHeightBlackRect, mHeightWhiteRect;

    private User mUser;

    private Text mTextLived, mTextRemained;

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

        prepareText();

        onDraw();
    }

    private void prepareText() {
        String resourcesTextLived = mContext.getResources().getString(R.string.draw_lived);
        String resourcesTextRemained = mContext.getResources().getString(R.string.draw_remained);

        IFactoryText factory = new FactoryText(mUser);

        mTextLived = factory.createWhiteText(resourcesTextLived);
        mTextRemained = factory.createBlackText(resourcesTextRemained);
    }

    private void onDraw() {
        if (checkInputData()) {
            prepareOnDraw();
            createListTextInRect();
            getView().draw(mTextInRectList);
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
        setLifeExpectancy(getView().getUser());
        prepareSizeDraw();
    }

    private void getScreenSize() {
        Point size = new Point();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(size);
        mWidthScreen = size.x;
        mHeightScreen = size.y;
    }

    private void setLifeExpectancy(User user) {
        float lifeExpectancy = mDatabaseAdapter.getLifeExpectancy(
                getCountryNameISO(user.getCountryFlag()),
                user.getSex());
        user.setLifeExpectancy(lifeExpectancy);
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
    
    private void prepareSizeDraw(){
        int heightAllDraw = mHeightScreen
                - getHeightNotificationBar(mContext)
                - getHeightToolbar(mContext);
        mHeightBlackRect = (int) ((getYearLived() * heightAllDraw)/mUser.getLifeExpectancy());
        mHeightWhiteRect = heightAllDraw;
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
    private int getHeightToolbar(Context context){
        int heightToolbar = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            heightToolbar = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().
                    getDisplayMetrics());
        }
        return heightToolbar;
    }

    private float getYearLived() {
        Calendar toDay = GregorianCalendar.getInstance();
        Calendar birthday = GregorianCalendar.getInstance();
        birthday.setTimeInMillis(mUser.getBirthday());

        return toDay.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    private List<TextInRectBase> createListTextInRect() {
        checkListTextInRect();

        double lifeLivedPercent = getLifeLivedPercent();

        TextInRectBase textInRectBaseFirst, textInRectBaseSecond, textInRectBaseThird;

        IFactoryTextInRect factory = new FactoryTextInRect();

        if (lifeLivedPercent >= HUNDRED_PERCENT) {
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, mWidthScreen, mHeightWhiteRect),
                    Color.WHITE,
                    getTextWin()
            );
            mTextInRectList.add(textInRectBaseFirst);
        }
        if (lifeLivedPercent > RECT_BLACK_NOT_CONTAIN_TEXT
                & lifeLivedPercent < RECT_WHITE_NOT_CONTAIN_TEXT) {
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, mWidthScreen, mHeightBlackRect),
                    Color.BLACK,
                    mTextLived.getText()
            );

            textInRectBaseSecond = factory.createTextInRectCenter(
                    getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                    Color.WHITE,
                    mTextRemained.getText()
            );
            mTextInRectList.add(textInRectBaseFirst);
            mTextInRectList.add(textInRectBaseSecond);
        }
        if (lifeLivedPercent > RECT_WHITE_NOT_CONTAIN_TEXT) {
            if (mHeightWhiteRect - mHeightBlackRect < 18) {
                mHeightBlackRect = mHeightWhiteRect - 18;
            }
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, mWidthScreen, mHeightBlackRect),
                    Color.BLACK,
                    mTextLived.getText()
            );
            textInRectBaseSecond = factory.createTextInRectBottom(
                    getRect(0, mWidthScreen, mHeightBlackRect),
                    Color.TRANSPARENT,
                    mTextRemained.getText()
            );
            textInRectBaseThird = factory.createTextInRectCenter(
                    getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                    Color.WHITE,
                    ""
            );

            mTextInRectList.add(textInRectBaseFirst);
            mTextInRectList.add(textInRectBaseSecond);
            mTextInRectList.add(textInRectBaseThird);
        }
        if (lifeLivedPercent < RECT_BLACK_NOT_CONTAIN_TEXT) {
            if (mHeightBlackRect < MIN_HEIGHT_RECT) {
                mHeightBlackRect = MIN_HEIGHT_RECT;
            }
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, mWidthScreen, mHeightBlackRect),
                    Color.BLACK,
                    ""
            );
            textInRectBaseSecond = factory.createTextInRectCenter(
                    getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                    Color.WHITE,
                    mTextRemained.getText()
            );
            textInRectBaseThird = factory.createTextInRectTop(
                    getRect(mHeightBlackRect, mWidthScreen, mHeightWhiteRect),
                    Color.TRANSPARENT,
                    mTextLived.getText()
            );
            mTextInRectList.add(textInRectBaseFirst);
            mTextInRectList.add(textInRectBaseSecond);
            mTextInRectList.add(textInRectBaseThird);
        }
        return mTextInRectList;
    }

    private void checkListTextInRect() {
        if (mTextInRectList != null) {
            mTextInRectList.clear();
        } else mTextInRectList = new ArrayList<TextInRectBase>();
    }

    private double getLifeLivedPercent() {
        return 100 * (
            new BigDecimal(mUser.getLifeLived() / mUser.getLifeExpectancy())
                    .setScale(4, RoundingMode.HALF_UP).doubleValue());
    }

    private Rect getRect(int top, int right, int bottom) {
        return new Rect(0, top, right, bottom);
    }

    private String getTextWin() {
        return mContext.getResources().getString(R.string.draw_win)
                + System.getProperty("line.separator")
                + mTextLived.getText();
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
