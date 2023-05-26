package org.example;

import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by admin on 13.05.2017.
 */
public class Utils {

    static public final String RUSSIA_GUID = "74a3cbb1-56fa-94f3-ab3f-e8db4940d96b";
    static public final long MAX_MERCURY_LIST_SIZE = 200;
    static public final int MAX_TEXT_FIELD_SIZE = 2000;

    static public <T> T nvl(Object test, T ifNull) {
        return test != null ? (T) test : ifNull;
    }

    static public String getStackTraceExtract(Throwable e) {
        return e != null && e.getStackTrace() != null && e.getStackTrace().length > 0
                ? ", stack trace is: [" + e.getStackTrace()[0].toString() + "...]"
                : "";
    }

    static public int defaultFloatingRoundScale() {
        return 3;
    }

    static public RoundingMode defaultFloatingRoundMode() {
        return RoundingMode.HALF_UP;
    }

    static public boolean isMercuryTest() {
        String mercuryInstance = System.getProperty("ru.x5.caduceus.mercury.instance", "pilot");
        return !mercuryInstance.equals("production");
    }

    static public String prepareTextForDbStorage(String text) {
        if (text == null)
            return null;
        if (text.length() > MAX_TEXT_FIELD_SIZE) {
            String processedText = text.trim().replaceAll("\\s+", " ");
            return processedText.length() > MAX_TEXT_FIELD_SIZE ? processedText.substring(0, MAX_TEXT_FIELD_SIZE) : processedText;
        }
        return text;
    }

    public static String limitingJoin(List<String> stringsToJoin, String delimiter, int lengthLimit) {
        if (stringsToJoin.isEmpty())
            return "";

        if (stringsToJoin.get(0).length() > lengthLimit)
            return stringsToJoin.get(0).substring(0, lengthLimit - 1);

        StringJoiner sj = new StringJoiner(delimiter);
        for (String string : stringsToJoin) {
            if (sj.length() + string.length() > lengthLimit)
                break;
            sj.add(string);
        }
        return sj.toString();
    }

    public static <T, U> U applyIfNotNull(T obj, Function<T, U> function) {
        return applyIfNotNull(obj, function, null);
    }

    public static <T, U> U applyIfNotNull(T obj, Function<T, U> function, U defaultValue) {
        if (obj == null) {
            return defaultValue;
        } else {
            return function.apply(obj);
        }
    }

    /**
     * @return predicate that allows you to filter distinct items in collection by Key
     * <p>
     * Usage: persons.stream().filter(distinctByKey(Person::getName))
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static boolean checkUrl(String url) {
        try {
            URI uri = new URI(url);

            if ("mailto".equals(uri.getScheme())) {
                return false;
            }
            return uri.getHost() != null;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}
