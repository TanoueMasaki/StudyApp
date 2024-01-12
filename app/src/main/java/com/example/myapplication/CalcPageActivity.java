package com.example.myapplication;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalcPageActivity extends AppCompatActivity {

    //フィールド
    private int initialVal ;
    private int finalVal ;
    private int leftValue;
    private int rightValue;
    private int leftCount = 0;
    private int rightCount = 0;
    private int questionNum = 1;
    private int opeInt;

    private String answerStr = "";
    private ArrayList<Integer> questions;//問題作成用配列

    //セッター
    public void setInitialVal(int value){
        this.initialVal = value;
    }
    public void setFinalVal(int value){
        this.finalVal = value;
    }
    public void setOpeInt(int opeInt){
        this.opeInt = opeInt;
    }
    public void setLeftValue(int value){
        this.leftValue = value;
    }
    public void setRightValue(int value){
        this.rightValue = value;
    }

    public void setLeftCount(int value){
        this.leftCount += value;
    }
    public void setRightCount(int value){
        this.rightCount += value;
    }
    public void clearRightCount(){
        this.rightCount = 0;
    }
    public void setAnswerStr(String str){
        if(this.answerStr.length() < 8){
            this.answerStr += str;
        }
    }
    public void clearAnswerStr(){
        this.answerStr = "";
    }

    public void deleteAnswerStr(){
        if(this.getAnswerStr().length() != 0){
            this.answerStr = this.answerStr.substring(0,this.answerStr.length()-1);
        }
    }
    public void setQuestionNum(int num){
        this.questionNum = num;
    }
    public  void setQuestions(ArrayList<Integer> array ){
        this.questions = array;
    }

    //ゲッター
    public int getInitialVal() {
        return this.initialVal;
    }
    public int getFinalVal() {
        return this.finalVal;
    }
    public int getOpeInt(){
        return this.opeInt;
    }
    public int getLeftValue(){
        return this.leftValue;
    }
    public int getRightValue(){
        return this.rightValue;
    }
    public int getLeftCount(){
        return this.leftCount;
    }
    public int getRightCount(){
        return this.rightCount;
    }
    public String getAnswerStr(){
        return this.answerStr;
    }
    public int getQuestionNum(){
        return this.questionNum;
    }


    //画面が開いたら
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_page);

        this.setInitialVal(getIntent().getIntExtra("INITIAL", 0));
        this.setFinalVal(getIntent().getIntExtra("FINAL", 0));
        this.setOpeInt(getIntent().getIntExtra("OPE", 0));

        //クラスのインスタンス化
        Calculation calculation = new Calculation();

        //フィールドに渡された数値を引数で渡してArrayList型配列を取得、this.questionsに入れる
        this.setQuestions(calculation.arraySet(this.getInitialVal(), this.getFinalVal()));
        System.out.println(questions);

        //解答結果用配列を用意する
        String[][] results = new String[5][questions.size() * questions.size()];

        //画面表示用に各TextViewをインスタンス化しておく
        TextView leftValueText = findViewById(R.id.leftValue);
        TextView rightValueText = findViewById(R.id.rightValue);
        TextView opeText = findViewById(R.id.ope);
        TextView answerText = findViewById(R.id.answer);
        TextView trueOrFalseText = findViewById(R.id.trueOrFalse);

        //intentで渡されたOPE_STRをopeTextにセットする
        String opeStr = getIntent().getStringExtra(("OPE_STR"));
        opeText.setText(opeStr);


        this.setLeftValue(questions.get(getLeftCount()));
        String leftValueStr = String.valueOf(this.getLeftValue());
        leftValueText.setText(leftValueStr);
        this.setRightValue(questions.get(getRightCount()));
        String rightValueStr = String.valueOf(this.getRightValue());
        rightValueText.setText(rightValueStr);

        //判定ボタンが押されたら
        Button judgment = findViewById(R.id.judgment);
        judgment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String answerStr = answerText.getText().toString();
                    int answer = Integer.parseInt(answerStr);
                    int answerCalc = calculation.makeCalculation(
                            CalcPageActivity.this.getOpeInt(),
                            CalcPageActivity.this.getLeftValue(),
                            CalcPageActivity.this.getRightValue()
                    );
