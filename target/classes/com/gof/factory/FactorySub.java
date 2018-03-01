package com.gof.factory;

/**
 * 减法工厂
 */
public class FactorySub implements Factory{
    @Override
    public Operation createOperate() {
        return new OperationSub();
    }
}
