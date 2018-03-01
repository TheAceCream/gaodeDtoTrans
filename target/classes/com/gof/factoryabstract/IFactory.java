package com.gof.factoryabstract;

/**
 * 抽象工厂接口
 */
public interface IFactory {
    IProductA createProductA();

    IProductB createProductB();
}
