package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "hotel")
@Table(name = "Hoteis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends PrimaryKey{

    @Column(name = "Name_hotel", unique = true)
    private String name;

    @Embedded
    private Address address;

    @Column(name = "Quality") @Embedded
    private Quality quality;

    @Embedded
    private Contacts contacts;
}
