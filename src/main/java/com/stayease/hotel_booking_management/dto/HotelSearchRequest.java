package com.stayease.hotel_booking_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "CheckIn date is required")
    private LocalDate checkInDate;

    @NotNull(message = "CheckOut date is required")
    private LocalDate checkOutDate;

    @NotNull(message = "Rooms count is required")
    @Min(value = 1, message = "At least 1 room is required")
    private Integer roomsCount;

    @Min(value = 0, message = "Page cannot be negative")
    private Integer page = 0;

    @Min(value = 1, message = "Size must be at least 1")
    private Integer size = 10;
}
