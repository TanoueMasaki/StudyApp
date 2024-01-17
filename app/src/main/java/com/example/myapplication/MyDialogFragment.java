package com.example.myapplication;

import static java.lang.ref.Cleaner.create;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MyDialogFragment extends DialogFragment {
    public MyDialogFragment(String title,String message){
        this.setTitle(title);
        this.setMessage(message);
    }
    private String title;
    private String message;
    public void setTitle(String title) {
        this.title = title;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getTitle(){
        return this.title;
    }
    public String getMessage(){
        return this.message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireActivity())
                .setTitle(getTitle())
                .setMessage(getMessage())
                .setPositiveButton("OK", (dialog, id) -> {
                    // このボタンを押した時の処理を書きます。
                })
                .setNegativeButton("キャンセル", null)
                .setNeutralButton("あとで", null)
                .create();
    }
}