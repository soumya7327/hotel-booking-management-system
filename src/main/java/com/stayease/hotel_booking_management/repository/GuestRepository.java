package com.stayease.hotel_booking_management.repository;

import com.stayease.hotel_booking_management.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
