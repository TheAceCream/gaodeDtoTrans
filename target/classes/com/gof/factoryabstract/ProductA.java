package com.gof.factoryabstract;

/**
 * 具体产品A
 */
public class ProductA implements IProductA {
    @Override
    public void productMethod() {
        System.out.println("产品A的实现方法");
    }
}
