package com.Hotels_System.ProjectHotel.exception;

public class HotelRoomNumberException extends Exception {
    public HotelRoomNumberException(){
        super("You can't repeat the room number on the same hotel.");
    }
}
