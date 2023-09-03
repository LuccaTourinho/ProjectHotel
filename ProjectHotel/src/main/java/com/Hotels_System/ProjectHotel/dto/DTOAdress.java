package com.Hotels_System.ProjectHotel.dto;

import jakarta.validation.constraints.NotNull;

public record DTOAdress(
        String country,
        String state,
        String city
) {
}
