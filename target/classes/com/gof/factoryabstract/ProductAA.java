package com.gof.factoryabstract;

/**
 * 具体产品A
 */
public class ProductAA implements IProductA {
    @Override
    public void productMethod() {
        System.out.println("产品AA的实现方法");
    }
}