//                    //正誤判定後、結果を配列に入れる
                    if (answerCalc == answer) {
                        trueOrFalseText.setText("○");
                        trueOrFalseText.setTextColor(Color.RED);
//                        results[questionNum - 1][1] = "正解";
                    } else {
                        trueOrFalseText.setText("×");
                        trueOrFalseText.setTextColor(Color.BLUE);
//                        results[questionNum - 1][1] = "不正解";
                    }
//                    //問題番号、問題、正解、入力した数値を配列に入れる
//                    results[questionNum - 1][0] = questionNum + "問目";
//                    results[questionNum - 1][2] = "問題:" + leftValue + opeStr + rightValue;
//                    results[questionNum - 1][3] = "正解:" + answerCalc;
//                    results[questionNum - 1][4] = "入力した数値:" + answer;
//                    //問題番号に+1する
//                    questionNum++;
                //trueOrFalseTextがタップされたら進む
                int leftC = CalcPageActivity.this.getLeftCount();
                int rightC = CalcPageActivity.this.getRightCount();
                String rightValueStr;
                String leftValueStr;

                if (rightC + 1 < questions.size()) {
                    CalcPageActivity.this.setRightCount(1);
                    CalcPageActivity.this.setRightValue(questions.get(getRightCount()));
                    rightValueStr = String.valueOf(CalcPageActivity.this.getRightValue());
                    rightValueText.setText(rightValueStr);
                } else if (rightC + 1 == questions.size() && leftC + 1 < questions.size()) {
                    CalcPageActivity.this.setLeftCount(1);
                    CalcPageActivity.this.setLeftValue(questions.get(getLeftCount()));
                    leftValueStr = String.valueOf(CalcPageActivity.this.getLeftValue());
                    leftValueText.setText(leftValueStr);
                    CalcPageActivity.this.clearRightCount();
                    CalcPageActivity.this.setRightValue(questions.get(getRightCount()));
                    rightValueStr = String.valueOf(CalcPageActivity.this.getRightValue());
                    rightValueText.setText(rightValueStr);
                }
            }
        });

        trueOrFalseText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                         break;
                case MotionEvent.ACTION_UP:
                                        break;
                }
                return true;
            }
        });


        //0ボタンが押されたら
        Button bu0 = findViewById(R.id.bu0);
        bu0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("0");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //1ボタンが押されたら
        Button bu1 = findViewById(R.id.bu1);
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("1");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //2ボタンが押されたら
        Button bu2 = findViewById(R.id.bu2);
        bu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("2");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //0ボタンが押されたら
        Button bu3 = findViewById(R.id.bu3);
        bu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("3");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //4ボタンが押されたら
        Button bu4 = findViewById(R.id.bu4);
        bu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("4");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //5ボタンが押されたら
        Button bu5 = findViewById(R.id.bu5);
        bu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("5");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //6ボタンが押されたら
        Button bu6 = findViewById(R.id.bu6);
        bu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("6");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //7ボタンが押されたら
        Button bu7 = findViewById(R.id.bu7);
        bu7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("7");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //8ボタンが押されたら
        Button bu8 = findViewById(R.id.bu8);
        bu8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("8");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //9ボタンが押されたら
        Button bu9 = findViewById(R.id.bu9);
        bu9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("9");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });

        //一字消すボタンが押されたら
        Button buDelete = findViewById(R.id.buDelete);
        buDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.deleteAnswerStr();
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //全て消すボタンが押されたらdeleteAnswerStr
        Button buAllClear = findViewById(R.id.buAllClear);
        buAllClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.clearAnswerStr();
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });


        //トップに戻るボタンが押されたら
        Button buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
