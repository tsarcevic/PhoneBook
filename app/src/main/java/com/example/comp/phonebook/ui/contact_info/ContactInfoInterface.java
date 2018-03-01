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
    }

    interface Presenter {

        void setView(View view);

        void sendContactId(int intExtra);
    }
}
