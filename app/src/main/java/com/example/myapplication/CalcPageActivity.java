package com.example.myapplication;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class CalcPageActivity extends AppCompatActivity{

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
    TextView leftValueText;
    TextView rightValueText;
    TextView answerText;
    TextView textJudge;
    TextView textTap;
    Button bu0;
    Button bu1;
    Button bu2;
    Button bu3;
    Button bu4;
    Button bu5;
    Button bu6;
    Button bu7;
    Button bu8;
    Button bu9;
    Button buttonFinish;
    Button buAllClear;
    Button buDelete;
    LocalDate date;
    LocalTime time;

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
    public void setQuestionNum(int num){
        this.questionNum += num;
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
    public int getQuestionSize(){
        return this.questions.size();
    }

    //解答を全削除
    public void clearAnswerStr(){
        this.answerStr = "";
    }
    //解答の最後１字消す
    public void deleteAnswerStr(){
        if(this.getAnswerStr().length() != 0){
            this.answerStr = this.answerStr.substring(0,this.answerStr.length()-1);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_page);

        date = LocalDate.now();
        time = LocalTime.now();

        this.setInitialVal(getIntent().getIntExtra("INITIAL", 0));
        this.setFinalVal(getIntent().getIntExtra("FINAL", 0));
        this.setOpeInt(getIntent().getIntExtra("OPE", 0));

        //クラスのインスタンス化
        Calculation calculation = new Calculation();

        //フィールドに渡された数値を引数で渡してArrayList型配列を取得、this.questionsに入れる
        this.setQuestions(calculation.arraySet(this.getInitialVal(), this.getFinalVal()));

        //intentで渡されたOPE_STRをopeTextにセットする
        TextView opeText = findViewById(R.id.ope);
        String opeStr = getIntent().getStringExtra(("OPE_STR"));
        opeText.setText(opeStr);

        //画面表示用に各TextViewのリスナー登録
        leftValueText = findViewById(R.id.leftValue);
        rightValueText = findViewById(R.id.rightValue);
        answerText = findViewById(R.id.answer);
        textJudge = findViewById(R.id.textJudge);
        textTap = findViewById(R.id.textTap);
        textTap.setVisibility(View.INVISIBLE);

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
                String[] currentResults = new String[7];

                bu0.setVisibility(View.INVISIBLE);
                bu1.setVisibility(View.INVISIBLE);
                bu2.setVisibility(View.INVISIBLE);
                bu3.setVisibility(View.INVISIBLE);
                bu4.setVisibility(View.INVISIBLE);
                bu5.setVisibility(View.INVISIBLE);
                bu6.setVisibility(View.INVISIBLE);
                bu7.setVisibility(View.INVISIBLE);
                bu8.setVisibility(View.INVISIBLE);
                bu9.setVisibility(View.INVISIBLE);
                buDelete.setVisibility(View.INVISIBLE);
                buAllClear.setVisibility(View.INVISIBLE);
                buttonFinish.setVisibility(View.INVISIBLE);
                judgment.setVisibility(View.INVISIBLE);
                textTap.setVisibility(View.VISIBLE);

                    String answerStr = answerText.getText().toString();
                    int answer = Integer.parseInt(answerStr);
                    int answerCalc = calculation.makeCalculation(
                            CalcPageActivity.this.getOpeInt(),
                            CalcPageActivity.this.getLeftValue(),
                            CalcPageActivity.this.getRightValue()
                    );
                    //                    //正誤判定後、結果を配列に入れる
                    if (answerCalc == answer) {
                        textJudge.setText("○");
                        textJudge.setTextColor(Color.RED);
                        currentResults[3] = "正解";
                    } else {
                        textJudge.setText("×");
                        textJudge.setTextColor(Color.BLUE);
                        currentResults[3] = "不正解";
                    }
                //問題番号、問題、正解、入力した数値を配列に入れる
                currentResults[0] = String.valueOf(date);
                currentResults[1] = String.valueOf(time);
                currentResults[2] = String.valueOf(getQuestionNum());
                currentResults[4] = leftValue + opeStr + rightValue;
                currentResults[5] = String.valueOf(answerCalc);
                currentResults[6] = String.valueOf(answer);

                String currentResult = String.join(",", currentResults);
                try{
                    FileOutputStream fileOutputstream = openFileOutput("results.csv", MODE_APPEND);
                    fileOutputstream.write(currentResult.getBytes());
                    fileOutputstream.write('\n');
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        //0ボタンが押されたら
        bu0 = findViewById(R.id.bu0);
        bu0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("0");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //1ボタンが押されたら
        bu1 = findViewById(R.id.bu1);
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("1");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //2ボタンが押されたら
        bu2 = findViewById(R.id.bu2);
        bu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("2");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //3ボタンが押されたら
        bu3 = findViewById(R.id.bu3);
        bu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("3");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //4ボタンが押されたら
        bu4 = findViewById(R.id.bu4);
        bu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("4");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //5ボタンが押されたら
        bu5 = findViewById(R.id.bu5);
        bu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("5");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //6ボタンが押されたら
        bu6 = findViewById(R.id.bu6);
        bu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("6");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //7ボタンが押されたら
        bu7 = findViewById(R.id.bu7);
        bu7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("7");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //8ボタンが押されたら
        bu8 = findViewById(R.id.bu8);
        bu8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("8");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //9ボタンが押されたら
        bu9 = findViewById(R.id.bu9);
        bu9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.setAnswerStr("9");
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //一字消すボタンが押されたら
        buDelete = findViewById(R.id.buDelete);
        buDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.deleteAnswerStr();
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //全て消すボタンが押されたらdeleteAnswerStr
        buAllClear = findViewById(R.id.buAllClear);
        buAllClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalcPageActivity.this.clearAnswerStr();
                answerText.setText(CalcPageActivity.this.getAnswerStr());
            }
        });
        //トップに戻るボタンが押されたら
        buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //○×がタッチされたら非表示にして次の問題へ
        textJudge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bu0.setVisibility(View.VISIBLE);
                bu1.setVisibility(View.VISIBLE);
                bu2.setVisibility(View.VISIBLE);
                bu3.setVisibility(View.VISIBLE);
                bu4.setVisibility(View.VISIBLE);
                bu5.setVisibility(View.VISIBLE);
                bu6.setVisibility(View.VISIBLE);
                bu7.setVisibility(View.VISIBLE);
                bu8.setVisibility(View.VISIBLE);
                bu9.setVisibility(View.VISIBLE);
                buDelete.setVisibility(View.VISIBLE);
                buAllClear.setVisibility(View.VISIBLE);
                buttonFinish.setVisibility(View.VISIBLE);
                judgment.setVisibility(View.VISIBLE);
                textTap.setVisibility(View.INVISIBLE);

                String rightValueStr;
                String leftValueStr;
                if (CalcPageActivity.this.getRightCount() + 1 < getQuestionSize()) {
                    CalcPageActivity.this.setRightCount(1);
                    CalcPageActivity.this.setRightValue(CalcPageActivity.this.questions.get(getRightCount()));
                    rightValueStr = String.valueOf(CalcPageActivity.this.getRightValue());
                    rightValueText.setText(rightValueStr);
                } else if (CalcPageActivity.this.getRightCount() + 1 == getQuestionSize() &&
                           CalcPageActivity.this.getLeftCount() + 1 < getQuestionSize()) {
                    CalcPageActivity.this.setLeftCount(1);
                    CalcPageActivity.this.setLeftValue(CalcPageActivity.this.questions.get(getLeftCount()));
                    leftValueStr = String.valueOf(CalcPageActivity.this.getLeftValue());
                    leftValueText.setText(leftValueStr);
                    CalcPageActivity.this.clearRightCount();
                    CalcPageActivity.this.setRightValue(CalcPageActivity.this.questions.get(getRightCount()));
                    rightValueStr = String.valueOf(CalcPageActivity.this.getRightValue());
                    rightValueText.setText(rightValueStr);
                }
                //問題番号に+1する
                CalcPageActivity.this.setQuestionNum(1);
                //入力をクリア
                CalcPageActivity.this.clearAnswerStr();
                answerText.setText(CalcPageActivity.this.getAnswerStr());

                textJudge.setText("");

                if (CalcPageActivity.this.getRightCount() + 1 == questions.size() &&
                    CalcPageActivity.this.getLeftCount() + 1 == questions.size()) {

                }
                return false;
            }
        });
    }
}
