package com.example.myapplication;

public enum Operator {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("×"),
    DIVISION("÷");

    private Operator operator;
    private String opeStr;
    private Operator(String opeStr){
        this.opeStr = opeStr;
    }
    public String getValue(){
        return this.opeStr;
    }
    public Operator getOperator(int opeOrdinal){
        Operator[] values = Operator.values();
        this.operator = values[opeOrdinal];
        return this.operator;
    }
}