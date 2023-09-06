package com.Hotels_System.ProjectHotel.util;

import com.Hotels_System.ProjectHotel.domain.address.Address;

public class AddressUpdater {

    private final Address address;

    public AddressUpdater(Address address){
        this.address = address;
    }

    public AddressUpdater country(String country){
        if(country != null){
            address.setCountry(country);
        }
        return this;
    }

    public AddressUpdater state(String state){
        if(state != null){
            address.setState(state);
        }
        return this;
    }

    public AddressUpdater city(String city){
        if(city != null){
            address.setCity(city);
        }
        return this;
    }

    public Address finishUpdater(){
        return address;
    }
}
