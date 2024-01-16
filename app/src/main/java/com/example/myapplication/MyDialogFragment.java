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


    //使わない、備忘録用に置いておく
    //使う際は使うクラス内で以下を記述
    //DialogFragment dialogFragment = new MyDialogFragment("○");
    //dialogFragment.setCancelable(false);
    //dialogFragment.show(getSupportFragmentManager(), "my_dialog");
    CalcPageActivity calcPageActivity = new CalcPageActivity();

    public MyDialogFragment(String title){
        this.setTitle(title);
    }
    private String title;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        TextView titleView = new TextView(getActivity());
        titleView.setText(this.getTitle());
        titleView.setTextSize(600);
        titleView.setTextColor(Color.RED);
        titleView.setBackgroundColor(Color.TRANSPARENT);
        titleView.setPadding(20, -500, 20, 20);
        titleView.setGravity(Gravity.CENTER);
//        titleView.setOnTouchListener((v, event) -> {
//            dismiss();
//            return false;
//        });
        return new MaterialAlertDialogBuilder(requireActivity())
                .setCustomTitle(titleView)
                .setBackground(new ColorDrawable(Color.TRANSPARENT))
                .create();
    }
    public void onPause(){
        super.onPause();

    }
}