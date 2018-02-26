package com.example.comp.phonebook.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by COMP on 23.2.2018..
 */

public class Contact extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String phoneNumber;
    private byte[] picture;
    private String address;

    public Contact() {
    }

    public Contact(int id, String name, String phoneNumber, byte[] picture, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getAddress() {
        return address;
    }
}
