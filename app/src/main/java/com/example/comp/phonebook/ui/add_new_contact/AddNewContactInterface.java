package com.example.comp.phonebook.ui.add_new_contact;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by COMP on 26.2.2018..
 */

public interface AddNewContactInterface {

    interface View {

        void showContactNameMissingError();

        void showContactNumberMissingError();

        void showContactAddressMissingError();

        void showContactPictureMissingError();

        void navigateToPreviousScreen();

        void articleAdded();

        void openCamera();

        void openGallery();

        void showContactPhoto(Bitmap personPicture);

        void showImageBox();

        void showContactNumberLengthError();

        void showContactNumberUnderlineError();

        void hideContactNumberError();

        void hideContactNumberUnderlineError();

        void showContactNumberUnderlineHintColor();
    }

    interface Presenter {

        void setView(View view);

        void addContactClicked(String name, String number, String address, Drawable drawable);

        void onBackClicked();

        void onArticleAdded();

        void onCameraClicked();

        void onGalleryClicked();

        void onActivityResult(int requestCode, int resultCode, Intent data, ContentResolver contentResolver);

        void checkContactNumberInput(CharSequence number);

        void onContactNumberLostFocus();
    }
}
