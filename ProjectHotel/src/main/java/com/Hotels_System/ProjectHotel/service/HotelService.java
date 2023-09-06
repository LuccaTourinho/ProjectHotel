package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotelComplete;
import com.Hotels_System.ProjectHotel.exception.HotelNotFoundException;
import com.Hotels_System.ProjectHotel.exception.HotelUniqueNameException;
import com.Hotels_System.ProjectHotel.factory.HotelFactory;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import com.Hotels_System.ProjectHotel.util.HotelUpdater;
import com.Hotels_System.ProjectHotel.validation.HotelUniqueNameValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelFactory hotelFactory;

    @Autowired
    private HotelUniqueNameValidation hotelUniqueNameValidation;

    @Transactional
    public Hotel register(DTOHotel dtoHotel) throws HotelUniqueNameException {
        if (!hotelUniqueNameValidation.isHotelNameUnique(dtoHotel.name())) {
            throw new HotelUniqueNameException();
        }
        Hotel hotel = hotelFactory.convertDTOHotelToHotel(dtoHotel);
        return repository.save(hotel);
    }

    public DTOHotelComplete detailHotel(Long id) throws HotelNotFoundException{
        var hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException());
        return hotelFactory.convertHotelToDTOComplete(hotel);
    }

    public Page<DTOHotelComplete> listHotels(Pageable pageable){
        Page<Hotel> hoteisPage = repository.findAll(pageable);
        return hoteisPage.map(hotelFactory::convertHotelToDTOComplete);
    }

    @Transactional
    public DTOHotelComplete updateHotel(DTOHotelComplete dtoHotelUpdate) throws HotelNotFoundException, HotelUniqueNameException{
        var hotel = repository.findById(dtoHotelUpdate.id()).orElseThrow(() -> new HotelNotFoundException());
        if (!hotelUniqueNameValidation.isHotelNameUnique(dtoHotelUpdate.name())) {
            throw new HotelUniqueNameException();
        }
        HotelUpdater hotelUpdated = new HotelUpdater(hotel)
                .name(dtoHotelUpdate.name())
                .address(dtoHotelUpdate.address())
                .quality(dtoHotelUpdate.quality())
                .contacts(dtoHotelUpdate.contacts());
        repository.save(hotelUpdated.finishUpdater());
        return hotelFactory.convertHotelToDTOComplete(hotelUpdated.finishUpdater());
    }

    @Transactional
    public void deleteHotel(Long id) throws HotelNotFoundException{
        var hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException());
        repository.delete(hotel);
    }

}
