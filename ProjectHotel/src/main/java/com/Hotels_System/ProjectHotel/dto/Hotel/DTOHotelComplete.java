package com.Hotels_System.ProjectHotel.dto.Hotel;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import com.Hotels_System.ProjectHotel.dto.address.DTOAddress;
import com.Hotels_System.ProjectHotel.dto.contacts.DTOContacts;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTOHotelComplete(
        @JsonAlias({"id"}) Long id,
        @JsonAlias({"name"}) String name,
        @JsonAlias({"address"}) DTOAddress address,
        @JsonAlias({"quality"}) String quality,
        @JsonAlias({"contacts"}) DTOContacts contacts
){
    public DTOHotelComplete(Hotel hotel) {
        this(hotel.getId(),hotel.getName(),new DTOAddress( hotel.getAddress()),hotel.getQuality().name(),new DTOContacts(hotel.getContacts()));
    }
}
