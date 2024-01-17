package com.example.myapplication;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    String[] currentResults;
    List<String> results;
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
    public String getResults(){
        String resultsStr = "";

        for(int i = 0;i < results.size();i++){
            resultsStr += results.get(i) + "\n";
        }
        return resultsStr;
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

        //クラスのインスタンス化
        calculation = new Calculation();

        //日時の取得（API26以降）
        date = LocalDate.now();
        time = LocalTime.now();

        //フィールドに渡された数値を引数で渡してArrayList型配列を取得、this.questionsに入れる
        this.setQuestions(calculation.arraySet(this.getInitialVal(), this.getFinalVal()));

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

        //○×がタッチされたら非表示にして次の問題へ
//        textJudge.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                bu0.setVisibility(View.VISIBLE);
//                bu1.setVisibility(View.VISIBLE);
//                bu2.setVisibility(View.VISIBLE);
//                bu3.setVisibility(View.VISIBLE);
//                bu4.setVisibility(View.VISIBLE);
//                bu5.setVisibility(View.VISIBLE);
//                bu6.setVisibility(View.VISIBLE);
//                bu7.setVisibility(View.VISIBLE);
//                bu8.setVisibility(View.VISIBLE);
//                bu9.setVisibility(View.VISIBLE);
//                buDelete.setVisibility(View.VISIBLE);
//                buAllClear.setVisibility(View.VISIBLE);
//                buttonFinish.setVisibility(View.VISIBLE);
//                judgment.setVisibility(View.VISIBLE);
//
//                textTap.setVisibility(View.INVISIBLE);
//                textJudge.setVisibility(View.INVISIBLE);
//                maru.setVisibility(View.INVISIBLE);
//                batsu.setVisibility(View.INVISIBLE);
//
//                String rightValueStr;
//                String leftValueStr;
//                if (CalcPageActivity.this.getRightCount() + 1 < getQuestionSize()) {
//                    CalcPageActivity.this.setRightCount(1);
//                    CalcPageActivity.this.setRightValue(CalcPageActivity.this.questions.get(getRightCount()));
//                    rightValueStr = String.valueOf(CalcPageActivity.this.getRightValue());
//                    rightValueText.setText(rightValueStr);
//                } else if (CalcPageActivity.this.getRightCount() + 1 == getQuestionSize() &&
//                           CalcPageActivity.this.getLeftCount() + 1 < getQuestionSize()) {
//                    CalcPageActivity.this.setLeftCount(1);
//                    CalcPageActivity.this.setLeftValue(CalcPageActivity.this.questions.get(getLeftCount()));
//                    leftValueStr = String.valueOf(CalcPageActivity.this.getLeftValue());
//                    leftValueText.setText(leftValueStr);
//                    CalcPageActivity.this.clearRightCount();
//                    CalcPageActivity.this.setRightValue(CalcPageActivity.this.questions.get(getRightCount()));
//                    rightValueStr = String.valueOf(CalcPageActivity.this.getRightValue());
//                    rightValueText.setText(rightValueStr);
//                }
//                //問題番号に+1する
//                CalcPageActivity.this.setQuestionNum(1);
//                //入力をクリア
//                CalcPageActivity.this.clearAnswerStr();
//                answerText.setText(CalcPageActivity.this.getAnswerStr());
//
//                if (CalcPageActivity.this.getRightCount() + 1 == questions.size() &&
//                    CalcPageActivity.this.getLeftCount() + 1 == questions.size()) {
//
//                }
//                return false;
//            }
//        });
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

        //現在の問題の結果を","区切りで１つの文字列してcurrentResultに代入
        String currentResult = String.join(",", currentResults);
        //問題終了時のResult画面に表示する用に今回分の問題すべてをArrayListに格納する
        results.add(currentResult);
        //次回以降にも結果を閲覧できる用に内部ストレージに保存しておく
        try{
            FileOutputStream fileOutputstream = openFileOutput("results.csv", MODE_APPEND);
            fileOutputstream.write(currentResult.getBytes());
            fileOutputstream.write('\n');
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //判定後処理用のTextViewをタップしたら
    public void onClickTextJudge(View view) {
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
        textJudge.setVisibility(View.INVISIBLE);
        maru.setVisibility(View.INVISIBLE);
        batsu.setVisibility(View.INVISIBLE);

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

        //問題が終わったら結果を表示して終了
        if (this.getRightCount() + 1 == questions.size() &&
            this.getLeftCount() + 1 == questions.size()) {
            DialogFragment dialogFragment = new MyDialogFragment("解答結果",getResults());
            dialogFragment.setCancelable(false);
            dialogFragment.show(getSupportFragmentManager(), "my_dialog");
            System.out.println(getResults());
        }else{
            //問題番号に+1する
            this.setQuestionNum(1);
            //入力をクリア
            this.clearAnswerStr();
            answerText.setText(this.getAnswerStr());
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
}
