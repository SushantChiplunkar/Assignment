package com.example.assignment;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SimpleMessagePopup {
    public static void showSimplePopup(Context context, String title, String message){
        new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK",(dialog, which) -> {
                    dialog.dismiss();
                }).show();
    }

//    public static void showSimplePopupOutSideActivity(Context context, String title, String message){
//        new MaterialAlertDialogBuilder(context,R.style.AppTheme)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("OK",(dialog, which) -> {
//                    dialog.dismiss();
//                }).show();
//    }
//
//    public static void showLoaderPopup(Context context,boolean isHide){
//        AlertDialog alertDialog;
//        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context)
//                .setView(R.layout.loading_dialog).setCancelable(false);
//        alertDialog = builder.create();
//        if (isHide) {
//            alertDialog.dismiss();
//        }else alertDialog.show();
//    }
}
