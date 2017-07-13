package com.example.alex.howmanymore.presenter.inputscreen;

import com.example.alex.howmanymore.presenter.IBasePresenter;

/**
 * Created by alex on 10.07.17.
 */

public interface IInputScreen extends IBasePresenter {
    void setListCountryToView();
    void setListSexToView();
    void setSpinnerItemSelected(String itemSelected, String flag);
    void setBirthday(long birthday);
    void buttonOnClick();
}
