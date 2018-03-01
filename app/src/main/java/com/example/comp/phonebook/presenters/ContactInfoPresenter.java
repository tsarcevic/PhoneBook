package com.example.comp.phonebook.presenters;

import com.example.comp.phonebook.data.Contact;
import com.example.comp.phonebook.database.DatabaseInterface;
import com.example.comp.phonebook.image.ImageUtils;
import com.example.comp.phonebook.ui.contact_info.ContactInfoInterface;

/**
 * Created by COMP on 28.2.2018..
 */

public class ContactInfoPresenter implements ContactInfoInterface.Presenter {

    private ContactInfoInterface.View view;

    private DatabaseInterface databaseInterface;

    public ContactInfoPresenter(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public void setView(ContactInfoInterface.View view) {
        this.view = view;
    }

    @Override
    public void sendContactId(int intExtra) {
        if (intExtra == -1) {
            view.showNoDataInfo();
        } else {
            getContactInfo(intExtra);
        }
    }

    private void getContactInfo(int intExtra) {
        Contact contact = databaseInterface.getContactInfo(intExtra);

        if (contact != null) {
            view.showContactPhoto(ImageUtils.convertByteToBitmapPicture(contact.getPicture()));
            view.showContactName(contact.getName());
            view.showContactNumber(contact.getPhoneNumber());
            view.showContactAddress(contact.getAddress());
        } else {
            view.showNoDataInfo();
        }
    }
}
