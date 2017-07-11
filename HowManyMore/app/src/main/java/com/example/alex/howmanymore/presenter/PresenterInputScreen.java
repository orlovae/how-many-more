package com.example.alex.howmanymore.presenter;

import android.content.Context;
import android.util.Log;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.activity.IInputScreenView;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10.07.17.
 */

public class PresenterInputScreen implements IInputScreen {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private IInputScreenView view;
    private Context context;

    public PresenterInputScreen(IInputScreenView view, Context context) {
        Log.d(LOG_TAG, "create construtor");
        this.view = view;
        this.context = context;
    }

    @Override
    public void setListCountryToView() {
        Log.d(LOG_TAG, "create setListCountryToView");
        view.showListCountry(getListCountry());
    }

    private List<String> getListCountry() {
        return new DatabaseAdapter(context).getListCountry(Constants.RU);
    }

    @Override
    public void setListSexToView() {
        view.showListSex(getListSex());
    }

    private List<String> getListSex() {
        List<String> sexes = new ArrayList<String>();
        sexes.add(Constants.SEXES);
        sexes.add(Constants.FEMALE);
        sexes.add(Constants.MALE);
        Log.d(LOG_TAG, "sexes = " + sexes.toString());
        return sexes;
        //TODO как-то через жопу, вдруг я захочу 1 пол оставить?. М.Б. через ENUM?
    }
}
