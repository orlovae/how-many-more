package com.example.alex.howmanymore.contract;

import com.example.alex.howmanymore.activity.IView;
import com.example.alex.howmanymore.activity.TextInRect.t.TextInRectBase;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.presenter.IPresenter;

import java.util.List;

/**
 * Created by alex on 14.10.17.
 */

public interface MainActivityContract {

    interface View extends IView {
        void draw(List<TextInRectBase> textInRectBaseList);
        void showMessage(int messageResId);
        User getUser();
    }

    interface Presenter extends IPresenter<View> {
        void setBirthday(long birthday);
        void setCountryFlag(int countryFlag);
        void setSex(String sex);
    }
}
