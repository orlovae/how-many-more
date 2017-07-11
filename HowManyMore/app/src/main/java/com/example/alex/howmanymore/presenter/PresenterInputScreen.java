package com.example.alex.howmanymore.presenter;

import android.content.Context;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.activity.IInputScreenView;
import com.example.alex.howmanymore.adapter.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10.07.17.
 */

public class PresenterInputScreen implements IInputScreen {
    private IInputScreenView view;
    private Context context;

    public PresenterInputScreen(IInputScreenView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void setListCountryToView() {
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
        return sexes;
        //TODO как-то через жопу, вдруг я захочу 1 пол оставить?. М.Б. через ENUM?
    }
}
