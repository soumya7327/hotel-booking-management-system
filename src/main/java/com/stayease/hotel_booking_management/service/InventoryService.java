package com.stayease.hotel_booking_management.service;


import com.stayease.hotel_booking_management.dto.HotelPriceDto;
import com.stayease.hotel_booking_management.dto.HotelSearchRequest;
import com.stayease.hotel_booking_management.dto.InventoryDto;
import com.stayease.hotel_booking_management.dto.UpdateInventoryRequestDto;
import com.stayease.hotel_booking_management.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;


public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);


}

