package com.example.chamcong.utils;

import org.joda.time.DateTime;

public class DateUtils {
    public static String getDate() {
        DateTime date = new DateTime();
        return date.toString();
    }
}
