package com.example.comp.phonebook.ui.edit_contact;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by COMP on 26.2.2018..
 */

public interface EditContactInterface {

    interface View {

        void showContactName(String name);

        void showContactNumber(String phoneNumber);

        void showContactAddress(String address);

        void showContactPicture(Bitmap picture);

        void contactNameError();

        void contactNumberError();

        void contactAddressError();

        void contactPictureError();

        void openGallery();

        void openCamera();

        void navigateBack();
    }

    interface Presenter {

        void setView(View view);

        void onSaveContactClicked(String name, String number, String address, Drawable drawable);

        void onGalleryClicked();

        void onCameraClicked();

        void onActivityResult(int requestCode, int resultCode, Intent data, ContentResolver contentResolver);

        void onBackClicked();

        void sendContactId(int id);
    }
}
