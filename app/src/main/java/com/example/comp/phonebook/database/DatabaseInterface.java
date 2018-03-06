package com.example.comp.phonebook.database;

import com.example.comp.phonebook.data.Contact;

import java.util.List;

/**
 * Created by COMP on 26.2.2018..
 */

public interface DatabaseInterface {

    List<Contact> getData();

    void addContact(String name, String number, String address, byte[] photo);

    Contact getContactInfo(int intExtra);

    void deleteContact(int id);
}
