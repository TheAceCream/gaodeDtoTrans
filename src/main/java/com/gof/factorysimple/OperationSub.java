package com.gof.factorysimple;

/**
 * 减法运算类
 */
public class OperationSub extends Operation{

    @Override
    public double getResult() {
        return getNumberA() - getNumberB();
    }
}
