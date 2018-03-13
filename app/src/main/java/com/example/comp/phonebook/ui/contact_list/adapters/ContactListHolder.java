package com.example.comp.phonebook.ui.contact_list.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.data.Contact;
import com.example.comp.phonebook.image.ImageUtils;
import com.example.comp.phonebook.interfaces.ContactClickListener;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by COMP on 26.2.2018..
 */

public class ContactListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.layout_root)
    ViewGroup rootLayout;

    @BindView(R.id.contact_image)
    ImageView contactImage;

    @BindView(R.id.contact_name)
    TextView contactName;

    @BindColor(R.color.colorWhite)
    int whiteColor;

    @BindColor(R.color.colorGray)
    int grayColor;

    private ContactClickListener contactClickListener;

    private int id;

    public ContactListHolder(View itemView, ContactClickListener contactClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.contactClickListener = contactClickListener;
    }

    @OnClick
    public void contactClicked() {
        if (contactClickListener != null) {
            contactClickListener.onContactClicked(id);
        }
    }

    @OnLongClick
    public boolean contactLongClicked() {
        if (contactClickListener != null) {
            contactClickListener.onContactLongClicked(id);

            return true;
        } else {
            return false;
        }
    }

    public void setContactInfo(Contact contact) {
        if (contact != null) {
            id = contact.getId();

            ImageUtils.setPicture(contactImage, contact.getPicture());
            contactName.setText(contact.getName());
        }

    }

    public void setWhiteBackground() {
        rootLayout.setBackgroundColor(whiteColor);
    }

    public void setGreyBackground() {
        rootLayout.setBackgroundColor(grayColor);
    }
}
