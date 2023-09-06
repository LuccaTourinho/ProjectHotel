package com.Hotels_System.ProjectHotel.dto.address;

import com.Hotels_System.ProjectHotel.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTOAddress(
        @JsonAlias({"country"}) String country,
        @JsonAlias({"state"}) String state,
        @JsonAlias({"city"}) String city
){
    public DTOAddress(Address address){
        this(address.getCountry(), address.getState(), address.getCity());
    }
}
