package com.example.alex.howmanymore.presentation.presenter;

import android.graphics.Color;
import android.graphics.Rect;

import com.example.alex.howmanymore.domain.ResourceManager;
import com.example.alex.howmanymore.presentation.activity.textInRect.FactoryTextInRect;
import com.example.alex.howmanymore.presentation.activity.textInRect.IFactoryTextInRect;
import com.example.alex.howmanymore.presentation.activity.textInRect.TextInRectBase;
import com.example.alex.howmanymore.presentation.constants.Keys;
import com.example.alex.howmanymore.presentation.R;
import com.example.alex.howmanymore.presentation.mapper.CountryModelDataMapper;
import com.example.alex.howmanymore.presentation.model.CountryModel;
import com.example.alex.howmanymore.presentation.app.App;
import com.example.alex.howmanymore.presentation.contract.MainActivityContract;
import com.example.alex.howmanymore.presentation.model.text.IFactoryText;
import com.example.alex.howmanymore.presentation.model.User;
import com.example.alex.howmanymore.presentation.model.text.Text;
import com.example.alex.howmanymore.presentation.model.text.FactoryText;
import com.example.alex.howmanymore.domain.interactor.Interactor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by alex on 13.07.17.
 */

public class MainActivityPresenter extends PresenterBase<MainActivityContract.View>
        implements MainActivityContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    @Inject
    protected Interactor interactor;

    @Inject
    protected ResourceManager resourceManager;

    private List<TextInRectBase> textInRectBases;

    private int widthScreen;
    private int heightAllDraw;
    private int heightBlackRect, heightWhiteRect;

    private User user;

    private Text textLived, textRemained;

    public MainActivityPresenter(){
        App.getComponent().injectsPresenter(this);
    }

    public Collection<CountryModel> getCountries() {
        CountryModelDataMapper mapper = new CountryModelDataMapper();
        return mapper.transform(interactor.getCountryList());
    }

    @Override
    public void viewIsReady(int widthScreen, int heightAllDraw) {
        this.widthScreen = widthScreen;
        this.heightAllDraw = heightAllDraw;

        user = getView().getUser();

        prepareText();

        onDraw();
    }

    private void prepareText() {
        String resourcesTextLived = resourceManager.getString(R.string.draw_lived);
        String resourcesTextRemained = resourceManager.getString(R.string.draw_remained);

        IFactoryText factory = new FactoryText(user);

        textLived = factory.createWhiteText(resourcesTextLived);
        textRemained = factory.createBlackText(resourcesTextRemained);
    }

    private void onDraw() {
        if (checkInputData()) {
            prepareOnDraw();
            createListTextInRect();
            getView().draw(textInRectBases);
        }
    }


    private boolean checkInputData() {
        if (user.getBirthday() != 0 && user.getCountryModel() != null && user.getSex() != null) {
            return true;
        } else {
            getView().showMessage(R.string.input_screen_toast);
            return false;
        }
    }

    private void prepareOnDraw() {
        setLifeExpectancy(getView().getUser());
        getHeightBlackRect();
        getHeightWhiteRect();
    }

    private void setLifeExpectancy(User user) {
        user.getLifeExpectancy();
    }

    private void getHeightBlackRect() {
        heightBlackRect = (int) ((getYearLived() * heightAllDraw)/ user.getLifeExpectancy());
    }

    private void getHeightWhiteRect() {
        heightWhiteRect = heightAllDraw - heightBlackRect;
    }

    private float getYearLived() {
        Calendar toDay = GregorianCalendar.getInstance();
        Calendar birthday = GregorianCalendar.getInstance();
        birthday.setTimeInMillis(user.getBirthday());

        return toDay.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    }

    private List<TextInRectBase> createListTextInRect() {
        checkListTextInRect();

        double lifeLivedPercent = getLifeLivedPercent();

        TextInRectBase textInRectBaseFirst, textInRectBaseSecond, textInRectBaseThird;

        IFactoryTextInRect factory = new FactoryTextInRect();

        if (lifeLivedPercent >= Keys.HUNDRED_PERCENT) {
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, widthScreen, heightWhiteRect),
                    Color.WHITE,
                    getTextWin()
            );
            textInRectBases.add(textInRectBaseFirst);
        }
        if (lifeLivedPercent > Keys.RECT_BLACK_NOT_CONTAIN_TEXT
                & lifeLivedPercent < Keys.RECT_WHITE_NOT_CONTAIN_TEXT) {
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, widthScreen, heightBlackRect),
                    Color.BLACK,
                    textLived.getText()
            );

            textInRectBaseSecond = factory.createTextInRectCenter(
                    getRect(heightBlackRect, widthScreen, heightWhiteRect),
                    Color.WHITE,
                    textRemained.getText()
            );
            textInRectBases.add(textInRectBaseFirst);
            textInRectBases.add(textInRectBaseSecond);
        }
        if (lifeLivedPercent > Keys.RECT_WHITE_NOT_CONTAIN_TEXT) {
            if (heightWhiteRect - heightBlackRect < 18) {
                heightBlackRect = heightWhiteRect - 18;
            }
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, widthScreen, heightBlackRect),
                    Color.BLACK,
                    textLived.getText()
            );
            textInRectBaseSecond = factory.createTextInRectBottom(
                    getRect(0, widthScreen, heightBlackRect),
                    Color.TRANSPARENT,
                    textRemained.getText()
            );
            textInRectBaseThird = factory.createTextInRectCenter(
                    getRect(heightBlackRect, widthScreen, heightWhiteRect),
                    Color.WHITE,
                    ""
            );

            textInRectBases.add(textInRectBaseFirst);
            textInRectBases.add(textInRectBaseSecond);
            textInRectBases.add(textInRectBaseThird);
        }
        if (lifeLivedPercent < Keys.RECT_BLACK_NOT_CONTAIN_TEXT) {
            if (heightBlackRect < Keys.MIN_HEIGHT_RECT) {
                heightBlackRect = Keys.MIN_HEIGHT_RECT;
            }
            textInRectBaseFirst = factory.createTextInRectCenter(
                    getRect(0, widthScreen, heightBlackRect),
                    Color.BLACK,
                    ""
            );
            textInRectBaseSecond = factory.createTextInRectCenter(
                    getRect(heightBlackRect, widthScreen, heightWhiteRect),
                    Color.WHITE,
                    textRemained.getText()
            );
            textInRectBaseThird = factory.createTextInRectTop(
                    getRect(heightBlackRect, widthScreen, heightWhiteRect),
                    Color.TRANSPARENT,
                    textLived.getText()
            );
            textInRectBases.add(textInRectBaseFirst);
            textInRectBases.add(textInRectBaseSecond);
            textInRectBases.add(textInRectBaseThird);
        }
        return textInRectBases;
    }

    private void checkListTextInRect() {
        if (textInRectBases != null) {
            textInRectBases.clear();
        } else {
            textInRectBases = new ArrayList<TextInRectBase>();
        }
    }

    private double getLifeLivedPercent() {
        return 100 * (
            new BigDecimal(user.getLifeLived() / user.getLifeExpectancy())
                    .setScale(4, RoundingMode.HALF_UP).doubleValue());
    }

    private Rect getRect(int top, int right, int bottom) {
        return new Rect(0, top, right, bottom);
    }

    private String getTextWin() {
        return resourceManager.getString(R.string.draw_win)
                + System.getProperty("line.separator")
                + textLived.getText();
    }

    @Override
    public void setBirthday(long birthday) {
        user.setBirthday(birthday);
        onDraw();
    }

    @Override
    public void setCountryModel(CountryModel countryModel) {
        user.setCountryModel(countryModel);
        onDraw();
    }

    @Override
    public void setSex(String sex) {
        user.setSex(sex);
        onDraw();
    }
}
