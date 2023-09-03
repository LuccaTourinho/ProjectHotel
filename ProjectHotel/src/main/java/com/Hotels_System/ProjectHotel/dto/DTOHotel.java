package com.Hotels_System.ProjectHotel.dto;

import com.Hotels_System.ProjectHotel.domain.Quality;

public record DTOHotel(
        String nome,
        DTOAddress address,
        Quality quality,
        DTOContacts contacts
        ) {
}
