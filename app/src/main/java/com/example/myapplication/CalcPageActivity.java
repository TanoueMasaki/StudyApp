package com.example.myapplication;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
    private List<Integer> questions;//問題作成用配列
    String[] currentResults;
    List<String[]> results;
    TextView leftValueText;
    TextView rightValueText;
    TextView answerText;
    TextView textJudge;
    TextView textTap;
    TextView opeText;
    String opeStr;
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
    Button judgment;
    Button buttonFinish;
    Button buAllClear;
    Button buDelete;
    ImageView maru;
    ImageView batsu;
    LocalDate date;
    LocalTime time;
    Calculation calculation;

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
    public  void setQuestions(List<Integer> array ){
        this.questions = array;
    }
    public void setResults(String[] array){
        this.results.add(array);
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
    public List<Integer> getQuestions(){
        return  this.questions;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_page);

        //MainActivityから渡されたデータをフィールドに代入する
        this.setInitialVal(getIntent().getIntExtra("INITIAL", 0));
        this.setFinalVal(getIntent().getIntExtra("FINAL", 0));
        this.setOpeInt(getIntent().getIntExtra("OPE", 0));
        opeStr = getIntent().getStringExtra(("OPE_STR"));

        //前回結果を初期化
        try {
            FileOutputStream fileOutputstreamTh = openFileOutput("thisResults.txt", MODE_PRIVATE);
            fileOutputstreamTh.write(null);
        }catch (Exception e){
            e.printStackTrace();
        }

        //クラスのインスタンス化
        calculation = new Calculation();

        //日時の取得（API26以降）
        date = LocalDate.now();
        time = LocalTime.now();

        //フィールドに渡された数値を引数で渡してArrayList型配列を取得、this.questionsに入れる
        this.setQuestions(calculation.arraySet(this.getInitialVal(), this.getFinalVal()));
        //引き算か割り算なら両辺とも初期値を最大値にする
        if(getOpeInt() == 1 || getOpeInt() == 3){
            setLeftCount(getQuestionSize()-1);
            setRightCount(getQuestionSize()-1);
        }

        //リスナー登録
        leftValueText = findViewById(R.id.leftValue);
        rightValueText = findViewById(R.id.rightValue);
        answerText = findViewById(R.id.answer);
        textJudge = findViewById(R.id.textJudge);
        textTap = findViewById(R.id.textTap);
        maru = findViewById(R.id.imageViewMaru);
        batsu = findViewById(R.id.imageViewBatsu);
        buttonFinish = findViewById(R.id.buttonFinish);
        bu0 = findViewById(R.id.bu0);
        bu1 = findViewById(R.id.bu1);
        bu2 = findViewById(R.id.bu2);
        bu3 = findViewById(R.id.bu3);
        bu4 = findViewById(R.id.bu4);
        bu5 = findViewById(R.id.bu5);
        bu6 = findViewById(R.id.bu6);
        bu7 = findViewById(R.id.bu7);
        bu8 = findViewById(R.id.bu8);
        bu9 = findViewById(R.id.bu9);
        judgment = findViewById(R.id.judgment);
        buDelete = findViewById(R.id.buDelete);
        buAllClear = findViewById(R.id.buAllClear);
        opeText = findViewById(R.id.ope);

        //演算子を画面に表示
        opeText.setText(opeStr);

        //判定後に表示する〇×と処理用のTextViewを非表示にする
        textTap.setVisibility(View.INVISIBLE);
        textJudge.setVisibility(View.INVISIBLE);
        maru.setVisibility(View.INVISIBLE);
        batsu.setVisibility(View.INVISIBLE);

        //問題の左辺と右辺にそれぞれ配列の初期値を代入して画面に表示
        this.setLeftValue(questions.get(getLeftCount()));
        String leftValueStr = String.valueOf(this.getLeftValue());
        leftValueText.setText(leftValueStr);
        this.setRightValue(questions.get(getRightCount()));
        String rightValueStr = String.valueOf(this.getRightValue());
        rightValueText.setText(rightValueStr);

    }

    //判定ボタンが押されたら
    public void onClickJudgment(View view) {
        currentResults = new String[7];
        results = new ArrayList<>();

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

        textJudge.setVisibility(View.VISIBLE);
        textTap.setVisibility(View.VISIBLE);

        String answerStr = answerText.getText().toString();
        int answer = Integer.parseInt(answerStr);
        int answerCalc = calculation.makeCalculation(
                this.getOpeInt(),
                this.getLeftValue(),
                this.getRightValue()
        );
        //正誤判定後、結果を配列に入れる
        if (answerCalc == answer) {
            maru.setVisibility(View.VISIBLE);
            currentResults[3] = "正解";
        } else {
            batsu.setVisibility(View.VISIBLE);
            currentResults[3] = "不正解";
        }
        //問題番号、問題、正解、入力した数値を配列に入れる
        currentResults[0] = String.valueOf(date);//日付
        currentResults[1] = String.valueOf(time);//時間
        currentResults[2] = String.valueOf(getQuestionNum());//問題番号
        currentResults[4] = leftValue + opeStr + rightValue;//問題
        currentResults[5] = String.valueOf(answerCalc);//正解
        currentResults[6] = String.valueOf(answer);//入力した解答

        //問題終了時のResult画面に表示する用に今回分の問題すべてをListに格納する
        this.setResults(currentResults);

        //現在の問題の結果を","区切りで１つの文字列してcurrentResultに代入
        String currentResult = String.join(",", currentResults);
        //今回結果を","区切りで１つの文字列してthisResultsに代入
        String thisResults = currentResults[2] + "問目,";
        thisResults += currentResults[3] + ",";
        thisResults += currentResults[4] + "=";
        thisResults += currentResults[5] + ",";
        thisResults += currentResults[6] + ",";

        //String thisResult = String.join(",", getThisResults());
        //次回以降にも結果を閲覧できる用に内部ストレージに保存しておく
        try{
            FileOutputStream fileOutputstreamCr = openFileOutput("results.csv", MODE_APPEND);
            fileOutputstreamCr.write(currentResult.getBytes());
            fileOutputstreamCr.write('\n');
            FileOutputStream fileOutputstreamTh = openFileOutput("thisResults.txt", MODE_APPEND);
            fileOutputstreamTh.write(thisResults.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //○×がタップされたら非表示にして次の問題へ
    public void onClickTextJudge(View view) {

        if(this.getOpeInt() == 0 || this.getOpeInt() == 2){
            if(this.getRightCount() + 1 == questions.size() &&
                    this.getLeftCount() + 1 == questions.size()) {

                Intent intentTest = new Intent(CalcPageActivity.this, ResultActivity.class);
                startActivity(intentTest);
            }else {
                nextQuestionIncreasing();
            }
        }else if(this.getOpeInt() == 1 || this.getOpeInt() == 3){
            if(this.getRightCount() == 0 && this.getLeftCount() == 0) {
                Intent intentTest = new Intent(CalcPageActivity.this, ResultActivity.class);
                startActivity(intentTest);
            }else {
                nextQuestionDecreases();
            }
        }
        //各ボタンの再表示
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

        //○×の非表示
        textTap.setVisibility(View.INVISIBLE);
        textJudge.setVisibility(View.INVISIBLE);
        maru.setVisibility(View.INVISIBLE);
        batsu.setVisibility(View.INVISIBLE);

        //問題番号に+1する
        this.setQuestionNum(1);
        //入力をクリア
        this.clearAnswerStr();
        answerText.setText(this.getAnswerStr());
    }

    public void nextQuestionIncreasing(){
        //次の問題へ
        String rightValueStr;
        String leftValueStr;
        if (this.getRightCount() + 1 < getQuestionSize()) {
            this.setRightCount(1);
            this.setRightValue(this.questions.get(getRightCount()));
            rightValueStr = String.valueOf(this.getRightValue());
            rightValueText.setText(rightValueStr);
        } else if (this.getRightCount() + 1 == getQuestionSize() &&
                this.getLeftCount() + 1 < getQuestionSize()) {
            this.setLeftCount(1);
            this.setLeftValue(this.questions.get(getLeftCount()));
            leftValueStr = String.valueOf(this.getLeftValue());
            leftValueText.setText(leftValueStr);
            this.clearRightCount();
            this.setRightValue(this.questions.get(getRightCount()));
            rightValueStr = String.valueOf(this.getRightValue());
            rightValueText.setText(rightValueStr);
        }
    }
    public void nextQuestionDecreases(){
        //次の問題へ
        String rightValueStr;
        String leftValueStr;

        //RightCountが0でなければ（インデックスが０でなければ）
        if (getRightCount() != 0) {
            //RightCount-1して右辺を1つ減らす
            this.setRightCount(-1);
            this.setRightValue(this.questions.get(getRightCount()));
            rightValueStr = String.valueOf(this.getRightValue());
            rightValueText.setText(rightValueStr);
        } else if (getRightCount() == 0 && getLeftCount() != 0) {
            this.setLeftCount(-1);
            this.setLeftValue(this.questions.get(getLeftCount()));
            leftValueStr = String.valueOf(this.getLeftValue());
            leftValueText.setText(leftValueStr);
            //RightCountから１ずつ減らして代入したいのでLeftCountを代入する
            this.setRightCount(getLeftCount());
            this.setRightValue(this.questions.get(getRightCount()));
            rightValueStr = String.valueOf(this.getRightValue());
            rightValueText.setText(rightValueStr);
        }
    }

    //トップに戻るボタンが押されたら
    public void onClickButtonFinish(View view) {
            finish();
        }

    //0ボタンが押されたら
    public void onClickBu0(View view) {
        this.setAnswerStr("0");
        answerText.setText(this.getAnswerStr());
    }

    //1ボタンが押されたら
    public void onClickBu1(View view) {
        this.setAnswerStr("1");
        answerText.setText(this.getAnswerStr());
    }

    //2ボタンが押されたら
    public void onClickBu2(View view) {
        this.setAnswerStr("2");
        answerText.setText(this.getAnswerStr());
    }

    //3ボタンが押されたら
    public void onClickBu3(View view) {
        this.setAnswerStr("3");
        answerText.setText(this.getAnswerStr());
    }

    //4ボタンが押されたら
    public void onClickBu4(View view) {
        this.setAnswerStr("4");
        answerText.setText(this.getAnswerStr());
    }

    //5ボタンが押されたら
    public void onClickBu5(View view) {
        this.setAnswerStr("5");
        answerText.setText(this.getAnswerStr());
    }

    //6ボタンが押されたら
    public void onClickBu6(View view) {
        this.setAnswerStr("6");
        answerText.setText(this.getAnswerStr());
    }

    //7ボタンが押されたら
    public void onClickBu7(View view) {
        this.setAnswerStr("7");
        answerText.setText(this.getAnswerStr());
    }

    //8ボタンが押されたら
    public void onClickBu8(View view) {
        this.setAnswerStr("8");
        answerText.setText(this.getAnswerStr());
    }

    //9ボタンが押されたら
    public void onClickBu9(View view) {
        this.setAnswerStr("9");
        answerText.setText(this.getAnswerStr());
    }

    //一字消すボタンが押されたら
    public void onClickBuDelete(View view) {
        this.deleteAnswerStr();
        answerText.setText(this.getAnswerStr());
    }

    //全て消すボタンが押されたら
    public void onClickBuAllClear(View view) {
        this.clearAnswerStr();
        answerText.setText(this.getAnswerStr());
    }

    @Override
    public void onPause(){
        super.onPause();

        this.finish();
    }
}
