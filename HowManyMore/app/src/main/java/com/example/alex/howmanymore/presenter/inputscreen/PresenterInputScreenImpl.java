package com.example.alex.howmanymore.presenter.inputscreen;

import android.content.Context;

import com.example.alex.howmanymore.App;
import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.activity.inputscreen.IInputScreenView;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10.07.17.
 */

public class PresenterInputScreenImpl implements IInputScreen {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private IInputScreenView view;
    private DatabaseAdapter databaseAdapter = null;

    private String itemSelectedSpinnerSex = null;
    private String itemSelectedSpinnerCountry = null;
    private long birthday = 0;

    public PresenterInputScreenImpl(IInputScreenView view) {
//        Log.d(LOG_TAG, "create construtor");
        this.view = view;
        databaseAdapter = new DatabaseAdapter(App.getAppContext());
    }

    @Override
    public void setListCountryToView() {
//        Log.d(LOG_TAG, "create setListCountryToView");
        view.showListCountry(getListCountry());
    }

    private List<String> getListCountry() {
        return databaseAdapter.getListCountry(Constants.RU);
    }

    @Override
    public void setListSexToView() {
        view.showListSex(getListSex());
    }

    @Override
    public void setSpinnerItemSelected(String itemSelected, String flag) {
//        Log.d(LOG_TAG, "start setSpinnerItemSelected");
        switch (flag) {
            case Constants.SPINNER_SEX:
                this.itemSelectedSpinnerSex = itemSelected;
                break;
            case Constants.SPINNER_COUNTRY:
                this.itemSelectedSpinnerCountry = itemSelected;
                break;
        }
//        Log.d(LOG_TAG, "presenter itemSelectedSpinnerSex = " + itemSelectedSpinnerSex);
//        Log.d(LOG_TAG, "presenter itemSelectedSpinnerCountry = " + itemSelectedSpinnerCountry);
    }

    @Override
    public void setBirthday(long birthday) {
        this.birthday = birthday;
        view.showDateInTextView(birthday);
//        Log.d(LOG_TAG, "birthday = " + new Date(birthday).toString());
    }

    @Override
    public void buttonOnClick() {
        if (checkInputData()) {
            view.nextActivity();
        }
    }

    private boolean checkInputData() {
        if (itemSelectedSpinnerSex != null && itemSelectedSpinnerCountry != null &&
                birthday > 0) {
            createNewModel(itemSelectedSpinnerCountry, itemSelectedSpinnerSex, birthday);
            return true;
        } else {
            view.showToast();
            return false;
        }
    }

    private void createNewModel(String country, String sex, long birthday){
        float yearLifeExpectancy = databaseAdapter.getYearLifeExpectancy(country, sex);
        Model model = new Model(yearLifeExpectancy, birthday, country, sex);
        databaseAdapter.insertInTableUserRequests(model);
    }


    private List<String> getListSex() {
        List<String> sexes = new ArrayList<String>();
        sexes.add(Constants.SEXES);
        sexes.add(Constants.FEMALE);
        sexes.add(Constants.MALE);
//        Log.d(LOG_TAG, "sexes = " + sexes.toString());
        return sexes;
        //TODO как-то через жопу, вдруг я захочу 1 пол оставить?. М.Б. через ENUM?
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
        itemSelectedSpinnerCountry = null;
        itemSelectedSpinnerSex = null;
    }
}
