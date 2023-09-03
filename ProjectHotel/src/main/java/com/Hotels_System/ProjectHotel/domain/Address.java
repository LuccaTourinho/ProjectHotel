package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address{

    @Column(name = "Country") @NotBlank @Pattern(regexp = "^[A-Z]{2}$")
    private String country;

    @Column(name = "State_code") @NotBlank @Pattern(regexp = "^[A-Z]{2}$")
    private String state;

    @Column(name = "City") @NotBlank
    private String city;
}
