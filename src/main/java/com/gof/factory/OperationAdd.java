package com.gof.factory;

/**
 * 添加运算类
 */
public class OperationAdd extends Operation {

    @Override
    public double getResult() {
        return getNumberA() + getNumberB();
    }
}
