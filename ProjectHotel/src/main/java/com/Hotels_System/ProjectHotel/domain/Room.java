package com.Hotels_System.ProjectHotel.domain;

import com.Hotels_System.ProjectHotel.dto.DTORoom;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "room")
@Table(name = "Rooms",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_hotel_number", columnNames = {"hotel_id", "number"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "pk_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "hotel_id") @NotNull
    private Hotel hotel;

    @NotNull @Column(name = "Capacity") @Enumerated(EnumType.STRING)
    private Capacity capacity;

    @NotNull @Column(name = "Number_room")
    private Integer number;

    @Column (name = "Available", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean available;

    public Room(DTORoom dtoRoom) {
        this.hotel = dtoRoom.hotel();
        this.capacity = Capacity.valueOf(dtoRoom.capacity());
        this.number = dtoRoom.number();
    }
}
