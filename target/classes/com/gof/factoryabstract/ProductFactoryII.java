package com.gof.factoryabstract;

/**
 * 产品工厂B
 */
public class ProductFactoryII implements IFactory {
    @Override
    public IProductA createProductA() {
        return new ProductAA();
    }

    @Override
    public IProductB createProductB() {
        return new ProductBB();
    }
}
