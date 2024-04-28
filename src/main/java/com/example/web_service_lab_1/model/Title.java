package com.example.web_service_lab_1.model;

import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.Validate.*;

public class Title {
    private final String value;

    public Title(String value) throws IllegalArgumentException {
        inclusiveBetween(0, 25, value.length());
        this.value = notEmpty(value);
    }
}
