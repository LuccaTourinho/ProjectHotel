package com.Hotels_System.ProjectHotel.validation;

import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelUniqueNameValidation {

    @Autowired
    private HotelRepository hotelRepository;

    public boolean isHotelNameUnique(String hotelName) {
        return !hotelRepository.existsByName(hotelName);
    }
}
