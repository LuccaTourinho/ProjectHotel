package com.Hotels_System.ProjectHotel.controller;

import com.Hotels_System.ProjectHotel.domain.Room;
import com.Hotels_System.ProjectHotel.dto.DTORoom;
import com.Hotels_System.ProjectHotel.dto.DTORoomUpdate;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.exception.RoomNotFoundException;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import com.Hotels_System.ProjectHotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @Autowired
    private HotelRepository repository;

    @PostMapping("/register")
    public ResponseEntity<Room> registerRoom(@RequestBody DTORoom dtoRoom, UriComponentsBuilder uriBuilder) throws HotelNotFoundException{

        var createdRoom = service.register(dtoRoom);

        URI uri = uriBuilder.path("/rooms/{id}").buildAndExpand(createdRoom.getId()).toUri();

        return ResponseEntity.created(uri).body(createdRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomDetails(@PathVariable Long id) throws RoomNotFoundException {
        var room = service.detailRoom(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<Room>> listAvailableRooms(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Optional<Page<Room>> availableRooms = service.listRoomAvailable(pageable);
        return availableRooms.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/notavailable")
    public ResponseEntity<Page<Room>> listNotAvailableRooms(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        Optional<Page<Room>> notAvailableRooms = service.listRoomNotAvailable(pageable);
        return notAvailableRooms.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody DTORoomUpdate dtoRoomUpdate)
            throws RoomNotFoundException {
        var updatedRoom = service.updateRoom(dtoRoomUpdate);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) throws RoomNotFoundException {
        service.deleteRoom(id);
        return new ResponseEntity<>("Room deleted", HttpStatus.OK);
    }
}
