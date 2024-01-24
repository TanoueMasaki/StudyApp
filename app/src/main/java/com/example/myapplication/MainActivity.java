package com.example.myapplication;

import android.app.Activity;
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
    Button btnMultiplication1;
    Button btnMultiplication2;
    Button btnMultiplication3;
    Button btnMultiplication4;
    Button btnMultiplication5;
    Button btnMultiplication6;
    Button btnMultiplication7;
    Button btnMultiplication8;
    Button btnMultiplication9;
    Button btnMultiplication10;
    Button btnDivision1;
    Button btnDivision2;
    Button btnDivision3;
    Button btnDivision4;
    Button btnDivision5;
    Button btnDivision6;
    Button btnDivision7;
    Button btnDivision8;
    Button btnDivision9;
    Button btnDivision10;
    Button btnTest;
    Spinner leftSpinner;
    Spinner rightSpinner;


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
        leftSpinner = findViewById(R.id.leftSpinner);
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
        rightSpinner = findViewById(R.id.rightSpinner);
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
        btnMultiplication1 = findViewById(R.id.btnMultiplication1);
        btnMultiplication2 = findViewById(R.id.btnMultiplication2);
        btnMultiplication3 = findViewById(R.id.btnMultiplication3);
        btnMultiplication4 = findViewById(R.id.btnMultiplication4);
        btnMultiplication5 = findViewById(R.id.btnMultiplication5);
        btnMultiplication6 = findViewById(R.id.btnMultiplication6);
        btnMultiplication7 = findViewById(R.id.btnMultiplication7);
        btnMultiplication8 = findViewById(R.id.btnMultiplication8);
        btnMultiplication9 = findViewById(R.id.btnMultiplication9);
        btnMultiplication10 = findViewById(R.id.btnMultiplication10);
        btnDivision1 = findViewById(R.id.btnDivision1);
        btnDivision2 = findViewById(R.id.btnDivision2);
        btnDivision3 = findViewById(R.id.btnDivision3);
        btnDivision4 = findViewById(R.id.btnDivision4);
        btnDivision5 = findViewById(R.id.btnDivision5);
        btnDivision6 = findViewById(R.id.btnDivision6);
        btnDivision7 = findViewById(R.id.btnDivision7);
        btnDivision8 = findViewById(R.id.btnDivision8);
        btnDivision9 = findViewById(R.id.btnDivision9);
        btnDivision10 = findViewById(R.id.btnDivision10);

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
        btnMultiplication1.setOnClickListener(this);
        btnMultiplication2.setOnClickListener(this);
        btnMultiplication3.setOnClickListener(this);
        btnMultiplication4.setOnClickListener(this);
        btnMultiplication5.setOnClickListener(this);
        btnMultiplication6.setOnClickListener(this);
        btnMultiplication7.setOnClickListener(this);
        btnMultiplication8.setOnClickListener(this);
        btnMultiplication9.setOnClickListener(this);
        btnMultiplication10.setOnClickListener(this);
        btnDivision1.setOnClickListener(this);
        btnDivision2.setOnClickListener(this);
        btnDivision3.setOnClickListener(this);
        btnDivision4.setOnClickListener(this);
        btnDivision5.setOnClickListener(this);
        btnDivision6.setOnClickListener(this);
        btnDivision7.setOnClickListener(this);
        btnDivision8.setOnClickListener(this);
        btnDivision9.setOnClickListener(this);
        btnDivision10.setOnClickListener(this);

        //いったん無効化
        btnDivision1.setEnabled(false);
        btnDivision2.setEnabled(false);
        btnDivision3.setEnabled(false);
        btnDivision4.setEnabled(false);
        btnDivision5.setEnabled(false);
        btnDivision6.setEnabled(false);
        btnDivision7.setEnabled(false);
        btnDivision8.setEnabled(false);
        btnDivision9.setEnabled(false);
        btnDivision10.setEnabled(false);
        buDivision.setEnabled(false);

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

        //過去の結果ボタンが押されたら
        Button btnPastResu = findViewById(R.id.btnPastResu);
        btnPastResu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllResultActivity.class);
                startActivity(intent);
            }
        });
        //テスト用
        Button btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view){
        //idを数値で取得
        int id = view.getId();
        //idをidの名前のまま文字列で取得
        String idStr = getResources().getResourceEntryName(view.getId());

        //カスタムの演算子ボタン
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

        //各レベルボタン
        //idに"Addition"を含んでいるなら
        }else if(idStr.contains("Addition")) {
            Log.d("VALUE",idStr);
            MainActivity.this.setOpe(Operator.ADDITION);
            //各レベル
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
                setIniValFinVal(1,5);
                startCalc();
            }else if(id == R.id.btnSubtraction2) {
                buttonSetColor(btnSubtraction2);
                setIniValFinVal(6,10);
                startCalc();
            }else if(id == R.id.btnSubtraction3) {
                buttonSetColor(btnSubtraction3);
                setIniValFinVal(11,15);
                startCalc();
            }else if(id == R.id.btnSubtraction4) {
                buttonSetColor(btnSubtraction4);
                setIniValFinVal(16,20);
                startCalc();
            }else if(id == R.id.btnSubtraction5) {
                buttonSetColor(btnSubtraction5);
                setIniValFinVal(21,25);
                startCalc();
            }else if(id == R.id.btnSubtraction6) {
                buttonSetColor(btnSubtraction6);
                setIniValFinVal(26,30);
                startCalc();
            }else if(id == R.id.btnSubtraction7) {
                buttonSetColor(btnSubtraction7);
                setIniValFinVal(31,35);
                startCalc();
            }else if(id == R.id.btnSubtraction8) {
                buttonSetColor(btnSubtraction8);
                setIniValFinVal(36,40);
                startCalc();
            }else if(id == R.id.btnSubtraction9) {
                buttonSetColor(btnSubtraction9);
                setIniValFinVal(41,45);
                startCalc();
            }else if(id == R.id.btnSubtraction10) {
                buttonSetColor(btnSubtraction10);
                setIniValFinVal(46,50);
                startCalc();
            }
        //"Multiplication"を含んでいるなら
        }else if(idStr.contains("Multiplication")) {
            Log.d("VALUE", idStr);
            MainActivity.this.setOpe(Operator.MULTIPLICATION);

            if (id == R.id.btnMultiplication1) {
                buttonSetColor(btnMultiplication1);
                setIniValFinVal(1, 5);
                startCalc();
            } else if (id == R.id.btnMultiplication2) {
                buttonSetColor(btnMultiplication2);
                setIniValFinVal(6, 10);
                startCalc();
            } else if (id == R.id.btnMultiplication3) {
                buttonSetColor(btnMultiplication3);
                setIniValFinVal(11, 15);
                startCalc();
            } else if (id == R.id.btnMultiplication4) {
                buttonSetColor(btnMultiplication4);
                setIniValFinVal(16, 20);
                startCalc();
            } else if (id == R.id.btnMultiplication5) {
                buttonSetColor(btnMultiplication5);
                setIniValFinVal(21, 25);
                startCalc();
            } else if (id == R.id.btnMultiplication6) {
                buttonSetColor(btnMultiplication6);
                setIniValFinVal(26, 30);
                startCalc();
            } else if (id == R.id.btnMultiplication7) {
                buttonSetColor(btnMultiplication7);
                setIniValFinVal(31, 35);
                startCalc();
            } else if (id == R.id.btnMultiplication8) {
                buttonSetColor(btnMultiplication8);
                setIniValFinVal(36, 40);
                startCalc();
            } else if (id == R.id.btnMultiplication9) {
                buttonSetColor(btnMultiplication9);
                setIniValFinVal(41, 45);
                startCalc();
            } else if (id == R.id.btnMultiplication10) {
                buttonSetColor(btnMultiplication10);
                setIniValFinVal(46, 50);
                startCalc();
            }
        //"Division"を含んでいるなら
        } else if (idStr.contains("Division")) {
            Log.d("VALUE", idStr);
            MainActivity.this.setOpe(Operator.DIVISION);

            if (id == R.id.btnDivision1) {
                buttonSetColor(btnDivision1);
                setIniValFinVal(1, 5);
//                    startCalc();
            } else if (id == R.id.btnDivision2) {
                buttonSetColor(btnDivision2);
                setIniValFinVal(6, 10);
//                    startCalc();
            } else if (id == R.id.btnDivision3) {
                buttonSetColor(btnDivision3);
                setIniValFinVal(11, 15);
//                    startCalc();
            } else if (id == R.id.btnDivision4) {
                buttonSetColor(btnDivision4);
                setIniValFinVal(16, 20);
//                    startCalc();
            } else if (id == R.id.btnDivision5) {
                buttonSetColor(btnDivision5);
                setIniValFinVal(21, 25);
//                    startCalc();
            } else if (id == R.id.btnDivision6) {
                buttonSetColor(btnDivision6);
                setIniValFinVal(26, 30);
//                    startCalc();
            } else if (id == R.id.btnDivision7) {
                buttonSetColor(btnDivision7);
                setIniValFinVal(31, 35);
//                    startCalc();
            } else if (id == R.id.btnDivision8) {
                buttonSetColor(btnDivision8);
                setIniValFinVal(36, 40);
//                    startCalc();
            } else if (id == R.id.btnDivision9) {
                buttonSetColor(btnDivision9);
                setIniValFinVal(41, 45);
//                    startCalc();
            } else if (id == R.id.btnDivision10) {
                buttonSetColor(btnDivision10);
                setIniValFinVal(46, 50);
//                    startCalc();
            }
        }
    }
    public void buttonSetColor(Button button){
        buttonAllColorReset();
        button.setBackgroundColor(Color.parseColor("#F441B9"));
    }
    public void buttonAllColorReset(){
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
        btnMultiplication1.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication2.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication3.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication4.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication5.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication6.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication7.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication8.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication9.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnMultiplication10.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision1.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision2.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision3.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision4.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision5.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision6.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision7.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision8.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision9.setBackgroundColor(Color.parseColor("#A37DFD"));
        btnDivision10.setBackgroundColor(Color.parseColor("#A37DFD"));
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

    @Override
    protected void onPostResume() {
        super.onPostResume();

        leftSpinner.setSelection(0);
        rightSpinner.setSelection(0);
        this.setOpe(null);
        buttonAllColorReset();
    }
}
