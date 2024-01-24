package com.example.myapplication;

import android.app.Activity;
import android.content.res.Resources;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

public class MyColor extends AppCompatActivity {
    ;
    int color;

    public int getMyColor(Activity activity,@ColorRes int id){
        this.color = activity.getResources().getColor(id,activity.getTheme());
        return this.color;
    }
}
