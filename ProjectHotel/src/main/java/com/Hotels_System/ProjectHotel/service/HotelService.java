package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.Address;
import com.Hotels_System.ProjectHotel.domain.Contacts;
import com.Hotels_System.ProjectHotel.domain.Hotel;
import com.Hotels_System.ProjectHotel.dto.DTOHotel;
import com.Hotels_System.ProjectHotel.dto.DTOHotelUpdate;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import com.Hotels_System.ProjectHotel.util.AddressUpdater;
import com.Hotels_System.ProjectHotel.util.HotelUpdater;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Transactional
    public Hotel register(DTOHotel dtoHotel){

        var hotel = Hotel.builder()
                .name(dtoHotel.nome())
                .address(
                        Address.builder()
                                .country(dtoHotel.address().country())
                                .state(dtoHotel.address().state())
                                .city(dtoHotel.address().city())
                                .build()
                )
                .quality(dtoHotel.quality())
                .contacts(
                        Contacts.builder()
                                .email(dtoHotel.contacts().email())
                                .phone(dtoHotel.contacts().phone())
                                .build()
                )
                .build();
        return repository.save(hotel);
    }

    public Hotel detailHotel(Long id) throws HotelNotFoundException{

        var hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException());
        return hotel;
    }

    public Page<Hotel> listHotels(Pageable pageable){

        Page<Hotel> hoteisPage = repository.findAll(pageable);
        return hoteisPage;
    }

    @Transactional
    public Hotel updateHotel(DTOHotelUpdate dtoHotelUpdate) throws HotelNotFoundException{

        var hotel = repository.findById(dtoHotelUpdate.id()).orElseThrow(() -> new HotelNotFoundException());

        HotelUpdater hotelUpdated = new HotelUpdater(hotel)
                .name(dtoHotelUpdate.nome())
                .address(dtoHotelUpdate.address())
                .quality(dtoHotelUpdate.quality())
                .contacts(dtoHotelUpdate.contacts());

        return repository.save(hotelUpdated.finishUpdater());
    }

}
