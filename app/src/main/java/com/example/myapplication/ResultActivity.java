package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    //配列を用意（あとでresultに替える）
    private final String[] dataset = {"1","2","3","4","5","6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //リスナー登録①
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        //RecyclerViewのサイズの固定
        recyclerView.setHasFixedSize(true);

        //リサイクルビューのlinearlayoutManagerを使う
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);
        //①のrecyclerViewにlinearlayoutManagerをセットする
        recyclerView.setLayoutManager(rLayoutManager);

        //自作したアダプターを使う（コンストラクタで配列を渡す）
        MyAdapter adapter = new MyAdapter(dataset);
        //①のrecyclerViewにアダプターをセットする
        recyclerView.setAdapter(adapter);
    }
}