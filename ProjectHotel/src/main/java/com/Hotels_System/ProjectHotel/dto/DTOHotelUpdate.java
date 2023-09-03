package com.Hotels_System.ProjectHotel.dto;

import com.Hotels_System.ProjectHotel.domain.Quality;

public record DTOHotelUpdate(
        Long id,
        String name,
        DTOAddress address,
        String quality,
        DTOContacts contacts
){
}
