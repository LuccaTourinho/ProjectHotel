package com.Hotels_System.ProjectHotel.dto.contacts;

import com.Hotels_System.ProjectHotel.domain.contacts.Contacts;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DTOContacts(
        @JsonAlias({"email"}) String email,
        @JsonAlias({"phone"}) String phone
){
    public DTOContacts (Contacts contacts){
        this(contacts.getEmail(), contacts.getPhone());
    }
}
