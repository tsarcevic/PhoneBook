package com.example.comp.phonebook.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.comp.phonebook.R;
import com.example.comp.phonebook.interfaces.DeleteListener;

/**
 * Created by COMP on 26.2.2018..
 */

public class DialogUtils {

    public static void showDialog(final int contactId, Context from, final DeleteListener listener) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(from);

        dialogBuilder.setMessage(R.string.contact_info_dialog_message);
        dialogBuilder.setCancelable(true);

        dialogBuilder.setPositiveButton(from.getString(R.string.contact_info_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDeleteClicked(contactId);
            }
        });

        dialogBuilder.setNegativeButton(R.string.contact_info_dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
