package com.Hotels_System.ProjectHotel.repository;

import com.Hotels_System.ProjectHotel.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Page<Room>> findByAvailableTrue(Pageable pageable);

    Optional<Page<Room>> findByAvailableFalse(Pageable pageable);
}
