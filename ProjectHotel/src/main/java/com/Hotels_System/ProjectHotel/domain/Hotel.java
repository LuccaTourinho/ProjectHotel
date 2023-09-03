package com.Hotels_System.ProjectHotel.domain;

import com.Hotels_System.ProjectHotel.dto.DTOHotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "hotel")
@Table(name = "Hoteis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "pk_ID")
    private Long id;

    @Column(name = "Name_hotel", unique = true) @NotBlank
    private String name;

    @Embedded
    private Address address;

    @Column(name = "Quality") @Enumerated(EnumType.STRING)
    private Quality quality;

    @Embedded
    private Contacts contacts;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;

    public Hotel(DTOHotel dtoHotel) {
        this.name = dtoHotel.name();
        this.address = new Address(dtoHotel.address());
        this.quality = Quality.valueOf(dtoHotel.quality());
        this.contacts = new Contacts(dtoHotel.contacts());
    }
}
