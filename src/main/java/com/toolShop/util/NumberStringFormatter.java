package com.toolShop.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Utility class for adding currency and percent sign 
 * @author tadtab
 *
 */
public class NumberStringFormatter {

    /**
     * Adds currency symbol $ to the amount specified
     * @param currencyAmount string representation of the formated currency
     * @return
     */
    public static String addCurrencySign(BigDecimal currencyAmount) {
        return NumberFormat.getCurrencyInstance().format(currencyAmount);
    }

    /**
     * Removes the currency sign and returns the currency amount
     * @param str string to be reformatted
     * @return BigDecimal 
     */
    public static BigDecimal convertStringToBigDecimal(String str) {
        String dailyChargeAmount = str.substring(1);

        return new BigDecimal(dailyChargeAmount);

    }

    /**
     * Adds a percent sign  to the numerical values
     * @param amount numerical value to be formatted
     * @return String result of the conversion
     */
    public static String addPercentSign(int amount) {
        double newAmount = (double) amount / 100;
        return NumberFormat.getPercentInstance().format(newAmount);
    }

}
