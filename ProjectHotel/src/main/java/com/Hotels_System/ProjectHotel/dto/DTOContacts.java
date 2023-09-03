package com.Hotels_System.ProjectHotel.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DTOContacts(
        @JsonAlias({"email"}) String email,
        @JsonAlias({"phone"}) String phone
){
}
