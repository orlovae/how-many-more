package com.example.alex.howmanymore.contract;

import android.graphics.Rect;

import com.example.alex.howmanymore.activity.IView;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.presenter.IPresenter;

/**
 * Created by alex on 14.10.17.
 */

public interface MainActivityContract {

    interface View extends IView {
        void draw(Rect rectWhite, Rect rectBlack, String textWhite, String textBlack);
        void showMessage(int messageResId);
        User getUser();
    }

    interface Presenter extends IPresenter<View> {
        void setBirthday(long birthday);
        void setCountryFlag(int countryFlag);
        void setSex(String sex);
    }
}
