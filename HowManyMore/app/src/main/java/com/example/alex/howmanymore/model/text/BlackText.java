package com.example.alex.howmanymore.model.text;

import com.example.alex.howmanymore.model.Period;
import com.example.alex.howmanymore.model.User;

/**
 * Created by alex on 15.01.18.
 */

public class BlackText extends Text {

    public BlackText(String string, User user) {
        super(string, user);
    }

    @Override
    float getPercent() {
        return 100 - mUser.getPercentLived();
    }

    @Override
    Period getPeriod() {
        return new Period(mLifeExpectancy, mBirthday);
    }
}
