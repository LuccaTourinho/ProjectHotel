package com.Hotels_System.ProjectHotel.factory;

import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.Hotels_System.ProjectHotel.dto.room.DTORoom;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomComplete;
import org.springframework.stereotype.Component;

@Component
public class RoomFactory {
        public Room convertDTORoomToRoom(DTORoom dtoRoom) {
            Room room = new Room(dtoRoom);
            return room;
        }

        public DTORoomComplete convertRoomToDTORoomComplete(Room room) {
            DTORoomComplete dtoRoomComplete = new DTORoomComplete(room);
            return dtoRoomComplete;
        }
}
