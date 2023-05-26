package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Custom exception for holding log information.
 */
@NoArgsConstructor
public class CaduceusLoggableEvent extends Exception {

    @Getter private String statusMessage;
    @Getter private LogEntryCategory category;
    @Getter private String code;
    @Getter private List<String> infoList;
    @Getter private String mainData;
    @Getter @Setter private Long codvId;
    @Getter @Setter private Boolean finished;
    // Should be marked as "transient" because references cannot be serialized/deserialized. AM 2018-05-18
    @Getter private transient List<LogObjectRef> objectRefs;
    @Getter private Date createdDate;

    public CaduceusLoggableEvent(String statusMessage, String[] infoList, LogEntryCategory category, String code, String mainData, Throwable throwable) {
        super(getFormattedCode(statusMessage, infoList), throwable);
        this.statusMessage = statusMessage;
        this.category = category;
        this.code = code;
        this.mainData = mainData;
        this.infoList = new ArrayList<>(Arrays.asList(infoList));
        this.objectRefs = new ArrayList<>();
        this.finished = false;
        this.createdDate = new Date();
    }
    public CaduceusLoggableEvent(String statusMessage, String[] infoList, LogEntryCategory category, String code) {
        this(statusMessage, infoList, category, code, null, null);
    }

    public CaduceusLoggableEvent(String statusMessage, String[] infoList, LogEntryCategory category, String code, Long codvId) {
        this(statusMessage, infoList, category, code, null, null);
        this.setCodvId(codvId);
    }

    public CaduceusLoggableEvent assureObjRef(LogObjectRef objectRef) {
        if (!objectRefs.contains(objectRef))
            objectRefs.add(objectRef);
        return this;
    }
    public CaduceusLoggableEvent setMainData(String mainData) {
        this.mainData = mainData;
        return this;
    }
    public CaduceusLoggableEvent addInfo(String info) {
        infoList.add(info);
        return this;
    }
    public CaduceusLoggableEvent clearInfo() {
        this.infoList.clear();
        return this;
    }
    public CaduceusLoggableEvent prependInfo(String info) {
        infoList.add(info);
        return this;
    }
    public CaduceusLoggableEvent setFinishedAndReturn(Boolean value) {
        this.finished = value;
        return this;
    }

    @Override
    public String getMessage() {
        return getFormattedCode(statusMessage, infoList.toArray(new String[infoList.size()]));
    }

    public static String getFormattedCode(String statusMessage, String[] placeholders) {
        if (statusMessage == null)
            return null;
        try {
            return String.format(statusMessage, (Object[]) placeholders);
        } catch (IllegalFormatException e) {
            return statusMessage;
        }
    }
}
