package com.example.alex.howmanymore.presentation.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 24.11.17.
 */

public class YearsRus {
    private int mYear;
    private String mNameYear;

    YearsRus(int year, String nameYear) {
        mYear = year;
        mNameYear = nameYear;
    }

    public int getYear() {
        return mYear;
    }

    public String getNameYear() {
        return mNameYear;
    }

    private static final YearsRus[] YEAR_RUS = {
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

    public static String getNameYearsRus(int year) {
        String nameYear = null;

        if (allYearsRusList == null) {
            allYearsRusList = Arrays.asList(YEAR_RUS);
        }

        for (YearsRus item:allYearsRusList
             ) {
            if (item.getYear() == year) {
                nameYear = item.getNameYear();
            }
        }
        return nameYear;
    }
}
