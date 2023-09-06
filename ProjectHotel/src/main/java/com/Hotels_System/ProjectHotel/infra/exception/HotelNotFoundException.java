package com.Hotels_System.ProjectHotel.infra.exception;

public class HotelNotFoundException extends Exception {
    public HotelNotFoundException(){
        super("Hotel does not exist");
    }
}
