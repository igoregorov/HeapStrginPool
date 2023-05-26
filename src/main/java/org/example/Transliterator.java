package org.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.TransportNumberFormatException.Kind.FORMAT_NUMBER_NOT_FOUND;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Transliterator {

    public static TransportNumberContainer convertLatinToCyrrilic(String latinNumber) {
        if (latinNumber == null || latinNumber.trim().isBlank()) {
            return null;
        }

        TransportNumberContainer numberContainer = new TransportNumberContainer();
        numberContainer.setOldNumber(latinNumber);

        var replacedNumber = latinNumber.replaceAll(" ", "").toUpperCase().replaceAll("RUS", "");

        StringBuilder strBuilder = new StringBuilder();

        for (Character latinChar: replacedNumber.toCharArray()) {
            if (Character.isDigit(latinChar) ||
                    TransliterationEnum.allValues().contains(latinChar.toString())) {
                strBuilder.append(latinChar);
                continue;
            }

            try {
                strBuilder.append(TransliterationEnum.findCurrilicByLatin(String.valueOf(latinChar)).getCyrillicSymbol());
            } catch (TransportNumberFormatException e) {
                numberContainer.setEx(e);
                return numberContainer;
            }
        }

        numberContainer.setNewNumber(strBuilder.toString());
        try {
            numberContainer.setCountry(correspondFormatNumber(numberContainer.getNewNumber()));
        } catch (TransportNumberFormatException e) {
            numberContainer.setEx(e);
            return numberContainer;
        }

        return numberContainer;
    }
    public static String correspondFormatNumber(String number) throws TransportNumberFormatException {

        return Arrays.stream(PatternsCountryTransportNumber.values())
                .filter(i -> matherFormat(i, number))
                .findFirst()
                .map(PatternsCountryTransportNumber::getCountryCode)
                .orElseThrow(() -> new TransportNumberFormatException(FORMAT_NUMBER_NOT_FOUND, number));
    }

    private static Boolean matherFormat(PatternsCountryTransportNumber patternsCountry, String number) {
        Pattern pattern = Pattern.compile(patternsCountry.getRegularExpression());
        Matcher matcher = pattern.matcher(number);
        System.out.println("pattern " + pattern.toString() + " number " + number);
        //System.out.println("mather is " + matcher.find());
        //System.out.println("mather is " + matcher.find());
        return matcher.find();
    }

}
