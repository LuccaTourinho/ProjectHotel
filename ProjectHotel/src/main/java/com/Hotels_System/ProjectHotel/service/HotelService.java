package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotelComplete;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
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
        var hotel = new Hotel(dtoHotel);
        return repository.save(hotel);
    }

    public DTOHotelComplete detailHotel(Long id) throws HotelNotFoundException{
        var hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException());
        var detailHotel = new DTOHotelComplete(hotel);
        return detailHotel;
    }

    public Page<DTOHotelComplete> listHotels(Pageable pageable){
        Page<Hotel> hoteisPage = repository.findAll(pageable);
        Page<DTOHotelComplete> dtoHotelCompletePage = hoteisPage.map(this::convertToDTO);
        return dtoHotelCompletePage;
    }

    private DTOHotelComplete convertToDTO(Hotel hotel) {
        DTOHotelComplete dtoHotel = new DTOHotelComplete(hotel);
        return dtoHotel;
    }

    @Transactional
    public DTOHotelComplete updateHotel(DTOHotelComplete dtoHotelUpdate) throws HotelNotFoundException{
        var hotel = repository.findById(dtoHotelUpdate.id()).orElseThrow(() -> new HotelNotFoundException());
        HotelUpdater hotelUpdated = new HotelUpdater(hotel)
                .name(dtoHotelUpdate.name())
                .address(dtoHotelUpdate.address())
                .quality(dtoHotelUpdate.quality())
                .contacts(dtoHotelUpdate.contacts());
        repository.save(hotelUpdated.finishUpdater());
        return new DTOHotelComplete(hotelUpdated.finishUpdater());
    }

    @Transactional
    public void deleteHotel(Long id) throws HotelNotFoundException{
        var hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException());
        repository.delete(hotel);
    }

}
