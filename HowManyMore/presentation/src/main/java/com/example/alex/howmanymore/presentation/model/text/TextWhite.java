package com.example.alex.howmanymore.presentation.model.text;

import com.example.alex.howmanymore.presentation.model.Period;
import com.example.alex.howmanymore.presentation.model.User;

/**
 * Created by alex on 15.01.18.
 */

public class TextWhite extends Text {

    public TextWhite(String string, User user) {
        super(string, user);
    }

    @Override
    float getPercent() {
        return mUser.getPercentLived();
    }

    @Override
    Period getPeriod() {
        return new Period(mBirthday, mToDay);
    }
}
