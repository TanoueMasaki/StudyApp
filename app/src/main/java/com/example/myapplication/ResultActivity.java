package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    //配列を用意（あとでresultに替える）
    private final List<String[]> dataset = new ArrayList<>();
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    RecyclerView recyclerView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        try{
            FileInputStream in = openFileInput( "thisResults.txt" );
            BufferedReader reader = new BufferedReader( new InputStreamReader( in , "UTF-8") );
            String tmp;
            while( (tmp = reader.readLine()) != null ){
                String array[] = tmp.split(",");
                dataset.add(array);
            }
            reader.close();
        }catch( IOException e ){
            e.printStackTrace();
        }

        //リスナー登録①
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView4 = findViewById(R.id.recyclerView4);
        recyclerView5 = findViewById(R.id.recyclerView5);

        //RecyclerViewのサイズの固定
//        recyclerView1.setHasFixedSize(true);
//        recyclerView2.setHasFixedSize(true);
//        recyclerView3.setHasFixedSize(true);
//        recyclerView4.setHasFixedSize(true);
//        recyclerView5.setHasFixedSize(true);

//        btnAsa = findViewById(R.id.morning);
//        btnHiru = findViewById(R.id.noon);
//        btnYoru = findViewById(R.id.night);
//        //②Buttonへのイベントリスナ設定
//        btnAsa.setOnClickListener(this);
//        btnHiru.setOnClickListener(this);
//        btnYoru.setOnClickListener(this);
//
//        greeting = findViewById(R.id.greeting);
//    }
//    public void onClick(View view){
//        int id = view.getId();
//
//        if(id == R.id.morning){
//            greeting.setText("おはようございます");
//        }else if(id == R.id.noon){
//            greeting.setText("こんにちは");
//        }else if(id == R.id.night) {
//            greeting.setText("こんばんは");
//        }
        //リサイクルビューのlinearlayoutManagerを使う
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this.recyclerView1);
        //①のrecyclerViewにlinearlayoutManagerをセットする

        recyclerView2.setLayoutManager(rLayoutManager);
        recyclerView3.setLayoutManager(rLayoutManager);
        recyclerView4.setLayoutManager(rLayoutManager);
        recyclerView5.setLayoutManager(rLayoutManager);



        MyAdapter adapter2 = new MyAdapter(dataset,1);
        recyclerView1.setAdapter(adapter2);
        MyAdapter adapter3 = new MyAdapter(dataset,2);
        recyclerView1.setAdapter(adapter3);
        MyAdapter adapter4 = new MyAdapter(dataset,3);
        recyclerView1.setAdapter(adapter4);
        MyAdapter adapter5 = new MyAdapter(dataset,4);
        recyclerView1.setAdapter(adapter5);
    }
    public void recyclerView1(){
        recyclerView1.setLayoutManager(rLayoutManager);
        //自作したアダプターを使う（コンストラクタで配列を渡す）
        MyAdapter adapter1 = new MyAdapter(dataset,0);
        //①のrecyclerViewにアダプターをセットする
        recyclerView1.setAdapter(adapter1);
    }
}