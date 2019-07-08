package com.toolShop.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/**
 * Utility class for converting string represented date to LocalDate and add
 * days to it.
 * 
 * @author tadtab
 *
 */
public class ToolShopUtility {

    /**
     * String parser for converting date with format M/d/yy to a LocalDate
     * 
     * @param date
     *            that is converted to LocalDate
     * @return LocalDate
     */
    public static LocalDate convertStringToLocalDate(String date) {

        String inputString = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");

        return LocalDate.parse(inputString, formatter);

    }

    /**
     * String parser for converting date with format yyyy-MM-dd to a LocalDate
     * 
     * @param date
     *            that is converted to LocalDate
     * @return LocalDate
     */
    public static String convertDateFormatToAnothorFormat(String date) {

        DateTimeFormatter f = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();

        LocalDate parsedDate = LocalDate.parse(date, f);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return parsedDate.format(f2);

    }

    /**
     * Adds some number of days to LocalDate
     * 
     * @param localDate
     *            date to be modified
     * @param numberOfDays
     *            number of days to be added
     * @return LocalDate
     */
    public static LocalDate addDaysToLocalDate(LocalDate localDate, int numberOfDays) {
        return localDate.plusDays(numberOfDays);
    }

}
