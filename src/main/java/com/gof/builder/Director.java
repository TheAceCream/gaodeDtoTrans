package com.gof.builder;

/**
 * 建造者模式 指挥者类
 */
public class Director {
    public void Construct(Builder builder){
        builder.buildPartA();
        builder.buildPartB();
    }
}
