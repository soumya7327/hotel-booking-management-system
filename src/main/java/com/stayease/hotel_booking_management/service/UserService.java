package com.stayease.hotel_booking_management.service;


import com.stayease.hotel_booking_management.dto.ProfileUpdateRequestDto;
import com.stayease.hotel_booking_management.dto.UserDto;
import com.stayease.hotel_booking_management.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();

}

