package com.stayease.hotel_booking_management.repository;

import com.stayease.hotel_booking_management.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository  extends JpaRepository<Room, Long> {
}
