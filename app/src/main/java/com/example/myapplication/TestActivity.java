package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    //フィールド
    private String test1 = "test1";
    private String test2 = "test2";
    private String test3 = "test3";

    Button button;
    Button button1;

    public void Click() {
            button.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView testText = findViewById(R.id.testText);
        testText.setText(this.test1);

        //buttonが押されたら
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testText.setText(TestActivity.this.test3);

            }

        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestActivity.this.Click();

            }

        });
    }
//    try {
//        //FileInputStreamを使用し、ファイルの内容を1行ずつ表示するJavaサンプルコード
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\test\\readLine.txt"), Charset.forName("UTF-8"))) ;
//        String text;
//        while ((text = br.readLine()) != null) {System.out.println(text);}
//    } catch (Exception ex){ }


}