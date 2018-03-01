package com.gof.builder;

/**
 * 具体建造者对象A
 */
public class ConcreteBuilderA extends Builder{
    //要建造的对象
    private Product product = new Product();
    @Override
    public void buildPartA() {
        product.add("具体建造者对象A 创建部件X");
    }

    @Override
    public void buildPartB() {
        product.add("具体建造者对象A 创建部件Y");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
