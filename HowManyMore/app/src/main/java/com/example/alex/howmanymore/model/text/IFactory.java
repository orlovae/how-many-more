package com.example.alex.howmanymore.model.text;

/**
 * Created by alex on 15.01.18.
 */

public interface IFactory {

    WhiteText createWhiteText(String string);
    BlackText createBlackText(String string);
}
