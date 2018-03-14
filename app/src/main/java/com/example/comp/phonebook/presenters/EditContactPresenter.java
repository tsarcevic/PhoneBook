package com.example.comp.phonebook.presenters;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.comp.phonebook.data.Contact;
import com.example.comp.phonebook.database.DatabaseInterface;
import com.example.comp.phonebook.image.ImageUtils;
import com.example.comp.phonebook.ui.edit_contact.EditContactInterface;
import com.example.comp.phonebook.utils.Constants;
import com.example.comp.phonebook.utils.StringUtils;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by COMP on 26.2.2018..
 */

public class EditContactPresenter implements EditContactInterface.Presenter {

    private EditContactInterface.View view;

    private DatabaseInterface databaseInterface;

    public EditContactPresenter(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public void setView(EditContactInterface.View view) {
        this.view = view;
    }

    @Override
    public void sendContactId(int id) {
        fetchData(id);
    }

    private void fetchData(int id) {
        Contact contact = databaseInterface.getContactInfo(id);

        if (contact != null) {
            view.showContactName(contact.getName());
            view.showContactNumber(contact.getPhoneNumber());
            view.showContactAddress(contact.getAddress());
            view.showContactPicture(ImageUtils.convertByteToBitmapPicture(contact.getPicture()));
        }
    }

    @Override
    public void onSaveContactClicked(String name, String number, String address, Drawable photo) {
        boolean isEmpty = false;

        if (!StringUtils.checkIfStringIsNotEmpty(name)) {
            view.contactNameError();
            isEmpty = true;
        }

        if (!StringUtils.checkIfStringIsNotEmpty(number)) {
            view.contactNumberError();
            isEmpty = true;
        }

        if (!StringUtils.checkIfStringIsNotEmpty(address)) {
            view.contactAddressError();
            isEmpty = true;
        }

        if (photo == null) {
            view.contactPictureError();
            isEmpty = true;
        }

        if (!isEmpty) {
            editContact(name, number, address, photo);
        }
    }

    private void editContact(String name, String number, String address, Drawable photo) {
        byte[] bytePhoto = ImageUtils.convertDrawablePictureToByte(photo);

        databaseInterface.updateContact(name, number, address, bytePhoto);
        view.navigateBack();
    }

    @Override
    public void checkContactNumberInput(CharSequence number) {
        if(number.length()<9 || number.length() > 20)
        {
            view.showContactNumberLengthError();
            view.showContactNumberUnderlineError();
        } else {
            view.hideContactNumberError();
            view.hideContactNumberUnderlineError();
        }
    }

    @Override
    public void onContactNumberLostFocus() {
        view.showContactNumberUnderlineHintColor();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, ContentResolver contentResolver) {
        Bitmap personPicture;

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.LOAD_IMAGE_RESULT:
                    Uri pickedPicture = data.getData();

                    try {
                        personPicture = MediaStore.Images.Media.getBitmap(contentResolver, pickedPicture);
                        view.showContactPicture(personPicture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.CAPTURE_IMAGE_RESULT:
                    personPicture = (Bitmap) data.getExtras().get("data");
                    view.showContactPicture(personPicture);
            }
        }
    }

    @Override
    public void onGalleryClicked() {
        view.openGallery();
    }

    @Override
    public void onCameraClicked() {
        view.openCamera();
    }

    @Override
    public void onBackClicked() {
        view.navigateBack();
    }
}
