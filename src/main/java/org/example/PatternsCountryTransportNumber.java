package org.example;

import lombok.Getter;

@Getter
public enum PatternsCountryTransportNumber {
    RUS_PATTERN_01 ("^[A-Я][0-9]{3}[A-Я]{2}[0-9]{2,3}$", "RUS"),
    RUS_PATTERN_02 ("^[A-Я]{2}[0-9]{6,7}$", "RUS"),
    RUS_PATTERN_03 ("^[0-9]{4}[A-Я]{2}[0-9]{2,3}$", "RUS"),
    RUS_PATTERN_04 ("^[A-Я]{1}[0-9]{3}[A-Я]{2}[0-9]{2,3}$", "RUS"),
    RUS_PATTERN_05 ("[AХУ][0-9][0-9][0-9][AХУ][AХУ][0-9][0-9][0-9]", "RUS"),
    ;

    private String regularExpression;
    private String countryCode;

    PatternsCountryTransportNumber(String regularExpression, String countryCode) {
        this.regularExpression = regularExpression;
        this.countryCode = countryCode;
    }

}
