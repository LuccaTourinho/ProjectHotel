package com.Hotels_System.ProjectHotel.infra.exception;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException(){
        super("Room does not exist");
    }
}
