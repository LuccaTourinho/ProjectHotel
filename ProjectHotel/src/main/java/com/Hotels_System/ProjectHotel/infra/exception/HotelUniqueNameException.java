package com.Hotels_System.ProjectHotel.infra.exception;

public class HotelUniqueNameException extends Exception{
    public HotelUniqueNameException(){
        super("This hotel name already exists.");
    }
}
