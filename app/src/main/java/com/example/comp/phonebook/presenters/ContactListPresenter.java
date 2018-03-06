package com.example.comp.phonebook.presenters;

import com.example.comp.phonebook.data.Contact;
import com.example.comp.phonebook.database.DatabaseInterface;
import com.example.comp.phonebook.ui.contact_list.ContactListInterface;

import java.util.List;

/**
 * Created by COMP on 26.2.2018..
 */

public class ContactListPresenter implements ContactListInterface.Presenter {

    private ContactListInterface.View view;

    private DatabaseInterface databaseInterface;

    private List<Contact> contactList;

    public ContactListPresenter(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public void setView(ContactListInterface.View view) {
        this.view = view;
    }

    @Override
    public void onAddButtonClicked() {
        view.navigateToContactAdding();
    }

    @Override
    public void viewReady() {
        fetchData();
    }

    private void fetchData() {
        contactList = databaseInterface.getData();

        if (contactList != null) {
            view.showContacts(contactList);

            if (contactList.isEmpty()) {
                view.showNoDataInfo();
                view.hideRecyclerView();
            } else {
                view.hideNoDataInfo();
                view.showRecyclerView();
            }
        }
    }

    @Override
    public void onContactClicked(int id) {
        view.navigateToContactInfo(id);
    }

    @Override
    public void onContactLongClicked(int id) {
        view.showDeleteDialog(id);
    }

    @Override
    public void onDeleteContactClicked(int id) {
        databaseInterface.deleteContact(id);

        view.showDeleteContactMessage();
        fetchData();
    }
}
