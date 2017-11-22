package com.example.alex.howmanymore;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by alex on 05.07.17.
 */

public class Constants {

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

    static final class YearsRus {
        private int mYear;
        private String mNameYear;

        YearsRus(int year, String nameYear) {
            mYear = year;
            mNameYear = nameYear;
        }

        static final YearsRus[] YEAR_RUS = {
                new YearsRus(1, "год"),
                new YearsRus(2, "года"),
                new YearsRus(3, "года"),
                new YearsRus(4, "года"),
                new YearsRus(5, "лет"),
                new YearsRus(6, "лет"),
                new YearsRus(7, "лет"),
                new YearsRus(8, "лет"),
                new YearsRus(9, "лет"),
                new YearsRus(10, "лет"),
                new YearsRus(11, "лет"),
                new YearsRus(12, "лет"),
                new YearsRus(13, "лет"),
                new YearsRus(14, "лет"),
                new YearsRus(15, "лет"),
                new YearsRus(16, "лет"),
                new YearsRus(17, "лет"),
                new YearsRus(18, "лет"),
                new YearsRus(19, "лет"),
                new YearsRus(20, "лет"),
                new YearsRus(21, "год"),
                new YearsRus(22, "года"),
                new YearsRus(23, "года"),
                new YearsRus(24, "года"),
                new YearsRus(25, "лет"),
                new YearsRus(26, "лет"),
                new YearsRus(27, "лет"),
                new YearsRus(28, "лет"),
                new YearsRus(29, "лет"),
                new YearsRus(30, "лет"),
                new YearsRus(31, "год"),
                new YearsRus(32, "года"),
                new YearsRus(33, "года"),
                new YearsRus(34, "года"),
                new YearsRus(35, "лет"),
                new YearsRus(36, "лет"),
                new YearsRus(37, "лет"),
                new YearsRus(38, "лет"),
                new YearsRus(39, "лет"),
                new YearsRus(40, "лет"),
                new YearsRus(41, "год"),
                new YearsRus(42, "года"),
                new YearsRus(43, "года"),
                new YearsRus(44, "года"),
                new YearsRus(45, "лет"),
                new YearsRus(46, "лет"),
                new YearsRus(47, "лет"),
                new YearsRus(48, "лет"),
                new YearsRus(49, "лет"),
                new YearsRus(50, "лет"),
                new YearsRus(51, "год"),
                new YearsRus(52, "года"),
                new YearsRus(53, "года"),
                new YearsRus(54, "года"),
                new YearsRus(55, "лет"),
                new YearsRus(56, "лет"),
                new YearsRus(57, "лет"),
                new YearsRus(58, "лет"),
                new YearsRus(59, "лет"),
                new YearsRus(60, "лет"),
                new YearsRus(61, "год"),
                new YearsRus(62, "года"),
                new YearsRus(63, "года"),
                new YearsRus(64, "года"),
                new YearsRus(65, "лет"),
                new YearsRus(66, "лет"),
                new YearsRus(67, "лет"),
                new YearsRus(68, "лет"),
                new YearsRus(69, "лет"),
                new YearsRus(70, "лет"),
                new YearsRus(71, "год"),
                new YearsRus(72, "года"),
                new YearsRus(73, "года"),
                new YearsRus(74, "года"),
                new YearsRus(75, "лет"),
                new YearsRus(76, "лет"),
                new YearsRus(77, "лет"),
                new YearsRus(78, "лет"),
                new YearsRus(79, "лет"),
                new YearsRus(80, "лет"),
                new YearsRus(81, "год"),
                new YearsRus(82, "года"),
                new YearsRus(83, "года"),
                new YearsRus(84, "года"),
                new YearsRus(85, "лет"),
                new YearsRus(86, "лет"),
                new YearsRus(87, "лет"),
                new YearsRus(88, "лет"),
                new YearsRus(89, "лет"),
                new YearsRus(90, "лет"),
                new YearsRus(91, "год"),
                new YearsRus(92, "года"),
                new YearsRus(93, "года"),
                new YearsRus(94, "года"),
                new YearsRus(95, "лет"),
                new YearsRus(96, "лет"),
                new YearsRus(97, "лет"),
                new YearsRus(98, "лет"),
                new YearsRus(99, "лет"),
                new YearsRus(100, "лет"),
                new YearsRus(101, "год"),
                new YearsRus(102, "года"),
                new YearsRus(103, "года"),
                new YearsRus(104, "года"),
                new YearsRus(105, "лет"),
                new YearsRus(106, "лет"),
                new YearsRus(107, "лет"),
                new YearsRus(108, "лет"),
                new YearsRus(109, "лет"),
                new YearsRus(110, "лет"),
                new YearsRus(111, "лет"),
                new YearsRus(112, "лет"),
                new YearsRus(113, "лет"),
                new YearsRus(114, "лет"),
                new YearsRus(115, "лет"),
                new YearsRus(116, "лет"),
                new YearsRus(117, "лет"),
                new YearsRus(118, "лет"),
                new YearsRus(119, "лет"),
                new YearsRus(120, "лет"),
        };

