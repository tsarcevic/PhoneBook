package com.example.comp.phonebook.ui.contact_info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.database.DatabaseManager;
import com.example.comp.phonebook.presenters.ContactInfoPresenter;
import com.example.comp.phonebook.ui.edit_contact.EditContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        contactName.setText(String.format(getString(R.string.show_contact_name), name));
    }

    @Override
    public void showContactNumber(String phoneNumber) {
        contactNumber.setText(String.format(getString(R.string.show_contact_number), phoneNumber));
    }

    @Override
    public void showContactAddress(String address) {
        contactAddress.setText(String.format(getString(R.string.show_contact_address), address));
    }

    @OnClick(R.id.contact_info_number)
    public void onContactNumberClicked() {
        presenter.onContactNumberClicked();
    }

    @OnClick(R.id.contact_info_back_button)
    public void onBackClicked() {
        presenter.onBackClicked();
    }

    @OnClick(R.id.contact_info_edit_button)
    public void onEditClicked() {
        presenter.onEditClicked();
    }

    @OnClick(R.id.contact_info_delete_button)
    public void onDeleteClicked() {
        presenter.onDeleteClicked();
    }

    @OnClick(R.id.contact_info_message_send)
    public void onMessageClicked() {
        presenter.onMessageClicked();
    }

    @Override
    public void navigateToContactCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        startActivity(intent);
    }

    @Override
    public void navigateToMessageSend(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)));
    }

    @Override
    public void navigateToEditContactScreen(int id) {
        startActivity(EditContact.getLaunchIntent(this, id));
    }

    @Override
    public void navigateToPreviousScreen() {
        finish();
    }

    @Override
    public void showDeleteDialog(int id) {

    }
}