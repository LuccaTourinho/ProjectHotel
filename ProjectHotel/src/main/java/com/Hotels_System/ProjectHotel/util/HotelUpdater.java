package com.Hotels_System.ProjectHotel.util;

import com.Hotels_System.ProjectHotel.domain.Hotel;
import com.Hotels_System.ProjectHotel.domain.Quality;
import com.Hotels_System.ProjectHotel.dto.DTOAddress;
import com.Hotels_System.ProjectHotel.dto.DTOContacts;

public class HotelUpdater {

    private final Hotel hotel;

    public HotelUpdater(Hotel hotel){
        this.hotel = hotel;
    }

    public HotelUpdater name(String name){

        if(name != null){
            hotel.setName(name);
        }
        return this;
    }

    public HotelUpdater address(DTOAddress dtoAddress){

        if(dtoAddress != null) {
            AddressUpdater addressUpdater = new AddressUpdater(hotel.getAddress())
                    .country(dtoAddress.country())
                    .state(dtoAddress.state())
                    .city(dtoAddress.city());
            hotel.setAddress(addressUpdater.finishUpdater());
        }
        return this;
    }

    public HotelUpdater quality(String quality){

        if(quality != null){
            Quality qualityvalue = Quality.valueOf(quality);
            hotel.setQuality(qualityvalue);
        }
        return this;
    }

    public HotelUpdater contacts(DTOContacts dtoContacts){
        if(dtoContacts != null){
            ContactsUpdater contactsUpdater = new ContactsUpdater(hotel.getContacts())
                    .email(dtoContacts.email())
                    .phone(dtoContacts.phone());
            hotel.setContacts(contactsUpdater.finishUpdater());
        }
        return this;
    }

    public Hotel finishUpdater(){
        return hotel;
    }
}
