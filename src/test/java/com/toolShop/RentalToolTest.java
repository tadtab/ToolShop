package com.toolShop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.toolShop.exception.InvalidDiscountPercentException;
import com.toolShop.exception.InvalidRentalDayCountException;
import com.toolShop.util.ToolShopUtility;

/**
 * Test class for rentalTool.
 * 
 * @author tadtab
 *
 */
public class RentalToolTest {

    /**
     * Tests the InvalidDiscountPercentException thrown when discount percent value
     * is not between 0 and 100 are provided
     * 
     * @throws InvalidDiscountPercentException
     * @throws InvalidRentalDayCountException
     */
    @Test(expected = InvalidDiscountPercentException.class)
    public void test1CheckOutForDiscountPercentException()
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        BigDecimal dailyCharge = new BigDecimal(1.99);
        ToolType toolType = new ToolType("Jackhammer", dailyCharge, true, true, false);

        RentalTool rentalTool = new RentalTool(ToolCode.LADW, Brand.RIDGID, toolType);

        String cheOutDate = "9/3/15";
        rentalTool.checkOut(ToolCode.JAKR, 5, 101, cheOutDate);

    }

    /**
     * Test tool with code LADW which is Ladder and there is a July 4th holiday on
     * on week day.
     * 
     * Expected the charges count will be 2 days since this tool does not have
     * charge on holiday
     * 
     * @throws InvalidDiscountPercentException
     * @throws InvalidRentalDayCountException
     */
    @Test
    public void test2CheckOutForLadderWithJulyFourthOnWeekDay()
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        BigDecimal dailyCharge = new BigDecimal(1.99);
        ToolType toolType = new ToolType("Ladder", dailyCharge, true, true, false);

        RentalTool rentalTool = new RentalTool(ToolCode.LADW, Brand.RIDGID, toolType);

        String cheOutDate = "7/2/20";
        RentalAgreement rentalAgreement = rentalTool.checkOut(ToolCode.LADW, 3, 10, cheOutDate);

        String dueDate = "7/5/20";
        LocalDate expectedDueDate = ToolShopUtility.convertStringToLocalDate(dueDate);

        // assert due date
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());

        // assert daily charge
        assertEquals("$1.99", rentalAgreement.getDailyRentalChage());

        // assert charges day
        assertEquals(2, rentalAgreement.getChargeDays());

        // assert preDiscount Charge
        assertEquals("$3.98", rentalAgreement.getPreDiscountCharge());

        // assert discount percent
        assertEquals("10%", rentalAgreement.getDiscountPercent());

        // assert Discount Amount
        assertEquals("$0.40", rentalAgreement.getDiscountAmount());

        // assert final charge
        assertEquals("$3.58", rentalAgreement.getFinalCharge());
        
        rentalAgreement.printOutThisPropertiesToConsole();
    }

    /**
     * Test tool with code CHNS which is Chain saw and there is a July 4th holiday
     *  on weekdays.
     * 
     * Expected the charges count will be 3 days since this tool does not have
     * charge on weekends.
     * 
     * @throws InvalidDiscountPercentException
     * @throws InvalidRentalDayCountException
     */
    @Test
    public void test3CheckOutForChainsawWithJulyFourthOnWeekEnd()
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        BigDecimal dailyCharge = new BigDecimal(1.49);
        ToolType toolType = new ToolType("Chainsaw", dailyCharge, true, true, false);

        RentalTool rentalTool = new RentalTool(ToolCode.CHNS, Brand.STIHL, toolType);

        String cheOutDate = "7/2/15";
        RentalAgreement rentalAgreement = rentalTool.checkOut(ToolCode.CHNS, 5, 25, cheOutDate);

        String dueDate = "7/7/15";
        LocalDate expectedDueDate = ToolShopUtility.convertStringToLocalDate(dueDate);

        // assert due date
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());

        // assert daily charge
        assertEquals("$1.49", rentalAgreement.getDailyRentalChage());

        // assert charges day
        assertEquals(3, rentalAgreement.getChargeDays());

        // assert preDiscount Charge
        assertEquals("$4.47", rentalAgreement.getPreDiscountCharge());

        // assert discount percent
        assertEquals("25%", rentalAgreement.getDiscountPercent());

        // assert Discount Amount
        assertEquals("$1.12", rentalAgreement.getDiscountAmount());

        // assert final charge
        assertEquals("$3.35", rentalAgreement.getFinalCharge());
    }

    /**
     * Test tool with code JACD which is Jack hammer and there is a Labor day
     * happening on first Monday of September.
     * 
     * Expected the charges day count will be 3 days since this tool does not have
     * charge on weekends and holidays.
     * 
     * @throws InvalidDiscountPercentException
     * @throws InvalidRentalDayCountException
     */
    @Test
    public void test4CheckOutForJackhammerWithLaborDayOnFirstMondayOfSeptember()
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        BigDecimal dailyCharge = new BigDecimal(2.99);
        ToolType toolType = new ToolType("Jackhammer", dailyCharge, true, true, false);

        RentalTool rentalTool = new RentalTool(ToolCode.JAKD, Brand.DEWALT, toolType);

        String cheOutDate = "9/3/15";
        RentalAgreement rentalAgreement = rentalTool.checkOut(ToolCode.JAKD, 6, 0, cheOutDate);

        String dueDate = "9/9/15";
        LocalDate expectedDueDate = ToolShopUtility.convertStringToLocalDate(dueDate);

        // assert due date
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());

        // assert daily charge
        assertEquals("$2.99", rentalAgreement.getDailyRentalChage());

        // assert charges day
        assertEquals(3, rentalAgreement.getChargeDays());

        // assert preDiscount Charge
        assertEquals("$8.97", rentalAgreement.getPreDiscountCharge());

        // assert discount percent
        assertEquals("0%", rentalAgreement.getDiscountPercent());

        // assert Discount Amount
        assertEquals("$0.00", rentalAgreement.getDiscountAmount());

        // assert final charge
        assertEquals("$8.97", rentalAgreement.getFinalCharge());
    }

    /**
     * Test tool with code JACD which is Jack hammer and there is Fourth of July
     * happening on Friday.
     * 
     * Expected the charges day count will be 5 days since this tool does not have
     * charge on weekends and holidays.
     * 
     * @throws InvalidDiscountPercentException
     * @throws InvalidRentalDayCountException
     */
    @Test
    public void test5CheckOutForJackhammerWithJulyFourthOnWeekDay()
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        BigDecimal dailyCharge = new BigDecimal(2.99);
        ToolType toolType = new ToolType("Jackhammer", dailyCharge, true, true, false);

        RentalTool rentalTool = new RentalTool(ToolCode.JAKR, Brand.RIDGID, toolType);

        String cheOutDate = "7/2/15";
        RentalAgreement rentalAgreement = rentalTool.checkOut(ToolCode.JAKR, 9, 0, cheOutDate);

        String dueDate = "7/11/15";
        LocalDate expectedDueDate = ToolShopUtility.convertStringToLocalDate(dueDate);

        // assert due date
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());

        // assert daily charge
        assertEquals("$2.99", rentalAgreement.getDailyRentalChage());

        // assert charges day
        assertEquals(5, rentalAgreement.getChargeDays());

        // assert preDiscount Charge
        assertEquals("$14.95", rentalAgreement.getPreDiscountCharge());

        // assert discount percent
        assertEquals("0%", rentalAgreement.getDiscountPercent());

        // assert Discount Amount
        assertEquals("$0.00", rentalAgreement.getDiscountAmount());

        // assert final charge
        assertEquals("$14.95", rentalAgreement.getFinalCharge());
    }

    /**
     * Test tool with code JACD which is Jack hammer and there is Fourth of July
     * happening on Friday.
     * 
     * Expected the charges day count will be 1 days since this tool does not have
     * charge on weekends and holidays.
     * 
     * @throws InvalidDiscountPercentException
     * @throws InvalidRentalDayCountException
     */
    @Test
    public void test6CheckOutForJackhammerWithJulyFourthOnWeekDayCase2()
            throws InvalidDiscountPercentException, InvalidRentalDayCountException {

        BigDecimal dailyCharge = new BigDecimal(2.99);
        ToolType toolType = new ToolType("Jackhammer", dailyCharge, true, true, false);

        RentalTool rentalTool = new RentalTool(ToolCode.JAKR, Brand.RIDGID, toolType);

        String cheOutDate = "7/2/20";
        RentalAgreement rentalAgreement = rentalTool.checkOut(ToolCode.JAKR, 4, 50, cheOutDate);

        String dueDate = "7/6/20";
        LocalDate expectedDueDate = ToolShopUtility.convertStringToLocalDate(dueDate);

        // assert due date
        assertEquals(expectedDueDate, rentalAgreement.getDueDate());

        // assert daily charge
        assertEquals("$2.99", rentalAgreement.getDailyRentalChage());

        // assert charges day
        assertEquals(1, rentalAgreement.getChargeDays());

        // assert preDiscount Charge
        assertEquals("$2.99", rentalAgreement.getPreDiscountCharge());

        // assert discount percent
        assertEquals("50%", rentalAgreement.getDiscountPercent());

        // assert Discount Amount
        assertEquals("$1.50", rentalAgreement.getDiscountAmount());

        // assert final charge
        assertEquals("$1.49", rentalAgreement.getFinalCharge());
    }

}
