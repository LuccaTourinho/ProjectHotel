package com.Hotels_System.ProjectHotel.service;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import com.Hotels_System.ProjectHotel.dto.Hotel.DTOHotel;
import com.Hotels_System.ProjectHotel.dto.address.DTOAddress;
import com.Hotels_System.ProjectHotel.dto.contacts.DTOContacts;
import com.Hotels_System.ProjectHotel.factory.HotelFactory;
import com.Hotels_System.ProjectHotel.infra.exception.HotelUniqueNameException;
import com.Hotels_System.ProjectHotel.repository.HotelRepository;
import com.Hotels_System.ProjectHotel.validation.HotelUniqueNameValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @MockBean
    private HotelRepository hotelRepository;

    @MockBean
    private HotelFactory hotelFactory;

    @MockBean
    private HotelUniqueNameValidation hotelUniqueNameValidation;

    @Test
    void register() throws HotelUniqueNameException {


        when(hotelRepository.save(any(Hotel.class))).thenReturn(new Hotel());
        when(hotelUniqueNameValidation.isHotelNameUnique(anyString())).thenReturn(true);

        DTOHotel dtoHotel = new DTOHotel("Nome do Hotel",
                            new DTOAddress("BR", "SP", "São Paulo"),
                            "STANDARD",
                            new DTOContacts("email@example.com", "+1 (123) 45678"));
        Hotel resultado = hotelService.register(dtoHotel);

        verify(hotelRepository).save(any(Hotel.class));
        verify(hotelUniqueNameValidation).isHotelNameUnique(anyString());

    }

    @Test
    void detailHotel() {
    }

    @Test
    void listHotels() {
    }

    @Test
    void updateHotel() {
    }

    @Test
    void deleteHotel() {
    }
}