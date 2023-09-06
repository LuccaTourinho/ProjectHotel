package com.Hotels_System.ProjectHotel.repository;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
}
