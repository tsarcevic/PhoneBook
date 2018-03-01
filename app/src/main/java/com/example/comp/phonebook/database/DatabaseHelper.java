package com.example.comp.phonebook.database;

import com.example.comp.phonebook.data.Contact;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by COMP on 25.2.2018..
 */

public class DatabaseHelper {

    private static DatabaseHelper databaseHelper;

    private Realm realm;

    private DatabaseHelper(Realm realm) {
        this.realm = realm;
    }

    public static DatabaseHelper getDatabaseInstance() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(Realm.getDefaultInstance());
        }

        return databaseHelper;
    }

    public void addContact(Contact contact) {
        if (contact != null) {
            int id;

            realm.beginTransaction();

            if (realm.where(Contact.class).count() == 0) {
                id = 0;
            } else {
                id = realm.where(Contact.class).max("id").intValue() + 1;
            }

            contact.setId(id);

            realm.copyToRealm(contact);

            realm.commitTransaction();
        }
    }

    public List<Contact> getAllContacts() {
        return realm.copyFromRealm(realm.where(Contact.class).findAll());
    }

    public Contact getContact(int id) {
        return realm.copyFromRealm(realm.where(Contact.class).equalTo("id", id).findFirst());
    }

    public void deleteContact(int id) {
        if (id != 0) {
            realm.beginTransaction();

            Contact deletedContact = realm.where(Contact.class).equalTo("id", id).findFirst();

            if (deletedContact != null) {
                deletedContact.deleteFromRealm();
            }

            realm.commitTransaction();
        }
    }

    public List<Contact> findContacts(String name) {
        List<Contact> contactList = new ArrayList<>();

        if (name != null) {
            realm.beginTransaction();

            contactList = realm.copyFromRealm(realm.where(Contact.class).contains("name", name).findAll());

            realm.commitTransaction();
        }

        return contactList;
    }
}
