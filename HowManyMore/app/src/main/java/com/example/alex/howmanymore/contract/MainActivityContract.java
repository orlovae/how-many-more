package com.example.alex.howmanymore.contract;

import com.example.alex.howmanymore.activity.IView;
import com.example.alex.howmanymore.model.Model;
import com.example.alex.howmanymore.presenter.IPresenter;

/**
 * Created by alex on 14.10.17.
 */

public interface MainActivityContract {

    interface View extends IView {
        void draw(int widthScreen, int heightBlackDraw, int heightWhiteDraw, int widthBlackLine);
        Model getModel();
    }

    interface Presenter extends IPresenter<View> {

    }
}
