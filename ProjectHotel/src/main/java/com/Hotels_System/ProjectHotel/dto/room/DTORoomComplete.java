package com.Hotels_System.ProjectHotel.dto.room;

import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotelComplete;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTORoomComplete(
        @JsonAlias({"id"}) Long id,
        @JsonAlias({"hotel"}) DTOHotelComplete hotel,
        @JsonAlias({"capacity"})String capacity,
        @JsonAlias({"number"})Integer number
) {
    public DTORoomComplete(Room room) {
        this(room.getId(), new DTOHotelComplete(room.getHotel()), room.getCapacity().name(), room.getNumber());
    }
}
