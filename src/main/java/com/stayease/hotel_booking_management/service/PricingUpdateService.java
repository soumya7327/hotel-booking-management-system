package com.stayease.hotel_booking_management.service;

import com.stayease.hotel_booking_management.entity.Hotel;
import com.stayease.hotel_booking_management.entity.HotelMinPrice;
import com.stayease.hotel_booking_management.entity.Inventory;
import com.stayease.hotel_booking_management.repository.HotelMinPriceRepository;
import com.stayease.hotel_booking_management.repository.HotelRepository;
import com.stayease.hotel_booking_management.repository.InventoryRepository;
import com.stayease.hotel_booking_management.strategy.PricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PricingUpdateService {

    // Scheduler to update the inventory and HotelMinPrice tables every hour

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final PricingService pricingService;

//        @Scheduled(cron = "*/5 * * * * *")
    @Scheduled(cron = "0 0 * * * *")
    public void updatePrices() {
        int page = 0;
        int batchSize = 100;


        while(true) {
            Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page, batchSize));
            if(hotelPage.isEmpty()) {
                break;
            }
            hotelPage.getContent().forEach(this::updateHotelPrices);
            //this::updateHotelPrices is a method reference. It means: For each hotel, call  updateHotelPrices(hotel) method.

            page++;
        }
    }

    private   void updateHotelPrices(Hotel hotel) {
        log.info("Updating hotel prices for hotel ID: {}", hotel.getId());
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1);

        List<Inventory> inventoryList = inventoryRepository.findByHotelAndDateBetween(hotel, startDate, endDate);
        // it means: Get all Inventory records belonging to this hotel whose date is between startDate and endDate.

        updateInventoryPrices(inventoryList);

        updateHotelMinPrice(hotel, inventoryList, startDate, endDate);
    }

    //The purpose is:
    //Take all the updated Inventory prices for one hotel, find the cheapest price for each date, and store that in HotelMinPrice.
    private void updateHotelMinPrice(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {
        // Compute minimum price per day for the hotel
        Map<LocalDate, BigDecimal> dailyMinPrices = inventoryList.stream()
                .collect(Collectors.groupingBy(
                        Inventory::getDate,
                        Collectors.mapping(Inventory::getPrice, Collectors.minBy(Comparator.naturalOrder()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().orElse(BigDecimal.ZERO)));

//        Map<LocalDate, BigDecimal> dailyMinPrices = new HashMap<>();
//
//        for (Inventory inventory : inventoryList) {
//
//            LocalDate date = inventory.getDate();
//            BigDecimal price = inventory.getPrice();
//
//            if (!dailyMinPrices.containsKey(date)
//                    || price.compareTo(dailyMinPrices.get(date)) < 0) {

                // example - price = 2500 , dailyMinPrices.get(date) = 3000
                //then 2500.compareTo(3000)  "Since 2500 is smaller than 3000, compareTo() returns a negative number."
                // then negative number < 0  (True)    . -> so updates the minimum price to ₹2500.

//               dailyMinPrices.put(date, price);
//            }
//        }

        // Prepare HotelPrice entities in bulk
        //For every date's minimum price, find the existing HotelMinPrice record. If it doesn't exist, create one. Set its price and put it into a list.
        List<HotelMinPrice> hotelPrices = new ArrayList<>();
        dailyMinPrices.forEach((date, price) -> {
            HotelMinPrice hotelPrice = hotelMinPriceRepository.findByHotelAndDate(hotel, date)
                    .orElse(new HotelMinPrice(hotel, date));
            hotelPrice.setPrice(price);
            hotelPrices.add(hotelPrice);
        });

        // Save all HotelPrice entities in bulk
        hotelMinPriceRepository.saveAll(hotelPrices);
    }

    //That method is responsible for:
    //Calculating the dynamic price for each inventory using your pricing strategies.
    private void updateInventoryPrices(List<Inventory> inventoryList) {
        inventoryList.forEach(inventory -> {
            BigDecimal dynamicPrice = pricingService.calculateDynamicPricing(inventory);
            inventory.setPrice(dynamicPrice);
        });
        inventoryRepository.saveAll(inventoryList);
    }

}
