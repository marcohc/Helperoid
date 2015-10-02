package com.marcohc.helperoid;

import java.util.regex.Pattern;

public class StringHelper {

    private static Pattern pattern;
    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     * Validate hex with regular expression
     *
     * @param email hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean isEmailValid(final String email) {
        if (pattern == null) {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }
        return pattern.matcher(email).matches();
    }

    public static boolean isEmpty(final String text) {
        return text == null || text.isEmpty();
    }

}
