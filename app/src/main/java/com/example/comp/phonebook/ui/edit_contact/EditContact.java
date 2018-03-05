package com.example.comp.phonebook.ui.edit_contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.comp.phonebook.R;

public class EditContact extends AppCompatActivity {

    public static final String KEY_EDIT_CONTACT_ID = "contactId";

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditContact.class);
        intent.putExtra(KEY_EDIT_CONTACT_ID, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
    }
}
