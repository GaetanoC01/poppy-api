package com.trafalino.poppy.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MonthEncoder {

    public static Integer encode(String month) {
        Integer result = null;

        try {
            String filePath = "./src/main/resources/static/MonthsEncoded.json";
            File jsonFile = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Integer> monthMap = objectMapper.readValue(jsonFile, Map.class);
            result = monthMap.get(month);

        } catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }
}
