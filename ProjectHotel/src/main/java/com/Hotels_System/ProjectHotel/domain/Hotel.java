package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @Column(name = "Name", unique = true)
    @NotBlank
    @Size(max = 50)
    private String name;

    @Column(name = "Address")
    @NotBlank
    @Embedded
    private Address address;

    @Embedded
    private Quality quality;

    @Embedded
    private Contacts contacts;
}
