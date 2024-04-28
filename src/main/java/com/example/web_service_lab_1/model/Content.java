package com.example.web_service_lab_1.model;

import static org.apache.commons.lang3.Validate.notEmpty;

public class Content {
    private final String value;

    public Content(String value) throws IllegalArgumentException {
        this.value = notEmpty(value);
    }
}
