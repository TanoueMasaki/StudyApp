package com.example.myapplication;


import com.example.myapplication.Operator;

import java.util.ArrayList;
//計算用クラス
public class Calculation {


    public int makeCalculation(int opeInt,int leftValue,int rightValue){
    	int answer = 0;

        switch (opeInt) {
            case 0 :
            answer = leftValue + rightValue;
            break;
            case 1 :
            answer = leftValue - rightValue;
            break;
            case 2 :
            answer = leftValue * rightValue;
            break;
            case 3 :
            answer = leftValue / rightValue;
            break;
        }
        return answer;
    }
   
    //引数（初期値から最終値）までが入った配列ArrayList型arrayを作成して返す
    public ArrayList<Integer> arraySet(int initialVal,int finalVal) {
    	ArrayList<Integer> array = new ArrayList<>();
    	for(int i = initialVal;i <= finalVal;i++) {
    		array.add(i);
    	}
		return array;
	}
}

