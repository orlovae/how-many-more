package com.example.alex.howmanymore.contract;

import com.example.alex.howmanymore.activity.IView;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.presenter.IPresenter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alex on 14.10.17.
 */

public interface InputScreenContract {

    interface View extends IView {
        void showListCountry(List<Country> listCountry);
        void showListNameCountry(List<String> listNameCountry);
        void showListSex(List<String> listSex);
        void showDateInTextView(long birthday);
        void showMessage(int messageResId);
        void nextActivity(User user);
    }

    interface Presenter extends IPresenter<View> {
        void setListCountryToView();
        void setListNameCountryToView();
        void setListSexToView();
        int getPositionSpinner();
        void setSpinnerItemSelected(String itemSelected, String flag);
        void setBirthday(long birthday);
        void buttonOnClick();
    }
}
