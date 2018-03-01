package com.gof.factory;


/**
 * 加法工厂
 */
public class FactoryAdd implements Factory{
    @Override
    public Operation createOperate() {
        return new OperationAdd();
    }
}
