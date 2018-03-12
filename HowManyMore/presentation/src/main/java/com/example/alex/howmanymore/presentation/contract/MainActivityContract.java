package com.example.alex.howmanymore.presentation.contract;

import com.example.alex.howmanymore.presentation.activity.IView;
import com.example.alex.howmanymore.presentation.activity.textInRect.TextInRectBase;
import com.example.alex.howmanymore.presentation.model.CountryModel;
import com.example.alex.howmanymore.presentation.model.User;
import com.example.alex.howmanymore.presentation.presenter.IPresenter;

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
        void setCountryModel(CountryModel countryModel);
        void setSex(String sex);
    }
}
