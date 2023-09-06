package com.Hotels_System.ProjectHotel.dto.room;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotelComplete;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTORoom(
        @JsonAlias({"hotel"}) DTOHotelComplete hotel,
        @JsonAlias({"capacity"})String capacity,
        @JsonAlias({"number"})Integer number

) {
    public DTORoom(Room room) {
        this(new DTOHotelComplete(room.getHotel()), room.getCapacity().name(),room.getNumber());
    }
}
