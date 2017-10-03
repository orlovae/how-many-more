package com.example.alex.howmanymore.activity.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.activity.Draw;
import com.example.alex.howmanymore.presenter.mainactivity.IMainActivity;
import com.example.alex.howmanymore.presenter.mainactivity.PresenterMainActivityImpl;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    private final String LOG_TAG = this.getClass().getSimpleName();
    private Toolbar toolbar;
    private IMainActivity presenter = new PresenterMainActivityImpl(this, this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniToolbar();

        presenter.preparationForPainting(null);
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
}
