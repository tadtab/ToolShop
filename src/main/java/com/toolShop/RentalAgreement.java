package com.toolShop;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import com.toolShop.util.NumberStringFormatter;
import com.toolShop.util.ToolNameAndCodeMapper;
import com.toolShop.util.ToolShopUtility;

/**
 * This class represents the Rental Agreement which shows tool info, charges,
 * due dates and discounts. Up on checkout some of these properties will be
 * printed for a single tool rental.
 * 
 * @author tadtab
 *
 */
public class RentalAgreement {

    private ToolCode toolCode;
    private String toolType;
    private Brand toolBrand;
    private int rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private String dailyRentalChage;
    private int chargeDays;
    private String preDiscountCharge;
    private String discountPercent;
    private String discountAmount;
    private String finalCharge;

    /**
     * Retrieves tool code
     */
    public ToolCode getToolCode() {
        return toolCode;
    }

    /**
     * Setter for tool code. the value is set from the tool being rented
     */
    public void setToolCode(ToolCode toolCode) {
        this.toolCode = toolCode;
    }

    /**
     * getter for tool type
     */
    public String getToolType() {
        return toolType;
    }

    /**
     * Sets tool type from the tool being rented
     */
    public void setToolType(String str) {
        this.toolType = str;
    }

    /**
     * getter for tool brand property
     */
    public Brand getToolBrand() {
        return toolBrand;
    }

    /**
     * Sets tool brand from the tool being rented
     */
    public void setToolBrand(Brand toolBrand) {
        this.toolBrand = toolBrand;
    }

    /**
     * retrieves the number of days the tool being rented
     */
    public int getRentalDays() {
        return rentalDays;
    }

    /**
     * sets the rental days for the tool
     */
    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    /**
     * retrieves the day the tool is checked out
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * sets the day the tool is checked out
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * retrieves the date the tool need to be returned
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the date the tool need to be returned. It is Calculated from checkout
     * date and rental days.
     */
    public void setDueDate(String checkOutDate, int rentalDays) {
        LocalDate localDate = ToolShopUtility.convertStringToLocalDate(checkOutDate);

        this.dueDate = ToolShopUtility.addDaysToLocalDate(localDate, rentalDays);
    }

    /**
     * Retrieves the daily rental charge of the tool.
     */
    public String getDailyRentalChage() {
        return dailyRentalChage;
    }

    /**
     * Sets the daily rental charge of the tool.
     *
     * @param dailyRentalChage
     */
    public void setDailyRentalCharge(BigDecimal dailyRentalChage) {

        this.dailyRentalChage = NumberStringFormatter.addCurrencySign(dailyRentalChage);
    }

    /**
     * Retrieves the number of days the customer is charged.
     */
    public int getChargeDays() {
        return chargeDays;
    }

    /**
     * Count of chargeable days, from day after checkout through and including due
     * date, excluding “no charge” days as specified by the tool type.
     * 
     * @param chargeDays
     */
    public void setChargeDays(ToolCode toolCode, LocalDate checkOutDate, LocalDate dueDate) {

        int chergeDayCounter = 0;

        LocalDate currentChargeDay = checkOutDate;

        while (currentChargeDay.isBefore(dueDate) && !currentChargeDay.equals(dueDate)) {

            currentChargeDay = currentChargeDay.plusDays(1);

            ToolNameAndCodeMapper mapperInstance = ToolNameAndCodeMapper.getMapper();

            if (("Ladder").equals(mapperInstance.toolCodeNameMap.get(toolCode)) && !isItFourthOfJuly(currentChargeDay)
                    && !isItLaborDay(currentChargeDay)) {
                chergeDayCounter++;
            }

            if (("Chainsaw").equals(mapperInstance.toolCodeNameMap.get(toolCode)) && !isItWeekEnd(currentChargeDay)) {

                chergeDayCounter++;

            }

            if (("Jackhammer").equals(mapperInstance.toolCodeNameMap.get(toolCode))
                    && !isItFourthOfJuly(currentChargeDay) && !isItLaborDay(currentChargeDay)
                    && !isItWeekEnd(currentChargeDay)) {
                chergeDayCounter++;
            }

        }

        this.chargeDays = chergeDayCounter;
    }

    /**
     * Determines if the current day is Saturday or Sunday.
     * 
     * @param currentChargeDay
     * @return
     */
    private boolean isItWeekEnd(LocalDate currentChargeDay) {

        boolean isCurrentDayAWeekEnd = false;

        if (DayOfWeek.SATURDAY.equals(currentChargeDay.getDayOfWeek())
                || DayOfWeek.SUNDAY.equals(currentChargeDay.getDayOfWeek())) {

            isCurrentDayAWeekEnd = true;
        }

        return isCurrentDayAWeekEnd;

    }

