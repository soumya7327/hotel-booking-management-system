package com.stayease.hotel_booking_management.Controller;

import com.stayease.hotel_booking_management.dto.HotelDto;
import com.stayease.hotel_booking_management.dto.HotelInfoDto;
import com.stayease.hotel_booking_management.dto.HotelPriceDto;
import com.stayease.hotel_booking_management.dto.HotelSearchRequest;
import com.stayease.hotel_booking_management.service.HotelService;
import com.stayease.hotel_booking_management.service.InventoryService;
import com.stayease.hotel_booking_management.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {

    private final HotelService hotelService;
    private final InventoryService inventoryService;

@GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@Valid @RequestBody HotelSearchRequest hotelSearchRequest) {

      var page=inventoryService.searchHotels(hotelSearchRequest);
       return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
    return ResponseEntity.ok(hotelService.getHotelInformation(hotelId));
    }
}
