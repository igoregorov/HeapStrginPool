package org.example;

import lombok.Getter;

public enum LogEntryCategory {
    TASK("TK"),
    REST_API("RA"),
    REST_GUI("RG"),
    MERCURY_SERVER("MS"),
    MERCURY_CLIENT("MC"),
    JOB("JB"),
    VETDOC_UPLOAD("VD"),
    MASTER_DATA("MD"),
    MERCURY_XML("MX"),
    PUSH("PU"),
    OTHER("OR");

    @Getter private String code;

    LogEntryCategory(String code) {
        this.code = code;
    }
}
