package com.Hotels_System.ProjectHotel.controller;

import com.Hotels_System.ProjectHotel.dto.room.DTORoom;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomComplete;
import com.Hotels_System.ProjectHotel.dto.room.DTORoomUpdate;
import com.Hotels_System.ProjectHotel.infra.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.infra.exception.HotelRoomNumberException;
import com.Hotels_System.ProjectHotel.infra.exception.RoomNotFoundException;
import com.Hotels_System.ProjectHotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/register")
    public ResponseEntity<DTORoomComplete> registerRoom(@RequestBody DTORoom dtoRoom, UriComponentsBuilder uriBuilder) throws HotelNotFoundException, DataIntegrityViolationException, HotelRoomNumberException {

        var createdRoom = service.register(dtoRoom);

        URI uri = uriBuilder.path("/rooms/get/{id}").buildAndExpand(createdRoom.id()).toUri();

        return ResponseEntity.created(uri).body(createdRoom);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DTORoomComplete> getRoomDetails(@PathVariable Long id) throws RoomNotFoundException {
        var room = service.detailRoom(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<DTORoomComplete>> listAvailableRooms(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<DTORoomComplete> availableRooms = service.listRoomAvailable(pageable);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/notavailable")
    public ResponseEntity<Page<DTORoomComplete>> listNotAvailableRooms(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<DTORoomComplete> notAvailableRooms = service.listRoomNotAvailable(pageable);
        return ResponseEntity.ok(notAvailableRooms);
    }

    @PutMapping("/update")
    public ResponseEntity<DTORoomComplete> updateRoom(@RequestBody DTORoomUpdate dtoRoomUpdate) throws RoomNotFoundException, DataIntegrityViolationException, HotelRoomNumberException {
        var updatedRoom = service.updateRoom(dtoRoomUpdate);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) throws RoomNotFoundException {
        service.deleteRoom(id);
        return new ResponseEntity<>("Room deleted", HttpStatus.OK);
    }
}
