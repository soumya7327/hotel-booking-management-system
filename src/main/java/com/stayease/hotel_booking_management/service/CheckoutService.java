package com.stayease.hotel_booking_management.service;

import com.stayease.hotel_booking_management.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}