    /**
     * Checks if the current day is July 4th and weekday, Or
     * 
     * If July Fourth is on weekend, consider the closest weekday as a Holiday. For
     * that analyze current day if it is July 3rd and Friday or if it is July 5th
     * and Monday.
     * 
     * @param currentChargeDay
     *            to be checked against July 4th
     * @return
     */
    private boolean isItFourthOfJuly(LocalDate currentChargeDay) {

        boolean isCurrentDayFourthOfJuly = false;

        if (Month.JULY.equals(currentChargeDay.getMonth())) {

            if (currentChargeDay.getDayOfMonth() == 4 && !(DayOfWeek.SATURDAY.equals(currentChargeDay.getDayOfWeek())
                    || DayOfWeek.SUNDAY.equals(currentChargeDay.getDayOfWeek()))) {

                isCurrentDayFourthOfJuly = true;
            }

            if (currentChargeDay.getDayOfMonth() == 3 && DayOfWeek.FRIDAY.equals(currentChargeDay.getDayOfWeek())) {

                isCurrentDayFourthOfJuly = true;
            }

            if (currentChargeDay.getDayOfMonth() == 5 && DayOfWeek.MONDAY.equals(currentChargeDay.getDayOfWeek())) {

                isCurrentDayFourthOfJuly = true;
            }
        }

        return isCurrentDayFourthOfJuly;
    }

    /**
     * Checks if the current day is Labor day which happens on first day of
     * September, To work on this first check if the month is September and the day
     * is Monday. To find out if it is the first Monday, check the day one week
     * before and see if it is still September
     * 
     * @param currentChargeDay
     * @return
     */
    private boolean isItLaborDay(LocalDate currentChargeDay) {

        boolean isCurrentDayLaborDay = false;

        if (Month.SEPTEMBER.equals(currentChargeDay.getMonth())
                && DayOfWeek.MONDAY.equals(currentChargeDay.getDayOfWeek())
                && !Month.SEPTEMBER.equals(currentChargeDay.minusDays(7).getMonth())) {

            isCurrentDayLaborDay = true;

        }

        return isCurrentDayLaborDay;
    }

    /**
     * retrieves the charges before discount
     * 
     * @return
     */
    public String getPreDiscountCharge() {
        return preDiscountCharge;
    }

    /**
     * Sets preDiscount charges. It is Calculated as charge days X daily charge.
     * Resulting total rounded half up to cents.
     * 
     * @param preDiscountCharge
     */
    public void setPreDiscountCharge(int chargeDays, String dailyCharge) {

        BigDecimal currenctAmount = NumberStringFormatter.convertStringToBigDecimal(dailyCharge);

        BigDecimal newpreDiscountCharge = currenctAmount.multiply(new BigDecimal(chargeDays));

        this.preDiscountCharge = NumberStringFormatter.addCurrencySign(newpreDiscountCharge);
    }

    /**
     * Retrieves the discount percent
     * 
     * @return
     */
    public String getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Sets discount percent. The amount is specified at checkout
     * 
     * @param dicountPercent
     */
    public void setDiscountPercent(int dicountPercent) {
        this.discountPercent = NumberStringFormatter.addPercentSign(dicountPercent);
    }

    /**
     * Retrives the dicount amount.
     * 
     * @return
     */
    public String getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the discount amount. It is calculated from discount % and pre-discount
     * charge. Resulting amount rounded half up to cents.
     * 
     * @param discountAmount
     */
    public void setDiscountAmount(int discountPercent, String preDiscountCharge) {
        BigDecimal chargeAmount = NumberStringFormatter.convertStringToBigDecimal(preDiscountCharge);
        double discountAmountInDecimal = (double) discountPercent / 100;
        BigDecimal newDiscountAmount = chargeAmount.multiply(BigDecimal.valueOf(discountAmountInDecimal));
        this.discountAmount = NumberStringFormatter.addCurrencySign(newDiscountAmount);
    }

    /**
     * Retrieves the final amount of the rental charge
     * 
     * @return
     */
    public String getFinalCharge() {
        return finalCharge;
    }

    /**
     * Sets the final Amount of the rental charge. It is Calculated as pre-discount
     * charge - discount amount.
     * 
     * @param finalCharge
     */
    public void setFinalCharge(String preDiscountCharge, String discountAmount) {
        BigDecimal preCharge = NumberStringFormatter.convertStringToBigDecimal(preDiscountCharge);
        BigDecimal discount = NumberStringFormatter.convertStringToBigDecimal(discountAmount);
        this.finalCharge = NumberStringFormatter.addCurrencySign(preCharge.subtract(discount));
    }

    /**
     * Prints out the properties of this objects
     */
    public void printOutThisPropertiesToConsole() {
        System.out.println("Tool code: " + this.getToolCode());
        System.out.println("Tool type: " + this.getToolType());
        System.out.println("Tool brand: " + this.getToolBrand().getValue());
        System.out.println("Rental days: " + this.getRentalDays());

        System.out.println("Check out date: "
                + ToolShopUtility.convertADateFormatToAnothorDateFormat(this.getCheckOutDate().toString()));
        System.out
                .println("Due date: " + ToolShopUtility.convertADateFormatToAnothorDateFormat(this.getDueDate().toString()));
        System.out.println("Daily rental charge: " + this.getDailyRentalChage());
        System.out.println("Charge days: " + this.getChargeDays());

        System.out.println("Pre-discount charge: " + this.getPreDiscountCharge());
        System.out.println("Discount percent: " + this.getDiscountPercent());
        System.out.println("Discount amount: " + this.getDiscountAmount());
        System.out.println("Final charge: " + this.getFinalCharge());
    }

}
