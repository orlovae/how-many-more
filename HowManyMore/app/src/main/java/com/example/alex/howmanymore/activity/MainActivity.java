package com.example.alex.howmanymore.activity;

import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alex.howmanymore.Constants;
import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.fragments.DatePickerFragment;
import com.example.alex.howmanymore.fragments.DialogCountryFragment;
import com.example.alex.howmanymore.fragments.IOnSelectedCountryListener;
import com.example.alex.howmanymore.fragments.IOnSelectedDateListener;
import com.example.alex.howmanymore.model.User;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.alex.howmanymore.Constants.INTENT_MODEL;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        IOnSelectedDateListener, IOnSelectedCountryListener {
    private final String TAG = this.getClass().getSimpleName();
    private Toolbar mToolbar;

    private MenuItem mItemCountry;

    @Inject
    MainActivityPresenter mPresenter;

    private List<String> mListNameCountry, mListSex;

    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().injectsActivity(this);

        mUser = (User) getIntent().getParcelableExtra(INTENT_MODEL);

        iniToolbar();

        mPresenter.attachView(this);
        mPresenter.viewIsReady(getApplicationContext());
    }

     private void iniToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main, menu);
         mItemCountry = menu.findItem(R.id.menu_item_country);
         return true;
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

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startDatePickerDialog() {
        DialogFragment changeDate = new DatePickerFragment();
        changeDate.show(getSupportFragmentManager(), Constants.DATE_PICKER_NAME);
    }

    private void startCountryDialog() {
        DialogCountryFragment dialogCountry = new DialogCountryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("country", (ArrayList<? extends Parcelable>) mPresenter.getCountries());
        dialogCountry.setArguments(args);
        dialogCountry.show(getSupportFragmentManager(), null);
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
    public User getUser() {
        return mUser;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        if (isFinishing()) {
            mPresenter.destroy();
        }
    }

    @Override
    public void onChooseDate(long dateFromDatePicker) {
        mPresenter.setBirthday(dateFromDatePicker);
    }

    @Override
    public void onChooseCountry(int flag) {
        mItemCountry.setIcon(ContextCompat.getDrawable(this, flag));
    }
}
