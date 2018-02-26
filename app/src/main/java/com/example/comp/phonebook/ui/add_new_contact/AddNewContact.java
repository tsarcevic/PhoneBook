package com.example.comp.phonebook.ui.add_new_contact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.utils.Constants;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewContact extends AppCompatActivity {

    @BindView(R.id.add_person_photo)
    ImageView contactPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_camera_add)
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constants.CAPTURE_IMAGE_RESULT);
    }

    @OnClick(R.id.button_gallery_add)
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, Constants.LOAD_IMAGE_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap personPicture;

        if(resultCode == RESULT_OK) {
            switch(requestCode) {
                case Constants.LOAD_IMAGE_RESULT:
                    Uri pickedPicture = data.getData();

                    try {
                        personPicture = MediaStore.Images.Media.getBitmap(getContentResolver(), pickedPicture);
                        setContactPhoto(personPicture);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.CAPTURE_IMAGE_RESULT:
                    personPicture = (Bitmap) data.getExtras().get("data");
                    setContactPhoto(personPicture);
            }
        }
    }

    public void setContactPhoto(Bitmap personPicture) {
        contactPhoto.setVisibility(View.VISIBLE);
        contactPhoto.setImageBitmap(personPicture);
    }
}
