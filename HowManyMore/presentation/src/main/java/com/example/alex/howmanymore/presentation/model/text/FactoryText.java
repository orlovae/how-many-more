package com.example.alex.howmanymore.presentation.model.text;

import com.example.alex.howmanymore.presentation.model.User;

/**
 * Created by alex on 15.01.18.
 */

public class FactoryText implements IFactoryText {
    private User mUser;

    public FactoryText(User user) {
        mUser = user;
    }

    @Override
    public TextWhite createWhiteText(String string) {
        return new TextWhite(string, mUser);
    }

    @Override
    public TextBlack createBlackText(String string) {
        return new TextBlack(string, mUser);
    }
}
