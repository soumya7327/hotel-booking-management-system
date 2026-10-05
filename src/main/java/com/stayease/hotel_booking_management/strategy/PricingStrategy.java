package com.stayease.hotel_booking_management.strategy;

import com.stayease.hotel_booking_management.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);

}
