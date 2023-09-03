package com.Hotels_System.ProjectHotel.dto;

import com.Hotels_System.ProjectHotel.domain.Hotel;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTORoom(
        @JsonAlias({"hotel"}) Hotel hotel,
        @JsonAlias({"capacity"})String capacity,
        @JsonAlias({"number"})Integer number

) {
}
