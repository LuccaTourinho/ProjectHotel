package com.Hotels_System.ProjectHotel.validation;

import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.Hotels_System.ProjectHotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoomNumberUniqueValidation {

    @Autowired private RoomRepository roomRepository;

    public boolean isRoomNumberUnique(Long hotelId, Integer roomNumber) {
        Optional<Room> existingRoom = roomRepository.findByHotelIdAndNumber(hotelId, roomNumber);
        return existingRoom.isEmpty();
    }
}
