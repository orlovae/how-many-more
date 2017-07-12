package com.example.alex.howmanymore.activity.inputscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.SpinnerAdapter;
import com.example.alex.howmanymore.presenter.inputscreen.IInputScreen;
import com.example.alex.howmanymore.presenter.inputscreen.presenterInputScreenImpl;

import java.util.List;

/**
 * Created by alex on 10.07.17.
 */

public class InputScreen extends AppCompatActivity implements IInputScreenView {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private TextView textViewBirthday;
    private Spinner spinnerCountry, spinnerSex;
    private List<String> listCountry, listSex;

    private IInputScreen presenter = new presenterInputScreenImpl(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);

        initView();

        presenter.setListSexToView();
        presenter.setListCountryToView();

        initSpinner(listCountry, spinnerCountry);
        spinnerItemSelected(spinnerCountry, listCountry);

        initSpinner(listSex, spinnerSex);
        spinnerItemSelected(spinnerSex, listSex);
    }

    private void initView() {
        textViewBirthday = (TextView) findViewById(R.id.text_view_birthday);
        spinnerCountry = (Spinner)findViewById(R.id.spinner_country);
        spinnerSex = (Spinner)findViewById(R.id.spinner_sex);
    }

    private void initSpinner(List<String> arrayList, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(adapter);
    }

    private void spinnerItemSelected(final Spinner spinner, final List<String> list) {
        spinner.post(new Runnable() {
            @Override
            public void run() {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SpinnerAdapter.flag = true;
                        Log.d(LOG_TAG, "choose = " + list.get(position));
                        if (spinner.getId() == spinnerCountry.getId()) {
                            presenter.setSpinnerItemSelected(list.get(position), Constants.SPINNER_COUNTRY);
                        }
                        if (spinner.getId() == spinnerSex.getId()) {
                            presenter.setSpinnerItemSelected(list.get(position), Constants.SPINNER_SEX);
                        };
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        if (spinner.getId() == spinnerCountry.getId()) {
                            presenter.setSpinnerItemSelected(null, Constants.SPINNER_COUNTRY);
                        }
                        if (spinner.getId() == spinnerSex.getId()){
                            presenter.setSpinnerItemSelected(null, Constants.SPINNER_SEX);
                        }
                    }
                });
            }
        });
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
