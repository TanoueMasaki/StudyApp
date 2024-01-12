package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //フィールド定義
    private Operator ope;
    //セッター
    public void setOpe(Operator ope){
        this.ope = ope;
    }
    //ゲッター
    public Operator getOpe(){
        return this.ope;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //左スピナーを設定
        String[] leftArray = new String[100];
        for (int i = 0; i < 100; i++) {
            leftArray[i] = String.valueOf(i + 1);
        }
        Spinner leftSpinner = findViewById(R.id.leftSpinner);
        ArrayAdapter<String> leftAdapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, leftArray);
        leftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(leftAdapter);

        //右スピナーを設定
        String[] rightArray = new String[100];
        for (int i = 0; i < 100; i++) {
            rightArray[i] = String.valueOf(i + 1);
        }
        Spinner rightSpinner = findViewById(R.id.rightSpinner);
        ArrayAdapter<String> rightAdapter
                = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, rightArray);
        rightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rightSpinner.setAdapter(rightAdapter);

        //演算子ボタンのインスタンス化
        Button buAddition = findViewById(R.id.buAddition);
        Button buSubtraction = findViewById(R.id.buSubtraction);
        Button buMultiplication = findViewById(R.id.buMultiplication);
        Button buDivision = findViewById(R.id.buDivision);

        //足し算ボタンが押されたら
        buAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.setOpe(Operator.ADDITION);
                buAddition.setBackgroundColor(Color.parseColor("#F441B9"));
                buSubtraction.setBackgroundColor(Color.parseColor("#A37DFD"));
                buMultiplication.setBackgroundColor(Color.parseColor("#A37DFD"));
                buDivision.setBackgroundColor(Color.parseColor("#A37DFD"));
            }
        });

        //ひき算ボタンが押されたら
        buSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.setOpe(Operator.SUBTRACTION);
                buAddition.setBackgroundColor(Color.parseColor("#A37DFD"));
                buSubtraction.setBackgroundColor(Color.parseColor("#F441B9"));
                buMultiplication.setBackgroundColor(Color.parseColor("#A37DFD"));
                buDivision.setBackgroundColor(Color.parseColor("#A37DFD"));
            }
        });
        //かけ算ボタンが押されたら
        buMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.setOpe(Operator.MULTIPLICATION);
                buAddition.setBackgroundColor(Color.parseColor("#A37DFD"));
                buSubtraction.setBackgroundColor(Color.parseColor("#A37DFD"));
                buMultiplication.setBackgroundColor(Color.parseColor("#F441B9"));
                buDivision.setBackgroundColor(Color.parseColor("#A37DFD"));
            }
        });
        //わり算ボタンが押されたら
        buDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.setOpe(Operator.DIVISION);
                buAddition.setBackgroundColor(Color.parseColor("#A37DFD"));
                buSubtraction.setBackgroundColor(Color.parseColor("#A37DFD"));
                buMultiplication.setBackgroundColor(Color.parseColor("#A37DFD"));
                buDivision.setBackgroundColor(Color.parseColor("#F441B9"));
            }
        });

        //スタートボタンが押されたら
        Button buStart = findViewById(R.id.buStart);
        buStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialValを取得してフィールドに代入
                Spinner initialVal = findViewById(R.id.leftSpinner);
                String initialValStr = (String) initialVal.getSelectedItem();
                int initialValInt = Integer.parseInt(initialValStr);

                //finalValを取得してフィールドに代入
                Spinner finalVal = findViewById(R.id.rightSpinner);
                String finalValStr = (String) finalVal.getSelectedItem();
                int finalValInt = Integer.parseInt(finalValStr);

                //CalcPageActivity遷移用のIntentをインスタンス化
                Intent intentCalcPage = new Intent(MainActivity.this, CalcPageActivity.class);
                //CalcPageActivityにデータを渡す
                intentCalcPage.putExtra("OPE",MainActivity.this.getOpe().ordinal());
                intentCalcPage.putExtra("OPE_STR",MainActivity.this.getOpe().getValue());
                intentCalcPage.putExtra("INITIAL",initialValInt);
                intentCalcPage.putExtra("FINAL",finalValInt);
                //アクティビティスタート
                startActivity(intentCalcPage);
            }
        });
    }
}
