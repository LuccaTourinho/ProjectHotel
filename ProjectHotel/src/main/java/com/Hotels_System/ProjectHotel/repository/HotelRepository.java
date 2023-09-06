package com.Hotels_System.ProjectHotel.repository;

import com.Hotels_System.ProjectHotel.domain.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
    @Query(value = "SELECT COUNT(*) > 0 FROM Hoteis WHERE Name_hotel = :hotelName", nativeQuery = true)
    boolean existsByName(@Param("hotelName") String hotelName);
}
