package com.Hotels_System.ProjectHotel.util;

import com.Hotels_System.ProjectHotel.domain.contacts.Contacts;

public class ContactsUpdater {

    private final Contacts contacts;

    public ContactsUpdater(Contacts contacts){
        this.contacts = contacts;
    }

    public ContactsUpdater email(String email){

        if(email != null){
            contacts.setEmail(email);
        }
        return this;
    }

    public ContactsUpdater phone(String phone){

        if(phone != null){
            contacts.setPhone(phone);
        }
        return this;
    }

    public Contacts finishUpdater(){
        return contacts;
    }
}
