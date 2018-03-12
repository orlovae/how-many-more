package com.example.alex.howmanymore.presentation.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 24.11.17.
 */

public class MountRus {
    private int mMount;
    private String mNameMount;

    MountRus(int mount, String nameMount) {
        mMount = mount;
        mNameMount = nameMount;
    }

    public int getMount() {
        return mMount;
    }

    public String getNameMount() {
        return mNameMount;
    }

    private static final MountRus[] MOUNTS_RUS = {
            new MountRus(1, "месяц"),
            new MountRus(2, "месяца"),
            new MountRus(3, "месяца"),
            new MountRus(4, "месяца"),
            new MountRus(5, "месяцев"),
            new MountRus(6, "месяцев"),
            new MountRus(7, "месяцев"),
            new MountRus(8, "месяцев"),
            new MountRus(9, "месяцев"),
            new MountRus(10, "месяцев"),
            new MountRus(11, "месяцев"),
            new MountRus(12, "месяцев"),
    };

    private static List<MountRus> allMountsRusList;

    public static String getNameMountsRus(int mount) {
        String nameMount = null;

        if (allMountsRusList == null) {
            allMountsRusList = Arrays.asList(MOUNTS_RUS);
        }

        for (MountRus item: allMountsRusList
             ) {
            if (item.getMount() == mount) {
                nameMount = item.getNameMount();
            }
        }

        return nameMount;
    }
}
