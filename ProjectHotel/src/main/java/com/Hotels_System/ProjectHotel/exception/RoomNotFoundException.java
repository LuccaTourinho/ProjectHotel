package com.Hotels_System.ProjectHotel.exception;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException(){
        super("Room does not exist");
    }
}
