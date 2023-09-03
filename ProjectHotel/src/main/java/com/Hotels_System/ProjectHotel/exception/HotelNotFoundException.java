package com.Hotels_System.ProjectHotel.exception;

public class HotelNotFoundException extends Exception {
    public HotelNotFoundException(){
        super("Hotel does not exist");
    }
}
