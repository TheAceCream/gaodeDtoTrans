package com.gof.factorysimple;

/**
 * 简单工厂模式 测试类
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Operation operation = OperationFactory.createOperate("/");
        operation.setNumberA(1);
        operation.setNumberB(2);
        System.out.println(operation.getResult());
    }
}
