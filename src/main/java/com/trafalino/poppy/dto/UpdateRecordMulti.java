package com.trafalino.poppy.dto;

public record UpdateRecordMulti(
        String fieldName,
        String operation,
        String value
) {}
