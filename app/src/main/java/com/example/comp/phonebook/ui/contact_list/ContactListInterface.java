package com.example.comp.phonebook.ui.contact_list;

import com.example.comp.phonebook.data.Contact;

import java.util.List;

/**
 * Created by COMP on 26.2.2018..
 */

public interface ContactListInterface {

    interface View {

        void showContacts(List<Contact> contactList);

        void showNoDataInfo();

        void hideNoDataInfo();

        void hideRecyclerView();

        void showRecyclerView();

        void navigateToContactAdding();

        void navigateToContactInfo(int id);
    }

    interface Presenter {

        void setView(View view);

        void viewReady();

        void onAddButtonClicked();

        void onContactClicked(int id);
    }
}
