package com.example.alex.howmanymore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.app.App;
import com.example.alex.howmanymore.contract.MainActivityContract;
import com.example.alex.howmanymore.model.Model;
import com.example.alex.howmanymore.presenter.MainActivityPresenter;

import javax.inject.Inject;

import static com.example.alex.howmanymore.Constants.INTENT_MODEL;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private Toolbar toolbar;

    @Inject
    MainActivityPresenter presenter;
    private Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().injectsActivity(this);

        model = (Model)getIntent().getParcelableExtra(INTENT_MODEL);

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
    public Model getModel() {
        return model;
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
