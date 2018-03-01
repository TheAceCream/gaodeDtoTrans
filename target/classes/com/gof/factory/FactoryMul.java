package com.gof.factory;

/**
 * 乘法工厂
 */
public class FactoryMul implements Factory{
    @Override
    public Operation createOperate() {
        return new OperationMul();
    }
}
