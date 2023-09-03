package com.Hotels_System.ProjectHotel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DTOHotelUpdate(
        @JsonAlias({"id"}) Long id,
        @JsonAlias({"name"}) String name,
        @JsonAlias({"address"}) DTOAddress address,
        @JsonAlias({"quality"}) String quality,
        @JsonAlias({"contacts"}) DTOContacts contacts
){
}
