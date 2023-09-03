package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.Room;
import com.Hotels_System.ProjectHotel.dto.DTORoom;
import com.Hotels_System.ProjectHotel.dto.DTORoomUpdate;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.exception.RoomNotFoundException;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import com.Hotels_System.ProjectHotel.repository.RoomRepository;
import com.Hotels_System.ProjectHotel.util.RoomUpdater;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private HotelRepository hotelrepository;

    @Transactional
    public Room register(DTORoom dtoRoom) throws HotelNotFoundException{
        if(isHotelExists(dtoRoom.hotel().getId())){
            var room = new Room(dtoRoom);
            return repository.save(room);
        }else{
            throw new HotelNotFoundException();
        }
    }

    public Boolean isHotelExists(Long hotelId){
        return hotelrepository.existsById(hotelId);
    }

    public Room detailRoom(Long id) throws RoomNotFoundException {
        var room = repository.findById(id).orElseThrow(() -> new RoomNotFoundException());
        return room;
    }

    public Optional<Page<Room>> listRoomAvailable(Pageable pageable){
        return repository.findByAvailableTrue(pageable);
    }

    public Optional<Page<Room>> listRoomNotAvailable(Pageable pageable) {
        return repository.findByAvailableFalse(pageable);
    }

    @Transactional
    public Room updateRoom(DTORoomUpdate dtoRoomUpdate) throws RoomNotFoundException{
        var room = repository.findById(dtoRoomUpdate.id()).orElseThrow(() -> new RoomNotFoundException());

        RoomUpdater roomUpdated = new RoomUpdater(room)
                .capacity(dtoRoomUpdate.capacity())
                .number(dtoRoomUpdate.number())
                .avalible(dtoRoomUpdate.available());

        return repository.save(roomUpdated.finishUpdater());
    }

    @Transactional
    public void deleteRoom(Long id) throws RoomNotFoundException{
        var room = repository.findById(id).orElseThrow(() -> new RoomNotFoundException());
        repository.delete(room);
    }
}
