package com.example.comp.phonebook.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.comp.phonebook.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by COMP on 28.2.2018..
 */

public class ImageUtils {

    public static void setPicture(ImageView target, byte[] picture) {
        Glide.with(target.getContext())
                .asBitmap()
                .load(picture)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_name))
                .into(target);
    }

    public static byte[] convertDrawablePictureToByte(Drawable picture) {
        Bitmap bitmap = ((BitmapDrawable) picture).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytePicture = stream.toByteArray();
        return bytePicture;
    }

    public static Bitmap convertByteToBitmapPicture(byte[] byteImage) {
        ByteArrayInputStream stream = new ByteArrayInputStream(byteImage);
        return BitmapFactory.decodeStream(stream);
    }
}
