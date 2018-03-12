package com.example.alex.howmanymore.presentation.activity;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alex.howmanymore.presentation.activity.textInRect.TextInRectBase;
import com.example.alex.howmanymore.presentation.constants.Keys;
import com.example.alex.howmanymore.presentation.R;
import com.example.alex.howmanymore.presentation.app.App;
import com.example.alex.howmanymore.presentation.contract.MainActivityContract;
import com.example.alex.howmanymore.presentation.fragments.DatePickerFragment;
import com.example.alex.howmanymore.presentation.fragments.CountryPickerFragment;
import com.example.alex.howmanymore.presentation.fragments.IOnSelectedCountryModelListener;
import com.example.alex.howmanymore.presentation.fragments.IOnSelectedDateListener;
import com.example.alex.howmanymore.presentation.fragments.IOnSelectedSexListener;
import com.example.alex.howmanymore.presentation.fragments.SexPickerFragment;
import com.example.alex.howmanymore.presentation.model.CountryModel;
import com.example.alex.howmanymore.presentation.model.User;
import com.example.alex.howmanymore.presentation.presenter.MainActivityPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.example.alex.howmanymore.presentation.constants.Keys.APP_PREFERENCES;
import static com.example.alex.howmanymore.presentation.constants.Keys.APP_PREFERENCES_BIRTHDAY;
import static com.example.alex.howmanymore.presentation.constants.Keys.APP_PREFERENCES_COUNTRY_FLAG;
import static com.example.alex.howmanymore.presentation.constants.Keys.APP_PREFERENCES_SEX;
import static com.example.alex.howmanymore.presentation.constants.Keys.COUNTRY_PICKER_LIST;
import static com.example.alex.howmanymore.presentation.constants.Keys.DATE_PICKER_BIRTHDAY;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        IOnSelectedDateListener, IOnSelectedCountryModelListener, IOnSelectedSexListener {
    private final String TAG = this.getClass().getSimpleName();
    private Toolbar mToolbar;

    private MenuItem mItemBirthday, mItemCountry, mItemSex;

    @Inject
    MainActivityPresenter mPresenter;

    private SharedPreferences mSetting;

    private User mUser = new User();

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
        mPresenter.viewIsReady(getWidthScreen(), getHeightAllDraw());
    }

    private int getWidthScreen() {
        return getScreenSize().x;
    }

    private int getHeightAllDraw() {
        final int heightScreen = getScreenSize().y;
        return heightScreen - getHeightNotificationBar() - getHeightToolbar();
    }

    private int getHeightNotificationBar(){
        int resourceId = getResources().getIdentifier(
                Keys.NAME_NOTIFICATION_BAR_1,
                Keys.NAME_NOTIFICATION_BAR_2,
                Keys.NAME_NOTIFICATION_BAR_3);

        return resourceId > 0 ? getResources().getDimensionPixelSize(resourceId) : 0;
    }

    private int getHeightToolbar(){
        TypedValue tv = new TypedValue();

        boolean thereIsAToolbar = getTheme().resolveAttribute(android.R.attr.actionBarSize, tv,
                true);

        return thereIsAToolbar ?
            TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics()) :
                0;
    }

    private Point getScreenSize() {
        final Point screen = new Point();
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(screen);

        return screen;
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
        args.putParcelableArrayList(COUNTRY_PICKER_LIST,
                (ArrayList<? extends Parcelable>) mPresenter.getCountries());
        dialogCountry.setArguments(args);
        dialogCountry.show(getSupportFragmentManager(), Keys.COUNTRY_PICKER_NAME);
    }

    private void startSexPickerDialog() {
        SexPickerFragment dialogSex = new SexPickerFragment();
        dialogSex.show(getSupportFragmentManager(), Keys.SEX_PICKER_NAME);
    }

    @Override
    public void draw(List<TextInRectBase> textInRectBaseList) {
        DrawRect drawRect = new DrawRect(this, textInRectBaseList);
        LinearLayout.LayoutParams lP = getLayoutParams();
        drawRect.setLayoutParams(lP);
        addViewInLayout(drawRect);
    }

    private LinearLayout.LayoutParams getLayoutParams() {
         return new LinearLayout.LayoutParams(
                 ViewGroup.LayoutParams.MATCH_PARENT,
                 ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void addViewInLayout (View view) {
         mLayout.removeAllViews();
         mLayout.addView(view);
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
         //TODO непонятно, почему презентер и модель оповещаются. Должно, что-то одно.
        mPresenter.setBirthday(dateFromDatePicker);
        mUser.setBirthday(dateFromDatePicker);
        setIconMenuBirthday(dateFromDatePicker);
    }

    @Override
    public void onChooseCountryModel(CountryModel countryModel) {
        mPresenter.setCountryModel(countryModel);
        mItemCountry.setIcon(ContextCompat.getDrawable(this, countryModel.getFlag()));
        mUser.setCountryModel(countryModel);
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
