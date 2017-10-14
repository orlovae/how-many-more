package com.example.alex.howmanymore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private Toolbar toolbar;
    private MainActivityContract.Presenter presenter = new MainActivityPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniToolbar();

        presenter.attachView(this);
        presenter.viewIsReady(getApplicationContext());
    }

     private void iniToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void draw(int widthScreen, int heightBlackDraw, int heightWhiteDraw, int widthBlackLine) {
        Draw draw = (Draw)findViewById(R.id.draw);
        draw.setWidthScreen(widthScreen);
        draw.setHeightBlackDraw(heightBlackDraw);
        draw.setHeightWhiteDraw(heightWhiteDraw);
        draw.setWidthBlackLine(widthBlackLine);
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
