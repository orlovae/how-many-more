package com.example.alex.howmanymore.activity;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alex.howmanymore.constants.Keys;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import static com.example.alex.howmanymore.constants.Keys.APP_PREFERENCES;
import static com.example.alex.howmanymore.constants.Keys.APP_PREFERENCES_BIRTHDAY;
import static com.example.alex.howmanymore.constants.Keys.APP_PREFERENCES_COUNTRY_FLAG;
import static com.example.alex.howmanymore.constants.Keys.APP_PREFERENCES_IS_INITIAL;
import static com.example.alex.howmanymore.constants.Keys.APP_PREFERENCES_SEX;
import static com.example.alex.howmanymore.constants.Keys.COUNTRY_PICKER_LIST;
import static com.example.alex.howmanymore.constants.Keys.DATE_PICKER_BIRTHDAY;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        IOnSelectedDateListener, IOnSelectedCountryListener, IOnSelectedSexListener {
    private final String TAG = this.getClass().getSimpleName();
    private Toolbar mToolbar;

    private MenuItem mItemBirthday, mItemCountry, mItemSex;

    @Inject
    MainActivityPresenter mPresenter;

    private SharedPreferences mSetting;

    private User mUser = new User();

    boolean isInitial = false;

    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSetting = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        setContentView(R.layout.activity_main);

        mLayout = (LinearLayout) findViewById(R.id.linear_layout);

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
        }

        if (mSetting.contains(APP_PREFERENCES_COUNTRY_FLAG)) {
            mUser.setCountryFlag(mSetting.getInt(APP_PREFERENCES_COUNTRY_FLAG, -1));
        }

        if (mSetting.contains(APP_PREFERENCES_SEX)) {
            mUser.setSex(mSetting.getString(APP_PREFERENCES_SEX, null));
        }
    }

     private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main, menu);
         mItemBirthday = menu.findItem(R.id.menu_item_birthday);
         mItemCountry = menu.findItem(R.id.menu_item_country);
         mItemSex = menu.findItem(R.id.menu_item_sex);

         initIconMenu();

         return true;
    }

    private void initIconMenu() {
         if (isUserNotNull()) {
             setIconMenuBirthday(mUser.getBirthday());
             mItemCountry.setIcon(ContextCompat.getDrawable(this, mUser.getCountryFlag()));
             setIconMenuSex(mUser.getSex());
         } else {
             mItemBirthday.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_cake_black_24dp));
             mItemCountry.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_country_black_24dp));
             mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_male_female));
         }
    }

    private boolean isUserNotNull() {
         if (mUser.getBirthday() != 0 && mUser.getCountryFlag() > 0 && mUser.getSex() != null) {
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

        if (mUser.getBirthday() != 0) {
            Bundle args = new Bundle();
            args.putLong(DATE_PICKER_BIRTHDAY, mUser.getBirthday());
            changeDate.setArguments(args);
        }
        changeDate.show(getSupportFragmentManager(), Keys.DATE_PICKER_NAME);
    }

    private void startCountryDialog() {
        CountryPickerFragment dialogCountry = new CountryPickerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(COUNTRY_PICKER_LIST, (ArrayList<? extends Parcelable>) mPresenter.getCountries());
        dialogCountry.setArguments(args);
        dialogCountry.show(getSupportFragmentManager(), Keys.COUNTRY_PICKER_NAME);
    }

    private void startSexPickerDialog() {
        SexPickerFragment dialogSex = new SexPickerFragment();
        dialogSex.show(getSupportFragmentManager(), Keys.SEX_PICKER_NAME);
    }

    @Override
    public void drawTwoRectTextInOneRect(Rect rectWhite, Rect rectBlack, String textWhite,
                                         String textBlack, int key) {
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        DrawTwoRectTextInOneRect drawTwoRectTextInOneRect = new DrawTwoRectTextInOneRect(this);
        drawTwoRectTextInOneRect.invalidate();
        drawTwoRectTextInOneRect.setRectWhite(rectWhite);
        drawTwoRectTextInOneRect.setRectBlack(rectBlack);
        drawTwoRectTextInOneRect.setTextWhite(textWhite);
        drawTwoRectTextInOneRect.setTextBlack(textBlack);
        drawTwoRectTextInOneRect.setKey(key);

        drawTwoRectTextInOneRect.setLayoutParams(lP);
        mLayout.removeAllViews();
        mLayout.addView(drawTwoRectTextInOneRect);
    }

    @Override
    public void drawTwoRect(Rect rectWhite, Rect rectBlack, String textWhite, String textBlack) {
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        DrawTwoRectTextInTwoRect drawTwoRectTextInTwoRect = new DrawTwoRectTextInTwoRect(this);
        drawTwoRectTextInTwoRect.invalidate();
        drawTwoRectTextInTwoRect.setRectWhite(rectWhite);
        drawTwoRectTextInTwoRect.setRectBlack(rectBlack);
        drawTwoRectTextInTwoRect.setTextWhite(textWhite);
        drawTwoRectTextInTwoRect.setTextBlack(textBlack);

        drawTwoRectTextInTwoRect.setLayoutParams(lP);
        mLayout.removeAllViews();
        mLayout.addView(drawTwoRectTextInTwoRect);
    }

    @Override
    public void drawOneRect(Rect rectWhite, String textBlack) {
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        DrawOneRect drawOneRect = new DrawOneRect(this);
        drawOneRect.invalidate();
        drawOneRect.setRectWhite(rectWhite);
        drawOneRect.setTextBlack(textBlack);

        drawOneRect.setLayoutParams(lP);
        mLayout.removeAllViews();
        mLayout.addView(drawOneRect);
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

    @Override
    protected void onResume() {
        super.onResume();
        loadPreferences();
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
        setIconMenuBirthday(dateFromDatePicker);
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

    private void setIconMenuBirthday(long birthday) {
        Date date = new Date();
        date.setTime(birthday);
        SimpleDateFormat sdf = new SimpleDateFormat(Keys.DATE_FORMAT);
        mItemBirthday.setTitle(sdf.format(date));
    }

    private void setIconMenuSex(String sex) {
        switch (sex) {
            case Keys.SEXES:
                mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_male_female));
                break;
            case Keys.FEMALE:
                mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_female));
                break;
            case Keys.MALE:
                mItemSex.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_human_male));
                break;
        }
    }
}
