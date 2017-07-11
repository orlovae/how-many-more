package com.example.alex.howmanymore.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.presenter.IInputScreen;
import com.example.alex.howmanymore.presenter.PresenterInputScreen;

import java.util.List;

/**
 * Created by alex on 10.07.17.
 */

public class InputScreen extends AppCompatActivity implements IInputScreenView {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private TextView textViewBirthday;
    private Spinner spinnerCountry, spinnerSex;
    private List<String> listCountry, listSex;

    private IInputScreen presenter = new PresenterInputScreen(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);

        initView();

        presenter.setListSexToView();
        presenter.setListCountryToView();

        initSpinner(listCountry, spinnerCountry);
        initSpinner(listSex, spinnerSex);
    }

    private void initView() {
        textViewBirthday = (TextView) findViewById(R.id.text_view_birthday);
        spinnerCountry = (Spinner)findViewById(R.id.spinner_country);
        spinnerSex = (Spinner)findViewById(R.id.spinner_sex);
    }

    private void initSpinner(List<String> arrayList, Spinner spinner){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void showListCountry(List<String> listCountry) {
        this.listCountry = listCountry;
    }

    @Override
    public void showListSex(List<String> listSex) {
        this.listSex = listSex;
    }
}
