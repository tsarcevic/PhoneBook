package com.example.comp.phonebook.ui.contact_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.data.Contact;
import com.example.comp.phonebook.database.DatabaseManager;
import com.example.comp.phonebook.interfaces.ContactClickListener;
import com.example.comp.phonebook.interfaces.DeleteListener;
import com.example.comp.phonebook.presenters.ContactListPresenter;
import com.example.comp.phonebook.ui.add_new_contact.AddNewContact;
import com.example.comp.phonebook.ui.contact_info.ContactInfo;
import com.example.comp.phonebook.ui.contact_list.adapters.ContactListAdapter;
import com.example.comp.phonebook.utils.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactList extends AppCompatActivity implements ContactClickListener, ContactListInterface.View, DeleteListener {

    @BindView(R.id.recycler_person)
    RecyclerView contactListRecycler;

    @BindView(R.id.contact_list_no_contact_info)
    TextView noDataText;

    ContactListAdapter contactListAdapter;

    ContactListInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        setUI();

        presenter = new ContactListPresenter(DatabaseManager.getDatabaseInstance());

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.viewReady();
    }

    private void setUI() {
        ButterKnife.bind(this);

        contactListAdapter = new ContactListAdapter();
        contactListAdapter.setContactClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contactListRecycler.setLayoutManager(layoutManager);
        contactListRecycler.setAdapter(contactListAdapter);
    }

    @OnClick(R.id.button_new_person)
    public void onAddButtonClicked() {
        presenter.onAddButtonClicked();
    }

    @Override
    public void navigateToContactAdding() {
        startActivity(AddNewContact.getLaunchIntent(this));
    }

    @Override
    public void onContactClicked(int id) {
        presenter.onContactClicked(id);
    }

    @Override
    public void onContactLongClicked(int id) {
        presenter.onContactLongClicked(id);
    }

    @Override
    public void onDeleteClicked(int id) {
        presenter.onDeleteContactClicked(id);
    }

    @Override
    public void navigateToContactInfo(int id) {
        startActivity(ContactInfo.getLaunchIntent(this, id));
    }

    @Override
    public void showDeleteContactMessage() {
        Toast.makeText(this, R.string.delete_contact_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteDialog(int id) {
        DialogUtils.showDialog(id, this, this);
    }

    @Override
    public void showContacts(List<Contact> contactList) {
        contactListAdapter.setContactList(contactList);
    }

    @Override
    public void showNoDataInfo() {
        noDataText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataInfo() {
        noDataText.setVisibility(View.GONE);
    }

    @Override
    public void hideRecyclerView() {
        contactListRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView() {
        contactListRecycler.setVisibility(View.VISIBLE);
    }
}
