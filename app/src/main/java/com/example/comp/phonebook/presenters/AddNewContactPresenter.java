package com.example.comp.phonebook.presenters;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.comp.phonebook.database.DatabaseInterface;
import com.example.comp.phonebook.image.ImageUtils;
import com.example.comp.phonebook.ui.add_new_contact.AddNewContactInterface;
import com.example.comp.phonebook.utils.Constants;
import com.example.comp.phonebook.utils.StringUtils;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by COMP on 26.2.2018..
 */

public class AddNewContactPresenter implements AddNewContactInterface.Presenter {

    private AddNewContactInterface.View view;

    private DatabaseInterface databaseInterface;

    public AddNewContactPresenter(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    @Override
    public void setView(AddNewContactInterface.View view) {
        this.view = view;
    }

    @Override
    public void addContactClicked(String name, String number, String address, Drawable photo) {
        boolean isEmpty = false;

        if (!StringUtils.checkIfStringIsNotEmpty(name)) {
            view.showContactNameMissingError();
            isEmpty = true;
        }

        if (!StringUtils.checkIfStringIsNotEmpty(number)) {
            view.showContactNumberMissingError();
            isEmpty = true;
        }

        if (!StringUtils.checkIfStringIsNotEmpty(address)) {
            view.showContactAddressMissingError();
            isEmpty = true;
        }

        if (photo == null) {
            view.showContactPictureMissingError();
            isEmpty = true;
        }

        if (!isEmpty) {
            addNewContact(name, number, address, photo);
        }
    }

    private void addNewContact(String name, String number, String address, Drawable photo) {
        byte[] bytePhoto = ImageUtils.convertDrawablePictureToByte(photo);

        databaseInterface.addContact(name, number, address, bytePhoto);
        view.articleAdded();
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
    public void onBackClicked() {
        view.navigateToPreviousScreen();
    }

    @Override
    public void onArticleAdded() {
        view.navigateToPreviousScreen();
    }

    @Override
    public void onCameraClicked() {
        view.openCamera();
    }

    @Override
    public void onGalleryClicked() {
        view.openGallery();
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
                        view.showImageBox();
                        view.showContactPhoto(personPicture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.CAPTURE_IMAGE_RESULT:
                    personPicture = (Bitmap) data.getExtras().get("data");
                    view.showImageBox();
                    view.showContactPhoto(personPicture);
            }
        }
    }
}
