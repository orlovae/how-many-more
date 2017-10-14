package com.example.alex.howmanymore.presenter;

import android.content.Context;

import com.example.alex.howmanymore.App;
import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.contract.InputScreenContract;
import com.example.alex.howmanymore.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10.07.17.
 */

public class InputScreenPresenter extends PresenterBase<InputScreenContract.View>
        implements InputScreenContract.Presenter {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private DatabaseAdapter databaseAdapter = null;

    private String itemSelectedSpinnerSex = null;
    private String itemSelectedSpinnerCountry = null;
    private long birthday = 0;

    public InputScreenPresenter() {
        databaseAdapter = new DatabaseAdapter(App.getAppContext());
    }

    @Override
    public void viewIsReady(Context context) {

    }

    @Override
    public void setListCountryToView() {
//        Log.d(LOG_TAG, "create setListCountryToView");
        getView().showListCountry(getListCountry());
    }

    private List<String> getListCountry() {
        return databaseAdapter.getListCountry(Constants.RU);
    }

    @Override
    public void setListSexToView() {
        getView().showListSex(getListSex());
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
        getView().showDateInTextView(birthday);
//        Log.d(LOG_TAG, "birthday = " + new Date(birthday).toString());
    }

    @Override
    public void buttonOnClick() {
        if (checkInputData()) {
            getView().nextActivity();
        }
    }

    private boolean checkInputData() {
        if (itemSelectedSpinnerSex != null && itemSelectedSpinnerCountry != null &&
                birthday > 0) {
            createNewModel(itemSelectedSpinnerCountry, itemSelectedSpinnerSex, birthday);
            return true;
        } else {
            getView().showToast();
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

}
