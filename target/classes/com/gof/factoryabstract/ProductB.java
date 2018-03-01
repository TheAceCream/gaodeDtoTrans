package com.gof.factoryabstract;

/**
 * 具体产品B
 */
public class ProductB implements IProductB {
    @Override
    public void productMethod() {
        System.out.println("产品B的实现方法");
    }
}
