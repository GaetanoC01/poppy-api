package com.trafalino.poppy.util;


import org.apache.commons.text.WordUtils;

public class StringUtils {

    //TODO: check that weird cases (e.g. tYBa are also capitalized to Tyba)
    public static String capitalize(String toCapitalize) {
        return WordUtils.capitalize(toCapitalize);
    }
}
