package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.Hotels_System.ProjectHotel.dto.room.DTORoom;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomComplete;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomUpdate;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.exception.HotelRoomNumberException;
import com.Hotels_System.ProjectHotel.exception.RoomNotFoundException;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import com.Hotels_System.ProjectHotel.repository.RoomRepository;
import com.Hotels_System.ProjectHotel.util.RoomUpdater;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public DTORoomComplete register(DTORoom dtoRoom) throws HotelNotFoundException, DataIntegrityViolationException, HotelRoomNumberException {
        if(isHotelExists(dtoRoom.hotel().id())){
            if(isRoomNumberUnique(dtoRoom.hotel().id(), dtoRoom.number())){
                var room = new Room(dtoRoom);
                room = repository.save(room);
                var roomComplete = new DTORoomComplete(room);
                return roomComplete;
            }else{
                throw new HotelRoomNumberException();
            }
        }
        else{
            throw new HotelNotFoundException();
        }
    }

    public Boolean isHotelExists(Long hotelId){
        return hotelrepository.existsById(hotelId);
    }

    public DTORoomComplete detailRoom(Long id) throws RoomNotFoundException {
        var room = repository.findById(id).orElseThrow(() -> new RoomNotFoundException());
        var dtoRoomComplete = new DTORoomComplete(room);
        return dtoRoomComplete;
    }

    public Page<DTORoomComplete> listRoomAvailable(Pageable pageable){
        Page<Room> roomList = repository.findByAvailableTrue(pageable);
        Page<DTORoomComplete> dtoRoomCompletes = roomList.map(this::convertToDTO);
        return dtoRoomCompletes;
    }

    public Page<DTORoomComplete> listRoomNotAvailable(Pageable pageable) {
        Page<Room> roomList = repository.findByAvailableFalse(pageable);
        Page<DTORoomComplete> dtoRoomCompletes = roomList.map(this::convertToDTO);
        return dtoRoomCompletes;
    }

    private DTORoomComplete convertToDTO(Room room){
        DTORoomComplete dtoRoomComplete = new DTORoomComplete(room);
        return dtoRoomComplete;
    }

    @Transactional
    public DTORoomComplete updateRoom(DTORoomUpdate dtoRoomUpdate) throws RoomNotFoundException,DataIntegrityViolationException, HotelRoomNumberException {
        var room = repository.findById(dtoRoomUpdate.id()).orElseThrow(() -> new RoomNotFoundException());
        if (isRoomNumberUnique(room.getHotel().getId(), dtoRoomUpdate.number())){
            RoomUpdater roomUpdated = new RoomUpdater(room)
                    .capacity(dtoRoomUpdate.capacity())
                    .number(dtoRoomUpdate.number())
                    .avalible(dtoRoomUpdate.available());

            repository.save(roomUpdated.finishUpdater());
            DTORoomComplete dtoRoomComplete = new DTORoomComplete(roomUpdated.finishUpdater());
            return dtoRoomComplete;
        }else {
            throw new HotelRoomNumberException();
        }
    }

    private boolean isRoomNumberUnique(Long hotelId, Integer roomNumber) {
        Optional<Room> existingRoom = repository.findByHotelIdAndNumber(hotelId, roomNumber);
        return existingRoom.isEmpty();
    }

    @Transactional
    public void deleteRoom(Long id) throws RoomNotFoundException{
        var room = repository.findById(id).orElseThrow(() -> new RoomNotFoundException());
        repository.delete(room);
    }
}
