package com.example.alex.howmanymore.presentation.constants;

/**
 * Created by alex on 05.07.17.
 */

public class Keys {

    //Key name sex to getSQLite
    public static final String SEXES = "sexes";
    public static final String FEMALE = "female";
    public static final String MALE = "male";

    //Key name to SharedPreferences
    public static final String APP_PREFERENCES = "mySettings";
    public static final String APP_PREFERENCES_BIRTHDAY = "birthday";
    public static final String APP_PREFERENCES_COUNTRY_FLAG = "countryFlag";
    public static final String APP_PREFERENCES_SEX = "sex";
    public static final String APP_PREFERENCES_IS_INITIAL = "isInitial";

    //Code Language
    public static final String EN = "EN";
    public static final String RU = "RU";

    /** DatePickerFragment **/
    public static final String DATE_PICKER_BIRTHDAY = "birthday";
    public static final int MAXIMUM_AGE = 125;

    /** CountryPickerFragment **/
    public static final String COUNTRY_PICKER_LIST = "countryList";

    /** TextOnDraw **/
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final float MILLIS_IN_ONE_YEAR = 365.2425f * 24 * 60 * 60 * 1000;
    public static final int DAY_IN_ONE_YEAR = 365;
    public static final int DAY_IN_ONE_LEAP_YEAR = 366;

    /** TextInRectBase **/
    public static final int TEXT_SIZE = 32;

    /** MainActivityPresenter **/
    public static final String DATE_PICKER_NAME = "datePicker";
    public static final String COUNTRY_PICKER_NAME = "countryPicker";
    public static final String SEX_PICKER_NAME = "sexPicker";
    public static final String NAME_NOTIFICATION_BAR_1 = "status_bar_height";
    public static final String NAME_NOTIFICATION_BAR_2 = "dimen";
    public static final String NAME_NOTIFICATION_BAR_3 = "android";
    public static final int MIN_HEIGHT_RECT = 2;
    public static final int HUNDRED_PERCENT = 100;
    public static final int RECT_BLACK_NOT_CONTAIN_TEXT = 13;
    public static final int RECT_WHITE_NOT_CONTAIN_TEXT = 87;
}
