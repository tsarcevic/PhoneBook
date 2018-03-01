package com.example.comp.phonebook.ui.contact_list.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.data.Contact;
import com.example.comp.phonebook.interfaces.ContactClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 26.2.2018..
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListHolder> {

    private List<Contact> contactList = new ArrayList<>();

    ContactClickListener contactClickListener;

    public void setContactClickListener(ContactClickListener contactClickListener) {
        this.contactClickListener = contactClickListener;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList.clear();
        this.contactList.addAll(contactList);
        notifyDataSetChanged();
    }

    @Override
    public ContactListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View contactView = layoutInflater.inflate(R.layout.item_contact, parent, false);
        return new ContactListHolder(contactView, contactClickListener);
    }

    @Override
    public void onBindViewHolder(ContactListHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.setContactInfo(contact);

        if (position % 2 == 0) {
            holder.setWhiteBackground();
        } else {
            holder.setGreyBackground();
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
