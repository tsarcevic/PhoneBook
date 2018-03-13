package com.example.comp.phonebook.ui.edit_contact;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.database.DatabaseManager;
import com.example.comp.phonebook.presenters.EditContactPresenter;
import com.example.comp.phonebook.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditContact extends AppCompatActivity implements EditContactInterface.View {

    public static final String KEY_EDIT_CONTACT_ID = "contactId";

    @BindView(R.id.edit_contact_name)
    EditText contactName;

    @BindView(R.id.edit_contact_phone)
    EditText contactNumber;

    @BindView(R.id.edit_contact_address)
    EditText contactAddress;

    @BindView(R.id.edit_contact_photo)
    ImageView contactPhoto;

    private int id;

    private EditContactPresenter presenter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditContact.class);
        intent.putExtra(KEY_EDIT_CONTACT_ID, id);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        setUI();

        presenter = new EditContactPresenter(DatabaseManager.getDatabaseInstance());
        presenter.setView(this);

        getExtras();
    }

    private void setUI() {
        ButterKnife.bind(this);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_EDIT_CONTACT_ID)) {
            id = getIntent().getIntExtra(KEY_EDIT_CONTACT_ID, -1);
            presenter.sendContactId(id);
        }
    }

    @OnClick(R.id.edit_camera_button)
    public void onCameraClicked() {
        presenter.onCameraClicked();
    }

    @OnClick(R.id.edit_gallery_button)
    public void onGalleryClicked() {
        presenter.onGalleryClicked();
    }

    @OnClick(R.id.edit_contact_button)
    public void onSaveContactClicked() {
        presenter.onSaveContactClicked(contactName.getText().toString().trim(), contactNumber.getText().toString().trim(),
                contactAddress.getText().toString().trim(), contactPhoto.getDrawable());
    }

    @OnClick(R.id.edit_button_back)
    public void onBackClicked() {
        presenter.onBackClicked();
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

    @Override
    public void showContactPicture(Bitmap picture) {
        contactPhoto.setImageBitmap(picture);
    }

    @Override
    public void contactNameError() {
        contactName.setError(getString(R.string.edit_text_error));
    }

    @Override
    public void contactNumberError() {
        contactNumber.setError(getString(R.string.edit_text_error));
    }

    @Override
    public void contactAddressError() {
        contactAddress.setError(getString(R.string.edit_text_error));
    }

    @Override
    public void contactPictureError() {
        Toast.makeText(this, R.string.contact_picture_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, Constants.LOAD_IMAGE_RESULT);
    }

    @Override
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constants.CAPTURE_IMAGE_RESULT);
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data, getContentResolver());
    }
}
