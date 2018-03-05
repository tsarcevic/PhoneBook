package com.example.comp.phonebook.ui.contact_info;

import android.graphics.Bitmap;

/**
 * Created by COMP on 27.2.2018..
 */

public interface ContactInfoInterface {

    interface View {

        void showNoDataInfo();

        void showContactPhoto(Bitmap bitmap);

        void showContactName(String name);

        void showContactNumber(String phoneNumber);

        void showContactAddress(String address);

        void navigateToContactCall(String phoneNumber);

        void navigateToEditContactScreen(int id);

        void navigateToPreviousScreen();

        void showDeleteDialog(int id);

        void navigateToMessageSend(String phoneNumber);
    }

    interface Presenter {

        void setView(View view);

        void sendContactId(int intExtra);

        void onContactNumberClicked();

        void onDeleteClicked();

        void onEditClicked();

        void onBackClicked();

        void onMessageClicked();
    }
}
