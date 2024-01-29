package com.trafalino.poppy.util;

public class StringUtils {
    public static String capitalize(String toCapitalize) {
        return toCapitalize
                .substring(0, 1)
                .toUpperCase()
                + toCapitalize.substring(1);
    }
}
