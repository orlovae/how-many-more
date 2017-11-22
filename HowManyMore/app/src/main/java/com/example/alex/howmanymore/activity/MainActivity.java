package com.example.alex.howmanymore.activity;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.fragments.DatePickerFragment;
import com.example.alex.howmanymore.fragments.CountryPickerFragment;
import com.example.alex.howmanymore.fragments.IOnSelectedCountryListener;
import com.example.alex.howmanymore.fragments.IOnSelectedDateListener;
import com.example.alex.howmanymore.fragments.IOnSelectedSexListener;
import com.example.alex.howmanymore.fragments.SexPickerFragment;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.alex.howmanymore.Constants.APP_PREFERENCES;
import static com.example.alex.howmanymore.Constants.APP_PREFERENCES_BIRTHDAY;
import static com.example.alex.howmanymore.Constants.APP_PREFERENCES_COUNTRY_FLAG;
import static com.example.alex.howmanymore.Constants.APP_PREFERENCES_IS_INITIAL;
import static com.example.alex.howmanymore.Constants.APP_PREFERENCES_SEX;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        IOnSelectedDateListener, IOnSelectedCountryListener, IOnSelectedSexListener {
    private final String TAG = this.getClass().getSimpleName();
    private Toolbar mToolbar;

    private MenuItem mItemCountry, mItemSex;

    @Inject
    MainActivityPresenter mPresenter;

    private SharedPreferences mSetting;

    private List<String> mListNameCountry, mListSex;

    private User mUser = new User();

    boolean isInitial = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetting = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        App.getComponent().injectsActivity(this);

        initToolbar();
        loadPreferences();

        mPresenter.attachView(this);
        mPresenter.viewIsReady(getApplicationContext());


//        initialConfiguration();
    }

    private void initialConfiguration() {
        isInitial = mSetting.getBoolean(APP_PREFERENCES_IS_INITIAL, false);
        if (!isInitial) {
            SharedPreferences.Editor editor = mSetting.edit();
            editor.putBoolean(APP_PREFERENCES_IS_INITIAL, true);
            isInitial = true;
            editor.apply();
        }
        Log.d(TAG, "initialConfiguration: " + isInitial);
    }

    private void loadPreferences() {
        if (mSetting.contains(APP_PREFERENCES_BIRTHDAY)) {
            mUser.setBirthday(mSetting.getLong(APP_PREFERENCES_BIRTHDAY, -1));
//            mPresenter.setBirthday(mUser.getBirthday());
        }

        if (mSetting.contains(APP_PREFERENCES_COUNTRY_FLAG)) {
            mUser.setCountryFlag(mSetting.getInt(APP_PREFERENCES_COUNTRY_FLAG, -1));
//            mPresenter.setCountryFlag(mUser.getCountryFlag());
        }

        if (mSetting.contains(APP_PREFERENCES_SEX)) {
            mUser.setSex(mSetting.getString(APP_PREFERENCES_SEX, null));
//            mPresenter.setSex(mUser.getSex());
        }
    }

     private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main, menu);
         mItemCountry = menu.findItem(R.id.menu_item_country);
         mItemSex = menu.findItem(R.id.menu_item_sex);

         initIconMenu();

         return true;
    }

    private void initIconMenu() {
         if (isUserNotNull()) {
             mItemCountry.setIcon(ContextCompat.getDrawable(this, mUser.getCountryFlag()));
             setIconMenuSex(mUser.getSex());
         }
    }

    private boolean isUserNotNull() {
         if (mUser.getBirthday() > 0 && mUser.getCountryFlag() > 0 && mUser.getSex() != null) {
             return true;
         } else {
             return false;
         }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_birthday:
                startDatePickerDialog();
                return true;
            case R.id.menu_item_country:
                startCountryDialog();
                return true;
            case R.id.menu_item_sex:
                startSexPickerDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startDatePickerDialog() {
        DialogFragment changeDate = new DatePickerFragment();
        if (mUser.getBirthday() > 0) {
            Bundle args = new Bundle();
            args.putLong("Birthday" , mUser.getBirthday());
            changeDate.setArguments(args);
        }
        changeDate.show(getSupportFragmentManager(), Constants.DATE_PICKER_NAME);
    }

    private void startCountryDialog() {
        CountryPickerFragment dialogCountry = new CountryPickerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("country", (ArrayList<? extends Parcelable>) mPresenter.getCountries());
        dialogCountry.setArguments(args);
        dialogCountry.show(getSupportFragmentManager(), null);
    }

    private void startSexPickerDialog() {
        SexPickerFragment dialogSex = new SexPickerFragment();
        dialogSex.show(getSupportFragmentManager(), null);
    }

    @Override
    public void draw(int widthScreen, int heightBlackDraw, int heightWhiteDraw, int widthBlackLine) {
        Draw draw = (Draw)findViewById(R.id.draw);
        draw.invalidate();
        draw.setWidthScreen(widthScreen);
        draw.setHeightBlackDraw(heightBlackDraw);
        draw.setHeightWhiteDraw(heightWhiteDraw);
        draw.setWidthBlackLine(widthBlackLine);
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public User getUser() {
        return mUser;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        savePreferences();

        mPresenter.detachView();
        if (isFinishing()) {
            mPresenter.destroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePreferences();
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = mSetting.edit();
        editor.putLong(APP_PREFERENCES_BIRTHDAY, mUser.getBirthday());
        editor.putInt(APP_PREFERENCES_COUNTRY_FLAG, mUser.getCountryFlag());
        editor.putString(APP_PREFERENCES_SEX, mUser.getSex());
        editor.apply();
    }

    @Override
    public void onChooseDate(long dateFromDatePicker) {
        mPresenter.setBirthday(dateFromDatePicker);
        mUser.setBirthday(dateFromDatePicker);
    }

    @Override
    public void onChooseCountry(int flag) {
        mPresenter.setCountryFlag(flag);
        mItemCountry.setIcon(ContextCompat.getDrawable(this, flag));
        mUser.setCountryFlag(flag);
    }

    @Override
    public void onChooseSex(String sex) {
        mPresenter.setSex(sex);
        mUser.setSex(sex);
        setIconMenuSex(sex);
    }

    private void setIconMenuSex(String sex) {
        switch (sex) {
            case Constants.SEXES:
                mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_male_female));
                break;
            case Constants.FEMALE:
                mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_female));
                break;
            case Constants.MALE:
                mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_male));
                break;
        }
    }
}