        private static List<YearsRus> allYearsRusList;

        public static List<YearsRus> getAllYearsRusList() {
            if (allYearsRusList == null) {
                allYearsRusList = Arrays.asList(YEAR_RUS);
            }
            return allYearsRusList;
        }
    }

    static final class Mount {
        private int mMount;
        private String mNameMount;

        Mount(int mount, String nameMount) {
            mMount = mount;
            mNameMount = nameMount;
        }

        static final Mount[] MOUNTS_RUS = {
                new Mount(1, "месяц"),
                new Mount(2, "месяца"),
                new Mount(3, "месяца"),
                new Mount(4, "месяца"),
                new Mount(5, "месяцев"),
                new Mount(6, "месяцев"),
                new Mount(7, "месяцев"),
                new Mount(8, "месяцев"),
                new Mount(9, "месяцев"),
                new Mount(10, "месяцев"),
                new Mount(11, "месяцев"),
                new Mount(12, "месяцев"),
        };

        private static List<Mount> allMountsRusList;

        public static List<Mount> getAllMountsRusList() {
            if (allMountsRusList == null) {
                allMountsRusList = Arrays.asList(MOUNTS_RUS);
            }
            return allMountsRusList;
        }
    }

    static final class Day {
        private int mDay;
        private String mNameDay;

        Day(int day, String nameDay) {
            mDay = day;
            mNameDay = nameDay;
        }

        static final Day[] DAYS_RUS = {
                new Day(1, "день"),
                new Day(2, "дня"),
                new Day(3, "дня"),
                new Day(4, "дня"),
                new Day(5, "дней"),
                new Day(6, "дней"),
                new Day(7, "дней"),
                new Day(8, "дней"),
                new Day(9, "дней"),
                new Day(10, "дней"),
                new Day(11, "дней"),
                new Day(12, "дней"),
                new Day(13, "дней"),
                new Day(14, "дней"),
                new Day(15, "дней"),
                new Day(16, "дней"),
                new Day(17, "дней"),
                new Day(18, "дней"),
                new Day(19, "дней"),
                new Day(20, "дней"),
                new Day(21, "день"),
                new Day(22, "дня"),
                new Day(23, "дня"),
                new Day(24, "дня"),
                new Day(25, "дня"),
                new Day(26, "дня"),
                new Day(27, "дня"),
                new Day(28, "дня"),
                new Day(29, "дня"),
                new Day(30, "дня"),
                new Day(31, "день"),
        };

        private static List<Day> allDaysRusList;

        public static List<Day> getAllDaysRusList() {
            if (allDaysRusList == null) {
                allDaysRusList = Arrays.asList(DAYS_RUS);
            }
            return allDaysRusList;
        }
    }
}
