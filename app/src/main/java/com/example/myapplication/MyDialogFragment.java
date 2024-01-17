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

import java.util.ArrayList;
import java.util.List;

public class MyDialogFragment extends DialogFragment {

    //フィールド
    private String title;
    private String message;
    private String[] array;
    ArrayList<Integer> checkedItems = new ArrayList<Integer>();

    //コンストラクタ
    public MyDialogFragment(String title,String message){
        this.setTitle(title);
        this.setMessage(message);
    }
    public MyDialogFragment(String title, List<String[]> array){
        this.setTitle(title);
        this.setArray(array);
    }

    //セッター
    public void setTitle(String title) {
        this.title = title;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setArray(List<String[]> list){
        for(int i = 0;i < list.size();i++){
            this.array[i] = String.join(",",list.get(i));
        }
    }

    //ゲッター
    public String getTitle(){
        return this.title;
    }
    public String getMessage(){
        return this.message;
    }
    public String[] getArray(){
        return this.array;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireActivity())
                .setTitle(getTitle())
                .setMultiChoiceItems(getArray(), null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) checkedItems.add(which);
                        else checkedItems.remove((Integer) which);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Integer i : checkedItems) {
                            // item_i checked
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}