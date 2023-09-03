package com.Hotels_System.ProjectHotel.controller;


import com.Hotels_System.ProjectHotel.domain.Hotel;
import com.Hotels_System.ProjectHotel.dto.DTOHotel;
import com.Hotels_System.ProjectHotel.dto.DTOHotelUpdate;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.service.HotelService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService service;

    @PostMapping("/register")
    public ResponseEntity<Hotel> register(@RequestBody @Valid DTOHotel dtoHotel, UriComponentsBuilder uriComponentsBuilder ){
        var hotel = service.register(dtoHotel);

        URI uri = uriComponentsBuilder.path("/hotel/{id}").buildAndExpand(hotel.getId()).toUri();

        return ResponseEntity.created(uri).body(hotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> detailHotel(@PathVariable Long id) throws HotelNotFoundException{
        var hotel = service.detailHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Hotel>> listHotel(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Hotel> hotelPage = service.listHotels(pageable);
        return ResponseEntity.ok(hotelPage);
    }

    @PutMapping("/update")
    public ResponseEntity<Hotel> updateHotel(@RequestBody @Valid DTOHotelUpdate dtoHotelUpdate) throws HotelNotFoundException{
        var hotelupdated = service.updateHotel(dtoHotelUpdate);
        return new ResponseEntity<>(hotelupdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) throws HotelNotFoundException{
        service.deleteHotel(id);
        return new ResponseEntity<>("Hotel deleted", HttpStatus.OK);
    }
}
