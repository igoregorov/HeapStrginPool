package org.example;

import lombok.Getter;

public enum LogObjectRefCategory {
    DOCUMENT("DC"),
    TASK("TK"),
    REST_API_FUNC("RA"),
    VETDOCUMENT("VD"),
    REST_TRACE_ID("RT");

    @Getter private String code;

    LogObjectRefCategory(String code) {
        this.code = code;
    }
}
