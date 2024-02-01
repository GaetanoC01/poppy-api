package com.trafalino.poppy.util;


import org.apache.commons.text.WordUtils;

public class StringUtils {

    public static String capitalize(String toCapitalize) {
        return WordUtils.capitalizeFully(toCapitalize);
    }
}
