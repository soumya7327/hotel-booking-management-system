package com.stayease.hotel_booking_management.repository;

import com.stayease.hotel_booking_management.entity.Hotel;
import com.stayease.hotel_booking_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);


}
