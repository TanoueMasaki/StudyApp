package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private final String[] dataset = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(rLayoutManager);

        int i = 0;
        while (i < 20) {
            dataset[i] = String.format(Locale.ENGLISH, "Data_0%d", i);
            i++;
        }

        MyAdapter adapter = new MyAdapter(dataset);
        recyclerView.setAdapter(adapter);
    }
}