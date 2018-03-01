package com.example.comp.phonebook.ui.contact_info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.database.DatabaseManager;
import com.example.comp.phonebook.presenters.ContactInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactInfo extends AppCompatActivity implements ContactInfoInterface.View {

    @BindView(R.id.info_contact_image)
    ImageView contactImage;

    @BindView(R.id.contact_info_name)
    TextView contactName;

    @BindView(R.id.contact_info_number)
    TextView contactNumber;

    @BindView(R.id.contact_info_address)
    TextView contactAddress;

    private static final String KEY_CONTACT_INFO_ID = "contactId";

    ContactInfoInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ContactInfo.class);
        intent.putExtra(KEY_CONTACT_INFO_ID, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        setUI();

        presenter = new ContactInfoPresenter(DatabaseManager.getDatabaseInstance());

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getExtras();
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_CONTACT_INFO_ID)) {
            presenter.sendContactId(getIntent().getIntExtra(KEY_CONTACT_INFO_ID, -1));
        }
    }

    private void setUI() {
        ButterKnife.bind(this);
    }

    @Override
    public void showNoDataInfo() {
        Toast.makeText(this, R.string.contact_info_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContactPhoto(Bitmap bitmap) {
        contactImage.setImageBitmap(bitmap);
    }

    @Override
    public void showContactName(String name) {
        contactName.setText(name);
    }

    @Override
    public void showContactNumber(String phoneNumber) {
        contactNumber.setText(phoneNumber);
    }

    @Override
    public void showContactAddress(String address) {
        contactAddress.setText(address);
    }
}
