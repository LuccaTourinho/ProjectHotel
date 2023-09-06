package com.Hotels_System.ProjectHotel.factory;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotelComplete;

public class HotelFactory {

    public Hotel convertDTOHotelToHotel(DTOHotel dtoHotel) {
        Hotel hotel = new Hotel(dtoHotel);
        return hotel;
    }

    public DTOHotelComplete convertHotelToDTOComplete(Hotel hotel) {
        DTOHotelComplete dtoHotelComplete = new DTOHotelComplete(hotel);
        return dtoHotelComplete;
    }

    public DTOHotelComplete createDTOHotelToDTOComplete(DTOHotel dtoHotel) {
        Hotel hotel = convertDTOHotelToHotel(dtoHotel);
        return convertHotelToDTOComplete(hotel);
    }

}
