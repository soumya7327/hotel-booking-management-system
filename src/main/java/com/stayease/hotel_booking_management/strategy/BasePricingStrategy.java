package com.stayease.hotel_booking_management.strategy;

import com.stayease.hotel_booking_management.entity.Inventory;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy{

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {

        return inventory.getRoom().getBasePrice();
    }
}