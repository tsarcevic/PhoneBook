package com.example.comp.phonebook.database;

import com.example.comp.phonebook.data.Contact;

import java.util.List;

/**
 * Created by COMP on 26.2.2018..
 */

public class DatabaseManager implements DatabaseInterface {

    private static DatabaseHelper dbHelper;
    private static DatabaseManager dbManager;

    public DatabaseManager() {
        dbHelper = DatabaseHelper.getDatabaseInstance();
    }

    public static DatabaseManager getDatabaseInstance() {
        if (dbManager == null) {
            dbManager = new DatabaseManager();
        }

        return dbManager;
    }

    @Override
    public List<Contact> getData() {
        return dbHelper.getAllContacts();
    }

    @Override
    public void addContact(String name, String number, String address, byte[] photo) {
        dbHelper.addContact(new Contact(name, number, photo, address));
    }

    @Override
    public Contact getContactInfo(int intExtra) {
        return dbHelper.getContact(intExtra);
    }

    @Override
    public void deleteContact(int id) {
        dbHelper.deleteContact(id);
    }

    @Override
    public void updateContact(String name, String number, String address, byte[] bytePhoto) {
        dbHelper.updateContact(new Contact(name, number, bytePhoto, address));
    }
}
