package com.example.alex.howmanymore.presenter;

import android.content.Context;

import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;
import com.example.alex.howmanymore.contract.InputScreenContract;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.alex.howmanymore.Constants.RU;

/**
 * Created by alex on 10.07.17.
 */

public class InputScreenPresenter extends PresenterBase<InputScreenContract.View>
        implements InputScreenContract.Presenter {
    private final String LOG_TAG = this.getClass().getSimpleName();

    @Inject
    protected DatabaseAdapter mDatabaseAdapter;

    @Inject
    protected List<Country> mCountries;

    private String itemSelectedSpinnerSex = null;
    private int itemSelectedSpinnerCountry;
    private long birthday = 0;

//    public InputScreenPresenter() {
//        App.getComponent().injectsPresenter(this);
//    }

    @Override
    public void viewIsReady(Context context) {
    }

    @Override
    public void setListCountryToView() {
//        Log.d(LOG_TAG, "create setListCountryToView");
        getView().showListCountry(mCountries);
    }

    @Override
    public void setListNameCountryToView() {
        getView().showListNameCountry(getListNameCountry());
    }

    private List<String> getListNameCountry() {
        return mDatabaseAdapter.getNameCountry(RU);
    }

    @Override
    public void setListSexToView() {
        getView().showListSex(getListSex());
    }

    @Override
    public int getPositionSpinner() {
        int position = -1;
        boolean done = false;

        for (int i = 0; !done; i++) {
            if(mCountries.get(i).getNameISO().equalsIgnoreCase(Constants.RU)) {
                done = true;
                return i;
            }
        }
        return position;
        //TODO посмотреть локаль, попробовать в зависимости от языка ставить по умолчанию страну.
    }

    @Override
    public void setSpinnerItemSelected(String itemSelected, String flag) {
//        Log.d(LOG_TAG, "start setSpinnerItemSelected");
//        switch (flag) {
//            case Constants.SPINNER_SEX:
//                this.itemSelectedSpinnerSex = itemSelected;
//                break;
//            case Constants.SPINNER_COUNTRY:
//                this.itemSelectedSpinnerCountry = itemSelected;
//                break;
//        }
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
            getView().nextActivity(createNewUser(itemSelectedSpinnerCountry,
                    itemSelectedSpinnerSex, birthday));
        }
    }

    private boolean checkInputData() {
        if (itemSelectedSpinnerSex != null && itemSelectedSpinnerCountry > 0 &&
                birthday > 0) {
            return true;
        } else {
            getView().showMessage(R.string.input_screen_toast);
            return false;
        }
    }

    private User createNewUser(int countryFlag, String sex, long birthday){
        String countryName = null;
        for (Country item:mCountries
             ) {
            if (item.getFlag() == countryFlag) {
                countryName = item.getNameISO();
            }
        }
        float yearLifeExpectancy = mDatabaseAdapter.getYearLifeExpectancy(countryName, sex);
        User user = new User(yearLifeExpectancy, birthday, countryFlag, sex);
        mDatabaseAdapter.insertInTableUserRequests(user);
        return user;
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
