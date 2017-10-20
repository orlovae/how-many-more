package com.example.alex.howmanymore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.InputScreenContract;
import com.example.alex.howmanymore.fragments.IOnSelectedDateListener;
import com.example.alex.howmanymore.fragments.DatePickerFragment;
import com.example.alex.howmanymore.model.Model;
import com.example.alex.howmanymore.presenter.InputScreenPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.example.alex.howmanymore.Constants.INTENT_MODEL;

/**
 * Created by alex on 10.07.17.
 */

public class InputScreen extends AppCompatActivity implements InputScreenContract.View,
        View.OnClickListener, IOnSelectedDateListener {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private TextView textViewBirthday;
    private Spinner spinnerCountry, spinnerSex;
    private Button button;

    private List<String> listCountry, listSex;

    @Inject
    InputScreenPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);

        App.getComponent().injectsActivity(this);

        initView();

        buttonBehavior();

        presenter.attachView(this);
        presenter.viewIsReady(getApplicationContext());

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
        button = (Button)findViewById(R.id.button);
    }

    private void buttonBehavior() {
        textViewBirthday.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_birthday:
                startDatePickerDialog();
                break;
            case R.id.button:
                presenter.buttonOnClick();
                break;
        }
    }

    private void startDatePickerDialog() {
        DialogFragment changeDate = new DatePickerFragment();
        changeDate.show(getSupportFragmentManager(), Constants.DATE_PICKER_NAME);

    }

    private void initSpinner(List<String> arrayList, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(adapter);
    }

    private void spinnerItemSelected(final Spinner spinner, final List<String> list) {
//        Log.d(LOG_TAG, "start spinnerItemSelected ");
        spinner.post(new Runnable() {
            @Override
            public void run() {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                                               long id) {
//                        Log.d(LOG_TAG, "choose = " + list.get(position));
                        switch (spinner.getId()) {
                            case R.id.spinner_country:
                                presenter.setSpinnerItemSelected(list.get(position),
                                        Constants.SPINNER_COUNTRY);
                                break;
                            case R.id.spinner_sex:
                                presenter.setSpinnerItemSelected(list.get(position),
                                        Constants.SPINNER_SEX);
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        switch (spinner.getId()) {
                            case R.id.spinner_country:
                                presenter.setSpinnerItemSelected(null, Constants.SPINNER_COUNTRY);
                                break;
                            case R.id.spinner_sex:
                                presenter.setSpinnerItemSelected(null, Constants.SPINNER_SEX);
                                break;
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

    @Override
    public void showDateInTextView(long birthday) {
        Date date = new Date();
        date.setTime(birthday);
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        textViewBirthday.setText(sdf.format(date));
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextActivity(Model model) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(INTENT_MODEL, model);
        startActivity(intent);
        finish();
    }

    @Override
    public void onChoose(long dateFromDatePicker) {
        presenter.setBirthday(dateFromDatePicker);

//        Log.d(LOG_TAG, "birthday = " + birthday.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
        }
    }
}