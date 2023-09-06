package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.room.Room;
import com.Hotels_System.ProjectHotel.dto.room.DTORoom;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomComplete;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomUpdate;
import com.Hotels_System.ProjectHotel.infra.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.infra.exception.HotelRoomNumberException;
import com.Hotels_System.ProjectHotel.infra.exception.RoomNotFoundException;
import com.Hotels_System.ProjectHotel.factory.RoomFactory;
import com.Hotels_System.ProjectHotel.repository.RoomRepository;
import com.Hotels_System.ProjectHotel.util.RoomUpdater;
import com.Hotels_System.ProjectHotel.validation.HotelExistsValidation;
import com.Hotels_System.ProjectHotel.validation.RoomNumberUniqueValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired private RoomRepository repository;
    @Autowired private HotelExistsValidation hotelExistValidation;
    @Autowired private RoomNumberUniqueValidation roomNumberUniqueValidation;
    @Autowired private RoomFactory roomFactory;

    @Transactional
    public DTORoomComplete register(DTORoom dtoRoom) throws HotelNotFoundException, DataIntegrityViolationException, HotelRoomNumberException {
        if(hotelExistValidation.isHotelExists(dtoRoom.hotel().id())){
            if(roomNumberUniqueValidation.isRoomNumberUnique(dtoRoom.hotel().id(), dtoRoom.number())){
                return roomFactory.convertRoomToDTORoomComplete(repository.save(roomFactory.convertDTORoomToRoom(dtoRoom)));
            }else{
                throw new HotelRoomNumberException();
            }
        }
        else{
            throw new HotelNotFoundException();
        }
    }

    public DTORoomComplete detailRoom(Long id) throws RoomNotFoundException {
        var room = repository.findById(id).orElseThrow(() -> new RoomNotFoundException());
        return roomFactory.convertRoomToDTORoomComplete(room);
    }

    public Page<DTORoomComplete> listRoomAvailable(Pageable pageable){
        Page<Room> roomList = repository.findByAvailableTrue(pageable);
        return roomList.map(roomFactory::convertRoomToDTORoomComplete);
    }

    public Page<DTORoomComplete> listRoomNotAvailable(Pageable pageable) {
        Page<Room> roomList = repository.findByAvailableFalse(pageable);
        return roomList.map(roomFactory::convertRoomToDTORoomComplete);
    }

    @Transactional
    public DTORoomComplete updateRoom(DTORoomUpdate dtoRoomUpdate) throws RoomNotFoundException,DataIntegrityViolationException, HotelRoomNumberException {
        var room = repository.findById(dtoRoomUpdate.id()).orElseThrow(() -> new RoomNotFoundException());
        if (roomNumberUniqueValidation.isRoomNumberUnique(room.getHotel().getId(), dtoRoomUpdate.number())){
            RoomUpdater roomUpdated = new RoomUpdater(room)
                    .capacity(dtoRoomUpdate.capacity())
                    .number(dtoRoomUpdate.number())
                    .avalible(dtoRoomUpdate.available());
            return roomFactory.convertRoomToDTORoomComplete(repository.save(roomUpdated.finishUpdater()));
        }else {
            throw new HotelRoomNumberException();
        }
    }

    @Transactional
    public void deleteRoom(Long id) throws RoomNotFoundException{
        var room = repository.findById(id).orElseThrow(() -> new RoomNotFoundException());
        repository.delete(room);
    }
}
