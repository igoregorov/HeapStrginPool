package org.example;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Транслитерация символов транспортных средств.
 * ГОСТом для использования на знаках разрешены 12 букв кириллицы, имеющие графические аналоги в латинском алфавите
 * — А, В, Е, К, М, Н, О, Р, С, Т, У и Х.
 */
public enum TransliterationEnum {

    A( "А"),
    B("В"),
    C("С"),
    E("Е"),
    H("Н"),
    K("К"),
    M("М"),
    O("О"),
    P("Р"),
    T("Т"),
    X("Х"),
    Y("У");

    @Getter
    private String cyrillicSymbol;

    TransliterationEnum(String cyrillicSymbol) {
        this.cyrillicSymbol = cyrillicSymbol;
    }

    public static TransliterationEnum findCurrilicByLatin(String latin) throws TransportNumberFormatException {
        return Arrays.stream(TransliterationEnum.values())
                .filter(enumValue -> enumValue.name().equalsIgnoreCase(latin))
                .findFirst()
                .orElseThrow(() -> new TransportNumberFormatException(TransportNumberFormatException.Kind.SYMBOLS_IS_BAD, latin));
    }

    public static List<String> allValues() {
        return Arrays.stream(TransliterationEnum.values())
                .map(TransliterationEnum::getCyrillicSymbol)
                .toList();
    }

}
