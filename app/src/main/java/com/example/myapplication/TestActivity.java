package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    //フィールド
    private int initialVal ;
    private int finalVal ;
    private int leftValue;
    private int rightValue;
    private int answer;
    private int questionNum = 1;
    private Operator ope;

    private ArrayList<Integer> questions;//問題作成用配列


    //クラスのインスタンス化


    //セッター
    public void setInitialVal(int value){
        this.initialVal = value;
    }
    public void setFinalVal(int value){
        this.finalVal = value;
    }
    public void setOpe(Operator ope){
        this.ope = ope;
    }
    public void setLeftValue(int value){
        this.leftValue = value;
    }
    public void setRightValue(int value){
        this.rightValue = value;
    }
    public void setAnswer(int ans){
        this.answer = ans;
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
    public Operator getOpe(){
        return this.ope;
    }
    public int getLeftValue(){
        return this.leftValue;
    }
    public int getRightValue(){
        return this.rightValue;
    }
    public int getAnswer(){
        return this.answer;
    }
    public int getQuestionNum(){
        return this.questionNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        int initial = getIntent().getIntExtra("INITIAL",0);


        Calculation calc = new Calculation();

        //メインページで選択された演算方法を取得してthis.opeに代入する
        //this.setOpe(main.getOpe());
        //メインページで入力された数値を引数で渡してArrayList型配列を取得する
        this.setQuestions(calc.arraySet(this.getInitialVal(),this.getFinalVal()));

        String[][] results = new String[5][questions.size() * questions.size()];//解答結果用配列

        //画面表示用に各TextViewをインスタンス化しておく
//        TextView leftValueText = findViewById(R.id.leftValue);
//        TextView rightValueText = findViewById(R.id.rightValue);
//        TextView opeText = findViewById(R.id.ope);
//        TextView answerText = findViewById(R.id.answer);
//        TextView trueOrFalseText = findViewById(R.id.trueOrFalse);

        //Operatorの種類で判別して文字列としての演算子を取得、opeTextにセットする
       //String opeStr = calculation.setOpe(this.ope);
        //opeText.setText(opeStr);

        TextView testText = findViewById(R.id.testText);
        //testText.setText(main.getOpe().name());


        System.out.println(initial);

        //testText.setText(Operator.ADDITION.name());


    }
}