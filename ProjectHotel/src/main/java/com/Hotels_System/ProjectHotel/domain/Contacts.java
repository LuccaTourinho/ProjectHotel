package com.Hotels_System.ProjectHotel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
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
public class Contacts{

    @Column(name = "Email", unique = true) @NotBlank @Email
    private String email;

    @Column(name = "Telefone", unique = true) @NotBlank @Pattern(regexp = "\\+\\d{1,3} \\(\\d{1,3}\\) \\d{5}-\\d{4}")
    private String phone;
}
