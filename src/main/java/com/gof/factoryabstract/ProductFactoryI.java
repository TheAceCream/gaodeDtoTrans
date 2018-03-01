package com.gof.factoryabstract;

/**
 * 产品工厂A
 */
public class ProductFactoryI implements IFactory {
    @Override
    public IProductA createProductA() {

        return new ProductA();
    }

    @Override
    public IProductB createProductB() {
        return new ProductB();
    }
}
