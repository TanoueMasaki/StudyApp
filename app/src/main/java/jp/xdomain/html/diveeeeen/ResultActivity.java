package jp.xdomain.html.diveeeeen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResultActivity extends AppCompatActivity {

    //配列を用意（あとでresultに替える）
    private String[] dataset;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        try{
            FileInputStream in = openFileInput( "thisResults.txt" );
            BufferedReader reader = new BufferedReader( new InputStreamReader( in , "UTF-8") );
            String tmp;
            while( (tmp = reader.readLine()) != null ){
                dataset = tmp.split(",");
            }
            reader.close();
        }catch( IOException e ) {
            e.printStackTrace();
        }

        //リスナー登録①
        recyclerView = findViewById(R.id.recyclerView);

        //RecyclerViewのサイズの固定
        recyclerView.setHasFixedSize(true);

        //リサイクルビューのlinearlayoutManagerを使う
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyAdapter adapter1 = new MyAdapter(this,dataset,2);
        recyclerView.setAdapter(adapter1);
    }

    //トップに戻るボタンが押されたら
    public void onClickButtonFinish(View view) {
        finish();
    }
}