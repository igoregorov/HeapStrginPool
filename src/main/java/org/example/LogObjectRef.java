package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;


/**
 * Reference to an object, initiated this log event.
 */
@Getter
@AllArgsConstructor
public class LogObjectRef {
    private LogObjectRefCategory category;
    private Long objId;
    private String objUuid;
    private Long typeId;

    public LogObjectRef(LogObjectRefCategory category, Long objId, Long typeId) {
        this(category, objId, null, typeId);
    }

    public LogObjectRef(LogObjectRefCategory category, String objUuid) {
        this(category, (long) objUuid.hashCode(), objUuid, null);
    }

    public LogObjectRef(LogObjectRefCategory category, Long objId) {
        this(category, objId, null, null);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof LogObjectRef)) return false;
        LogObjectRef that = (LogObjectRef)other;
        return this.category.equals(that.category) &&
                (this.objId == null && that.objId == null || this.objId != null && this.objId.equals(that.objId)) &&
                (this.objUuid == null && that.objUuid == null || this.objUuid != null && this.objUuid.equals(that.objUuid)) &&
                (typeId == null || this.typeId.equals(that.typeId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, objId, objUuid, typeId);
    }
}
