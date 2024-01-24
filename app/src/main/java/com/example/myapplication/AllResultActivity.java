package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllResultActivity extends AppCompatActivity {
    private String[] dataset;
    private String datasetStr = "";
    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_result);

        try{
            FileInputStream in = openFileInput( "results.csv" );
            BufferedReader reader = new BufferedReader( new InputStreamReader( in , "UTF-8") );
            String tmp;
            while( (tmp = reader.readLine()) != null ) {
                datasetStr = tmp + "," + datasetStr;
            }
            dataset = datasetStr.split(",");

            reader.close();
        }catch( IOException e ) {
            e.printStackTrace();
        }

        //リスナー登録①
        recyclerView = findViewById(R.id.recyclerView);

        //RecyclerViewのサイズの固定
        recyclerView.setHasFixedSize(true);

        //リサイクルビューのlinearlayoutManagerを使う
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,7);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MyAdapter(this,dataset);
        recyclerView.setAdapter(adapter);
    }

    public void onClickButtonFinish(View view) {
        finish();
    }
}