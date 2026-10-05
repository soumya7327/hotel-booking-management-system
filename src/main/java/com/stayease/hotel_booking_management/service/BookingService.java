package com.stayease.hotel_booking_management.service;

import com.stayease.hotel_booking_management.dto.BookingDto;
import com.stayease.hotel_booking_management.dto.BookingRequest;
import com.stayease.hotel_booking_management.dto.GuestDto;
import com.stayease.hotel_booking_management.dto.HotelReportDto;
import com.stripe.model.Event;


import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest );

    BookingDto addGuests( Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();
}
