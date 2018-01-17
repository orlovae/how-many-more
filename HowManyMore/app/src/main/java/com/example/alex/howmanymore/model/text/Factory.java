package com.example.alex.howmanymore.model.text;

import com.example.alex.howmanymore.model.User;

/**
 * Created by alex on 15.01.18.
 */

public class Factory implements IFactory {
    private User mUser;

    public Factory(User user) {
        mUser = user;
    }

    @Override
    public WhiteText createWhiteText(String string) {
        return new WhiteText(string, mUser);
    }

    @Override
    public BlackText createBlackText(String string) {
        return new BlackText(string, mUser);
    }
}
