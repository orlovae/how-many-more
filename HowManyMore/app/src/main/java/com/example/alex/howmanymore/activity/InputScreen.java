package com.example.alex.howmanymore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.howmanymore.constants.Keys;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.contract.InputScreenContract;
import com.example.alex.howmanymore.fragments.IOnSelectedDateListener;
import com.example.alex.howmanymore.fragments.DatePickerFragment;
import com.example.alex.howmanymore.model.Country;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.presenter.InputScreenPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.example.alex.howmanymore.constants.Keys.INTENT_MODEL;

/**
 * Created by alex on 10.07.17.
 */

public class InputScreen extends AppCompatActivity implements InputScreenContract.View,
        View.OnClickListener, IOnSelectedDateListener {
    private final String TAG = this.getClass().getSimpleName();

    private TextView mTextViewBirthday;
    private Spinner mSpinnerCountry, mSpinnerSex;
    private Button mButton;

    private List<Country> mCountries;

    private List<String> mListNameCountry, mListSex;

    @Inject
    InputScreenPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);

//        App.getComponent().injectsActivity(this);

        initView();

        buttonBehavior();

        presenter.attachView(this);
        presenter.viewIsReady(getApplicationContext());

        presenter.setListSexToView();
        presenter.setListCountryToView();
        presenter.setListNameCountryToView();

        initSpinnerCountries(mCountries, mSpinnerCountry);
        spinnerItemSelected(mSpinnerCountry, mListNameCountry);

        initSpinner(mListSex, mSpinnerSex);
        spinnerItemSelected(mSpinnerSex, mListSex);
    }

    private void initView() {
        mTextViewBirthday = (TextView) findViewById(R.id.text_view_birthday);
        mSpinnerCountry = (Spinner)findViewById(R.id.spinner_country);
        mSpinnerSex = (Spinner)findViewById(R.id.spinner_sex);
        mButton = (Button)findViewById(R.id.button);
    }

    private void buttonBehavior() {
        mTextViewBirthday.setOnClickListener(this);
        mButton.setOnClickListener(this);
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
        changeDate.show(getSupportFragmentManager(), Keys.DATE_PICKER_NAME);

    }

    private void initSpinnerCountries(List<Country> countries, Spinner spinner){
        Log.d(TAG, "initSpinnerCountries: countries.size=" + countries.size());

        spinner.setSelection(presenter.getPositionSpinner());
    }

    private void initSpinner(List<String> listSex, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, listSex);
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
                                        Keys.SPINNER_COUNTRY);
                                break;
                            case R.id.spinner_sex:
                                presenter.setSpinnerItemSelected(list.get(position),
                                        Keys.SPINNER_SEX);
                                break;
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        switch (spinner.getId()) {
                            case R.id.spinner_country:
                                presenter.setSpinnerItemSelected(null, Keys.SPINNER_COUNTRY);
                                break;
                            case R.id.spinner_sex:
                                presenter.setSpinnerItemSelected(null, Keys.SPINNER_SEX);
                                break;
                        }
                    }
                });
            }
        });
    }

    @Override
    public void showListCountry(List<Country> countries) {
        mCountries = countries;
    }

    @Override
    public void showListNameCountry(List<String> listNameCountry) {
        mListNameCountry = listNameCountry;
    }

    @Override
    public void showListSex(List<String> listSex) {
        mListSex = listSex;
    }

    @Override
    public void showDateInTextView(long birthday) {
        Date date = new Date();
        date.setTime(birthday);
        SimpleDateFormat sdf = new SimpleDateFormat(Keys.DATE_FORMAT);
        mTextViewBirthday.setText(sdf.format(date));
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(INTENT_MODEL, user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onChooseDate(long dateFromDatePicker) {
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
