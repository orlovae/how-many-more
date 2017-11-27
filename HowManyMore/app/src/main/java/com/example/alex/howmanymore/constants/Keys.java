package com.example.alex.howmanymore.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 05.07.17.
 */

public class Keys {

    //Key name sex to getSQLite
    public static final String SEXES = "sexes";
    public static final String FEMALE = "female";
    public static final String MALE = "male";

    //Key name to SharedPreferences
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_BIRTHDAY = "birthday";
    public static final String APP_PREFERENCES_COUNTRY_FLAG = "country flag";
    public static final String APP_PREFERENCES_SEX = "sex";
    public static final String APP_PREFERENCES_IS_INITIAL = "isInitial";

    //Code Language
    public static final String EN = "EN";
    public static final String RU = "RU";

    //Flag Spinner
    public static final String SPINNER_COUNTRY = "country";
    public static final String SPINNER_SEX = "sex";

    /** InputScreen **/
    public static final String DATE_SELECTED = "date";
    public static final String DATE_PICKER_NAME = "datePicker";
    public static final int DATE_SELECTED_REQUEST_CODE = 1;
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String INTENT_MODEL = "model";

    /** MainActivityPresenter **/
    public static final float ONE_YEAR = 365.2425f;
    public static final float ONE_MOUNT = 365.2425f / 12;
    public static final int ONE_DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
    public static final String NAME_NOTIFICATION_BAR_1 = "status_bar_height";
    public static final String NAME_NOTIFICATION_BAR_2 = "dimen";
    public static final String NAME_NOTIFICATION_BAR_3 = "android";
    public static final int SIZE_BLACK_LINE = 10;
    public static final int SIZE_FRACTIONAL_LINE = 20; /** это линия, дробной части прожитого года**/

    /** DBHelper **/
    public static final String DB_NAME = "HowManyMore.db";
    public static final int DB_VERSION = 1;
    public static final String DB_PATH_SDK_INT_MORE_17 = "/databases/";
    public static final String DB_PATH_SDK_INT_LESS_17 = "/data/data/";
    public static final String ERORR = "ErrorCopyingDataBase";
}