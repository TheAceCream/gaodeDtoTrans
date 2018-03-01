package com.gof.factory;

/**
 * 乘法运算类
 */
public class OperationMul extends Operation {

    @Override
    public double getResult() {
        return getNumberA() * getNumberB();
    }
}