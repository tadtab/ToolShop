package com.toolShop;


import com.toolShop.exception.InvalidDiscountPercentException;
import com.toolShop.exception.InvalidRentalDayCountException;

public interface Tool {
    
    public RentalAgreement checkOut(ToolCode toolCode, 
            int rentalDayCount, int discountPercent, String checoutDate) throws InvalidDiscountPercentException, InvalidRentalDayCountException;
}
