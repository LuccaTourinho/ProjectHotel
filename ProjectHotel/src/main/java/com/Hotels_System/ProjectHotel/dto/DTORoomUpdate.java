package com.Hotels_System.ProjectHotel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DTORoomUpdate(
        @JsonAlias({"id"}) Long id,
        @JsonAlias({"capacity"}) String capacity,
        @JsonAlias({"number"}) Integer number,
        @JsonAlias({"available"})Boolean available
) {
}
