package com.Hotels_System.ProjectHotel.util;

import com.Hotels_System.ProjectHotel.domain.room.Capacity;
import com.Hotels_System.ProjectHotel.domain.room.Room;

public class RoomUpdater {

    private final Room room;

    public RoomUpdater(Room room){
        this.room = room;
    }

    public RoomUpdater capacity(String capacity){
        if(capacity != null) {
            room.setCapacity(Capacity.valueOf(capacity));
        }
        return this;
    }

    public RoomUpdater number(Integer number){
        if(number != null){
            room.setNumber(number);
        }
        return this;
    }

    public RoomUpdater avalible(Boolean available){
        if(available != null){
            room.setAvailable(available);
        }
        return this;
    }

    public Room finishUpdater(){return room;}
}
