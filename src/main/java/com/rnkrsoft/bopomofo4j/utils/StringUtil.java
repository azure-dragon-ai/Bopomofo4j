package com.rnkrsoft.bopomofo4j.utils;

public class StringUtil {
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
