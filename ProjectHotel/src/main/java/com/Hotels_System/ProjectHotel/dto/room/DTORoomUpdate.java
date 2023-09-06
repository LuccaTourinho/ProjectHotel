package com.Hotels_System.ProjectHotel.dto.room;

import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTORoomUpdate(
        @JsonAlias({"id"}) Long id,
        @JsonAlias({"capacity"}) String capacity,
        @JsonAlias({"number"}) Integer number,
        @JsonAlias({"available"})Boolean available
) {
    public DTORoomUpdate(Room room) {
        this(room.getId(), room.getCapacity().name(),room.getNumber(), room.getAvailable());
    }
}
