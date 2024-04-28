package com.example.web_service_lab_1.model;

import java.time.Year;

import static org.apache.commons.lang3.Validate.notEmpty;

public class PublishYear {
    private final Year value;

    public PublishYear(String strValue) {
        notEmpty(strValue);
        this.value = Year.parse(strValue);

        if (this.value.isAfter(Year.now())) {
            throw new IllegalArgumentException("No time travel!");
        } else if (this.value.getValue() <= 0) {
            throw new IllegalArgumentException("Too old book!");
        }
    }
}
