package com.gof.builder;

/**
 * 具体建造者对象B
 */
public class ConcreteBuilderB extends Builder{
    //要建造的对象
    private Product product = new Product();
    @Override
    public void buildPartA() {
        product.add("具体建造者对象B 创建部件A");
    }

    @Override
    public void buildPartB() {
        product.add("具体建造者对象B 创建部件B");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
