package com.example.alex.howmanymore.presentation.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 24.11.17.
 */

public class DayRus {
    private int mDay;
    private String mNameDay;

    public int getDay() {
        return mDay;
    }

    public String getNameDay() {
        return mNameDay;
    }

    DayRus(int day, String nameDay) {
        mDay = day;
        mNameDay = nameDay;
    }

    private static final DayRus[] DAYS_RUS = {
            new DayRus(1, "день"),
            new DayRus(2, "дня"),
            new DayRus(3, "дня"),
            new DayRus(4, "дня"),
            new DayRus(5, "дней"),
            new DayRus(6, "дней"),
            new DayRus(7, "дней"),
            new DayRus(8, "дней"),
            new DayRus(9, "дней"),
            new DayRus(10, "дней"),
            new DayRus(11, "дней"),
            new DayRus(12, "дней"),
            new DayRus(13, "дней"),
            new DayRus(14, "дней"),
            new DayRus(15, "дней"),
            new DayRus(16, "дней"),
            new DayRus(17, "дней"),
            new DayRus(18, "дней"),
            new DayRus(19, "дней"),
            new DayRus(20, "дней"),
            new DayRus(21, "день"),
            new DayRus(22, "дня"),
            new DayRus(23, "дня"),
            new DayRus(24, "дня"),
            new DayRus(25, "дней"),
            new DayRus(26, "дней"),
            new DayRus(27, "дней"),
            new DayRus(28, "дней"),
            new DayRus(29, "дней"),
            new DayRus(30, "дней"),
            new DayRus(31, "день"),
    };

    private static List<DayRus> allDaysRusList;

    public static String getNameDaysRus(int day) {
        String nameDay = null;

        if (allDaysRusList == null) {
            allDaysRusList = Arrays.asList(DAYS_RUS);
        }

        for (DayRus item:allDaysRusList
             ) {
            if (item.getDay() == day) {
                nameDay = item.getNameDay();
            }
        }

        return nameDay;
    }
}
