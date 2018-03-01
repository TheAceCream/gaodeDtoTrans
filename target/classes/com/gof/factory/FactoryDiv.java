package com.gof.factory;

/**
 * 除法工厂
 */
public class FactoryDiv implements Factory{
    @Override
    public Operation createOperate() {
        return new OperationDiv();
    }
}
