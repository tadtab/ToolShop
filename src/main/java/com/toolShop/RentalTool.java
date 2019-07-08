package com.toolShop;

import java.time.LocalDate;
import com.toolShop.exception.InvalidDiscountPercentException;
import com.toolShop.exception.InvalidRentalDayCountException;
import com.toolShop.util.ToolShopUtility;

/**
 * This class represents the rental tool with code, brand and tool type.
 * Invoking the checkout method will return the RentalAgreement object.
 * 
 * 
 * @author tadtab
 *
 */
public class RentalTool implements Tool {

    private ToolCode toolCode;
    private Brand brand;
    private ToolType toolType;

    /**
     * Constructor with three arguments which initializes RentalTool properties to
     * the values passed to it by the caller.
     * 
     * @param toolCode
     *            identifies each tool
     * @param brand
     *            tool maker name
     * @param toolType
     *            type of the tool which also describes to which days(weekday,
     *            weekend or holiday) rental charge will applied
     */
    public RentalTool(ToolCode toolCode, Brand brand, ToolType toolType) {
        this.toolCode = toolCode;
        this.brand = brand;
        this.toolType = toolType;

    }

    /**
     * A method that is responsible for producing a rental agreement. it takes tool
     * code, rental day count and checkout date.
     * 
     * @param toolCode
     *            identifies each tool.
     * @param rentalDayCount
     *            number of days the tool will be rented.
     * @param discountPercent
     *            discount on the daily rental charge made up on checkout.
     * @param checkOutDate
     *            the date the tool is checked out.
     * 
     * @return RentalAgreement object returned upon checkout.
     * 
     * @throws InvalidDiscountPercentException
     *             thrown when the discount percent is not between 0 and 100
     * @throws InvalidRentalDayCountException
     *             thrown if the rental day count is less than 0;
     * 
     */
    public RentalAgreement checkOut(ToolCode toolCode, int rentalDayCount, int discountPercent, String checkOutDate)
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        RentalAgreement rentalAgreement = new RentalAgreement();

        if (discountPercent < 0 || discountPercent > 100) {

            throw new InvalidDiscountPercentException();
        }

        if (rentalDayCount < 1) {

            throw new InvalidRentalDayCountException();
        }

        // set tool code
        rentalAgreement.setToolCode(toolCode);

        // set tool type
        rentalAgreement.setToolType(this.getToolType().getToolName());

        // set tool brand
        rentalAgreement.setToolBrand(this.getBrand());

        // set rental days
        rentalAgreement.setRentalDays(rentalDayCount);

        // set due date
        rentalAgreement.setDueDate(checkOutDate, rentalDayCount);

        // set number of charge day
        LocalDate toolCheckOut = ToolShopUtility.convertStringToLocalDate(checkOutDate);
        rentalAgreement.setChargeDays(toolCode, toolCheckOut, rentalAgreement.getDueDate());

        // Check out date
        rentalAgreement.setCheckOutDate(toolCheckOut);

        // set daily charge
        rentalAgreement.setDailyRentalCharge(this.getToolType().getDailyChargeAmount());

        // set PreDiscount Charge
        int dailyChargeCount = rentalAgreement.getChargeDays();
        String dailyRentalChage = rentalAgreement.getDailyRentalChage();
        rentalAgreement.setPreDiscountCharge(dailyChargeCount, dailyRentalChage);

        // populate Discount present
        rentalAgreement.setDiscountPercent(discountPercent);

        // populate discount amount
        String preDiscountCharge = rentalAgreement.getPreDiscountCharge();
        rentalAgreement.setDiscountAmount(discountPercent, preDiscountCharge);

        // populate final charge
        String discountAmount = rentalAgreement.getDiscountAmount();
        rentalAgreement.setFinalCharge(preDiscountCharge, discountAmount);

        return rentalAgreement;
    }

    /**
     * Retrieves tool code.
     * 
     * @return ToolCode
     */
    public ToolCode getToolCode() {
        return toolCode;
    }

    /**
     * Retrieves tool brand
     * 
     * @return Brand
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Retrieves tool type
     * 
     * @return ToolType
     */
    public ToolType getToolType() {
        return toolType;
    }

}