package com.stayease.hotel_booking_management.dto;

import com.stayease.hotel_booking_management.entity.enums.Gender;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
