package com.gof.factory;

/**
 * 工厂模式 测试类
 *
 * 工厂方法模式 定义了一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个雷的实例化延迟到其子类。
 *
 * 简单工厂的最大优点在于 工厂类中包含了必要的逻辑判断，根据客户的选择条件实例化相关的类，对于客户端来说，去除了与具体产品的依赖
 *
 * 工厂方法模式实现时，客户端需要决定实例化哪一个工厂来实现运算类，选择判断的问题还是存在的，
 * 也就是说，工厂方法把简单的工厂的内部逻辑判断移到了客户端代码来进行。
 *
 * 你想要加功能简单工厂中本来是要改工厂类，而现在是修改客户端。
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Factory factory = new FactoryAdd();

        //Factory factory = new FactoryDiv();

        //Factory factory = new FactoryMul();

        //Factory factory = new FactorySub();

        Operation operation = factory.createOperate();

        operation.setNumberA(1);
        operation.setNumberB(2);
        System.out.println(operation.getResult());
    }
}
