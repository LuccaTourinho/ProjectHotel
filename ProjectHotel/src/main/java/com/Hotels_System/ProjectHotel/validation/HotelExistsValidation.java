package com.Hotels_System.ProjectHotel.validation;

import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelExistsValidation {

    @Autowired private HotelRepository repository;

    public Boolean isHotelExists(Long hotelId) {
        return repository.existsById(hotelId);
    }
}
