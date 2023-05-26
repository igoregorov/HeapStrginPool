package org.example;

import lombok.Getter;
import lombok.Setter;

public class TransportNumberFormatException extends CaduceusLoggableEvent {

    @Getter @Setter private Kind kind;

    public TransportNumberFormatException(Kind kind, String... info) {
        super( kind.getDescription(),
                info,
                LogEntryCategory.TASK,
                kind.getCode().toString() );
        this.kind = kind;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public enum Kind {
        NUMBER_EXCEPTION_UNKNOWN("Ошибка при обработке номера ТС", -1001L),
        REGULAR_EXPRESSION_NOT_FOUND("Не найдено выражение %s", -1002L),
        FORMAT_NUMBER_NOT_FOUND("номер ТС не соответствует ни одному формату %s", -1003L),
        SYMBOLS_IS_BAD("введённые символы в номере ТС не соответствуют требованиям формата для РФ %s", -1004L),
        ;

        @Getter
        private final String description;
        @Getter
        private final Long code;

        Kind(String description, Long code) {
            this.description = description;
            this.code = code;
        }
    }
}
