package com.stayease.hotel_booking_management.strategy;


import com.stayease.hotel_booking_management.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class UrgencyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);

        LocalDate today = LocalDate.now();
        if(!inventory.getDate().isBefore(today) && inventory.getDate().isBefore(today.plusDays(7))) {
            //It means: “If the inventory date is today or later, and it is before 7 days from today, then apply the urgency price increase.”
           // it simply means: If the booking date is within the next 7 days from today, apply the urgency price increase.
            price = price.multiply(BigDecimal.valueOf(1.15));
        }
        return price;
    }
}
