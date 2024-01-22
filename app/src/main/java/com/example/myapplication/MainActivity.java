package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //フィールド定義
    private Operator ope;
    private int initialValInt;
    private int finalValInt;
    Button buAddition;
    Button buSubtraction;
    Button buMultiplication;
    Button buDivision;
    Button btnAddition1;
    Button btnAddition2;
    Button btnAddition3;
    Button btnAddition4;
    Button btnAddition5;
    Button btnAddition6;
    Button btnAddition7;
    Button btnAddition8;
    Button btnAddition9;
    Button btnAddition10;
    Button btnSubtraction1;
    Button btnSubtraction2;
    Button btnSubtraction3;
    Button btnSubtraction4;
    Button btnSubtraction5;
    Button btnSubtraction6;
    Button btnSubtraction7;
    Button btnSubtraction8;
    Button btnSubtraction9;
    Button btnSubtraction10;


    //セッター
    public void setOpe(Operator ope){
        this.ope = ope;
    }
    public void setInitialValInt(int val){
        this.initialValInt = val;
    }
    public void setFinalValInt(int val){
        this.finalValInt = val;
    }
    public void setIniValFinVal(int ini,int fin){
        this.initialValInt = ini;
        this.finalValInt = fin;
    }

    //ゲッター
    public Operator getOpe(){
        return this.ope;
    }
    public int getInitialValInt(){
        return this.initialValInt;
    }
    public int getFinalValInt(){
        return this.finalValInt;
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
                R.layout.spinner_item, leftArray);
        leftAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        leftSpinner.setAdapter(leftAdapter);

        //右スピナーを設定
        String[] rightArray = new String[100];
        for (int i = 0; i < 100; i++) {
            rightArray[i] = String.valueOf(i + 1);
        }
        Spinner rightSpinner = findViewById(R.id.rightSpinner);
        ArrayAdapter<String> rightAdapter
                = new ArrayAdapter<>(this,
                R.layout.spinner_item, rightArray);
        rightAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        rightSpinner.setAdapter(rightAdapter);

        //演算子ボタンのリスナー登録
        buAddition = findViewById(R.id.buAddition);
        buSubtraction = findViewById(R.id.buSubtraction);
        buMultiplication = findViewById(R.id.buMultiplication);
        buDivision = findViewById(R.id.buDivision);

        //レベルボタンのリスナー登録
        btnAddition1 = findViewById(R.id.btnAddition1);
        btnAddition2 = findViewById(R.id.btnAddition2);
        btnAddition3 = findViewById(R.id.btnAddition3);
        btnAddition4 = findViewById(R.id.btnAddition4);
        btnAddition5 = findViewById(R.id.btnAddition5);
        btnAddition6 = findViewById(R.id.btnAddition6);
        btnAddition7 = findViewById(R.id.btnAddition7);
        btnAddition8 = findViewById(R.id.btnAddition8);
        btnAddition9 = findViewById(R.id.btnAddition9);
        btnAddition10 = findViewById(R.id.btnAddition10);
        btnSubtraction1 = findViewById(R.id.btnSubtraction1);
        btnSubtraction2 = findViewById(R.id.btnSubtraction2);
        btnSubtraction3 = findViewById(R.id.btnSubtraction3);
        btnSubtraction4 = findViewById(R.id.btnSubtraction4);
        btnSubtraction5 = findViewById(R.id.btnSubtraction5);
        btnSubtraction6 = findViewById(R.id.btnSubtraction6);
        btnSubtraction7 = findViewById(R.id.btnSubtraction7);
        btnSubtraction8 = findViewById(R.id.btnSubtraction8);
        btnSubtraction9 = findViewById(R.id.btnSubtraction9);
        btnSubtraction10 = findViewById(R.id.btnSubtraction10);

        //各レベルボタンが押されたら
        btnAddition1.setOnClickListener(this);
        btnAddition2.setOnClickListener(this);
        btnAddition3.setOnClickListener(this);
        btnAddition4.setOnClickListener(this);
        btnAddition5.setOnClickListener(this);
        btnAddition6.setOnClickListener(this);
        btnAddition7.setOnClickListener(this);
        btnAddition8.setOnClickListener(this);
        btnAddition9.setOnClickListener(this);
        btnAddition10.setOnClickListener(this);
        btnSubtraction1.setOnClickListener(this);
        btnSubtraction2.setOnClickListener(this);
        btnSubtraction3.setOnClickListener(this);
        btnSubtraction4.setOnClickListener(this);
        btnSubtraction5.setOnClickListener(this);
        btnSubtraction6.setOnClickListener(this);
        btnSubtraction7.setOnClickListener(this);
        btnSubtraction8.setOnClickListener(this);
        btnSubtraction9.setOnClickListener(this);
        btnSubtraction10.setOnClickListener(this);

        //カスタムの演算子ボタンが押されたら
        buAddition.setOnClickListener(this);
        buSubtraction.setOnClickListener(this);
        buMultiplication.setOnClickListener(this);
        buDivision.setOnClickListener(this);

        //カスタムのスタートボタンが押されたら
        Button buStart = findViewById(R.id.buStart);
        buStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getOpe() == null ){
                    Toast.makeText(getApplicationContext(), "けいさん方法をえらんでね", Toast.LENGTH_LONG).show();
                }else {
                    Spinner initialVal = findViewById(R.id.leftSpinner);
                    String initialValStr = (String) initialVal.getSelectedItem();
                    setInitialValInt(Integer.parseInt(initialValStr));
                    Spinner finalVal = findViewById(R.id.rightSpinner);
                    String finalValStr = (String) finalVal.getSelectedItem();
                    setFinalValInt(Integer.parseInt(finalValStr));

                    startCalc();
                }
            }
        });

        //テストボタンが押されたら
        Button buTest = findViewById(R.id.buTest);
        buTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTest = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intentTest);
            }
        });
    }

    @Override
    public void onClick(View view){
        //idを数値で取得
        int id = view.getId();
        //idをidの名前のまま文字列で取得
        String idStr = getResources().getResourceEntryName(view.getId());

        if(id == R.id.buAddition){
            MainActivity.this.setOpe(Operator.ADDITION);
            buttonSetColor(buAddition);
        }else if(id == R.id.buSubtraction){
            MainActivity.this.setOpe(Operator.SUBTRACTION);
            buttonSetColor(buSubtraction);
        }else if(id == R.id.buMultiplication) {
            MainActivity.this.setOpe(Operator.MULTIPLICATION);
            buttonSetColor(buMultiplication);
        }else if(id == R.id.buDivision) {
            MainActivity.this.setOpe(Operator.DIVISION);
            buttonSetColor(buDivision);

        //"Addition"を含んでいるなら
        }else if(idStr.contains("Addition")) {
            Log.d("VALUE",idStr);
            MainActivity.this.setOpe(Operator.ADDITION);

            if(id == R.id.btnAddition1) {
                buttonSetColor(btnAddition1);
                setIniValFinVal(1,5);
                startCalc();
            }else if(id == R.id.btnAddition2) {
                buttonSetColor(btnAddition2);
                setIniValFinVal(6,10);
                startCalc();
            }else if(id == R.id.btnAddition3) {
                buttonSetColor(btnAddition3);
                setIniValFinVal(11,15);
                startCalc();
            }else if(id == R.id.btnAddition4) {
                buttonSetColor(btnAddition4);
                setIniValFinVal(16,20);
                startCalc();
            }else if(id == R.id.btnAddition5) {
                buttonSetColor(btnAddition5);
                setIniValFinVal(21,25);
                startCalc();
            }else if(id == R.id.btnAddition6) {
                buttonSetColor(btnAddition6);
                setIniValFinVal(26,30);
                startCalc();
            }else if(id == R.id.btnAddition7) {
                buttonSetColor(btnAddition7);
                setIniValFinVal(31,35);
                startCalc();
            }else if(id == R.id.btnAddition8) {
                buttonSetColor(btnAddition8);
                setIniValFinVal(36,40);
                startCalc();
            }else if(id == R.id.btnAddition9) {
                buttonSetColor(btnAddition9);
                setIniValFinVal(41,45);
                startCalc();
            }else if(id == R.id.btnAddition10) {
                buttonSetColor(btnAddition10);
                setIniValFinVal(46,50);
                startCalc();
            }

        //"Subtraction"を含んでいるなら
        }else if(idStr.contains("Subtraction")) {
            Log.d("VALUE",idStr);
            MainActivity.this.setOpe(Operator.SUBTRACTION);

            if(id == R.id.btnSubtraction1) {
                buttonSetColor(btnSubtraction1);
            }else if(id == R.id.btnSubtraction2) {
                buttonSetColor(btnSubtraction2);
            }else if(id == R.id.btnSubtraction3) {
                buttonSetColor(btnSubtraction3);
            }else if(id == R.id.btnSubtraction4) {
                buttonSetColor(btnSubtraction4);
            }else if(id == R.id.btnSubtraction5) {
                buttonSetColor(btnSubtraction5);
            }else if(id == R.id.btnSubtraction6) {
                buttonSetColor(btnSubtraction6);
            }else if(id == R.id.btnSubtraction7) {
                buttonSetColor(btnSubtraction7);
            }else if(id == R.id.btnSubtraction8) {
                buttonSetColor(btnSubtraction8);
            }else if(id == R.id.btnSubtraction9) {
                buttonSetColor(btnSubtraction9);
            }else if(id == R.id.btnSubtraction10) {
                buttonSetColor(btnSubtraction10);
            }
        }
    }
    public void buttonSetColor(Button button){
        buAddition.setBackgroundColor(Color.parseColor("#A37DFD"));
        buSubtraction.setBackgroundColor(Color.parseColor("#A37DFD"));
        buMultiplication.setBackgroundColor(Color.parseColor("#A37DFD"));
        buDivision.setBackgroundColor(Color.parseColor("#A37DFD"));

        btnAddition1.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition2.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition3.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition4.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition5.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition6.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition7.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition8.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition9.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnAddition10.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction1.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction2.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction3.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction4.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction5.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction6.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction7.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction8.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction9.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnSubtraction10.setBackgroundColor(Color.parseColor("#A37DFD"));

        button.setBackgroundColor(Color.parseColor("#F441B9"));
    }
    public void startCalc(){
        //CalcPageActivity遷移用のIntentをインスタンス化
        Intent intentCalcPage = new Intent(MainActivity.this, CalcPageActivity.class);
        //CalcPageActivityにデータを渡す
        intentCalcPage.putExtra("OPE", MainActivity.this.getOpe().ordinal());
        intentCalcPage.putExtra("OPE_STR", MainActivity.this.getOpe().getValue());
        intentCalcPage.putExtra("INITIAL", getInitialValInt());
        intentCalcPage.putExtra("FINAL", getFinalValInt());
        //アクティビティスタート
        startActivity(intentCalcPage);
    }
}
