package com.Hotels_System.ProjectHotel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DTOAddress(
        @JsonAlias({"country"}) String country,
        @JsonAlias({"state"}) String state,
        @JsonAlias({"city"}) String city
){
}
