package com.stayease.hotel_booking_management.service;


import com.stayease.hotel_booking_management.dto.HotelDto;
import com.stayease.hotel_booking_management.dto.HotelInfoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);

    HotelInfoDto getHotelInformation(Long hotelId);

    List<HotelDto> getAllHotels();


}
