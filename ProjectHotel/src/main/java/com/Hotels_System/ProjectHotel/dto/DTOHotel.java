package com.Hotels_System.ProjectHotel.dto;

import com.Hotels_System.ProjectHotel.domain.Address;
import com.Hotels_System.ProjectHotel.domain.Contacts;
import com.Hotels_System.ProjectHotel.domain.Quality;
import jakarta.validation.constraints.NotNull;

public record DTOHotel(
        String nome,
        DTOAdress dtoAdress,
        Quality quality,
        DTOContacts dtoContacts
        ) {
}
