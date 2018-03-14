package com.example.comp.phonebook.ui.add_new_contact;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.database.DatabaseManager;
import com.example.comp.phonebook.presenters.AddNewContactPresenter;
import com.example.comp.phonebook.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewContact extends AppCompatActivity implements AddNewContactInterface.View {

    @BindView(R.id.add_contact_name)
    TextView contactName;

    @BindView(R.id.add_contact_phone)
    TextView contactNumber;

    @BindView(R.id.add_contact_address)
    TextView contactAddress;

    @BindView(R.id.add_contact_photo)
    ImageView contactPhoto;

    AddNewContactInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, AddNewContact.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        setUI();

        presenter = new AddNewContactPresenter(DatabaseManager.getDatabaseInstance());

        presenter.setView(this);

    }

    private void setUI() {
        ButterKnife.bind(this);

        contactNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!contactNumber.hasFocus()) {
                    presenter.onContactNumberLostFocus();
                }
            }
        });

        contactNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.checkContactNumberInput(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.button_contact_add)
    public void addContactClicked() {
        presenter.addContactClicked(contactName.getText().toString().trim(), contactNumber.getText().toString().trim(),
                contactAddress.getText().toString().trim(), contactPhoto.getDrawable());
    }

    @Override
    public void showContactNameMissingError() {
        contactName.setError(getString(R.string.edit_text_error));
    }

    @Override
    public void showContactNumberMissingError() {
        contactNumber.setError(getString(R.string.edit_text_error));
    }

    @Override
    public void showContactAddressMissingError() {
        contactAddress.setError(getString(R.string.edit_text_error));
    }

    @Override
    public void showContactPictureMissingError() {
        Toast.makeText(this, R.string.contact_picture_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToPreviousScreen() {
        finish();
    }

    @Override
    public void articleAdded() {
        presenter.onArticleAdded();
    }

    @OnClick(R.id.button_back)
    public void onBackClicked() {
        presenter.onBackClicked();
    }

    @OnClick(R.id.button_camera_add)
    public void onCameraClicked() {
        presenter.onCameraClicked();
    }

    @Override
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constants.CAPTURE_IMAGE_RESULT);
    }

    @OnClick(R.id.button_gallery_add)
    public void onGalleryClicked() {
        presenter.onGalleryClicked();
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, Constants.LOAD_IMAGE_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data, getContentResolver());
    }

    @Override
    public void showContactPhoto(Bitmap personPicture) {
        contactPhoto.setImageBitmap(personPicture);
    }

    @Override
    public void showImageBox() {
        contactPhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContactNumberLengthError() {
        contactNumber.setError(getString(R.string.contact_number_lenght_error));
    }

    @Override
    public void showContactNumberUnderlineError() {
        contactNumber.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void hideContactNumberError() {
        contactNumber.setError(null);
    }

    @Override
    public void hideContactNumberUnderlineError() {
        contactNumber.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void showContactNumberUnderlineHintColor() {
        contactNumber.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorLightBlue), PorterDuff.Mode.SRC_ATOP);
    }
}
