package com.toolShop;

import java.math.BigDecimal;

/**
 * Rental tool type with daily charge and to which days(weekends, weekdays, holidays) the charge applies.
 * 
 * @author tadtab
 *
 */
public class ToolType {
    
    private String toolName;
    private BigDecimal dailyChargeAmount;
    private boolean weekDayChargeApplies;
    private boolean weekEndChargeApplies;
    private boolean holidayChargeApplies;
    
    /**
     *Constructor to instantiate the ToolType object with given values. 
     * 
     * @param toolName Name of the tool being rented
     * @param dailyChargeAmount charge amount to be charged on daily basis
     * @param weekDayChargeApplies indicates whether the daily charge applies to weekDays
     * @param weekEndChargeApplies indicates whether the daily charge applies to weekends
     * @param holidayChargeApplies indicates whether the daily charge applies to holidays
     */
    public ToolType(String toolName, BigDecimal dailyChargeAmount, boolean weekDayChargeApplies, boolean weekEndChargeApplies,
            boolean holidayChargeApplies) {
        this.toolName = toolName;
        this.dailyChargeAmount = dailyChargeAmount;
        this.weekDayChargeApplies = weekDayChargeApplies;
        this.weekEndChargeApplies = weekEndChargeApplies;
        this.holidayChargeApplies = holidayChargeApplies;
    }
    
    /**
     * Retrieve the tool name 
     * 
     * @return
     */
    public String getToolName() {
        return toolName;
    }
    
    /**
     * Retrieves the daily charge amount
     * @return
     */
    public BigDecimal getDailyChargeAmount() {
        return dailyChargeAmount;
    }
    
    /**
     * Indicates if charge applies to weekdays 
     * @return boolean
     */
    public boolean isWeekDayChargeApplies() {
        return weekDayChargeApplies;
    }
    /**
     * Indicates if charge applies to weekends 
     * @return boolean
     */
    public boolean isWeekEndChargeApplies() {
        return weekEndChargeApplies;
    }
    
    /**
     * Indicates if charge applies to holidays 
     * @return boolean
     */
    public boolean isHolidayChargeApplies() {
        return holidayChargeApplies;
    }
    
    

}
