package com.gof.builder;

/**
 * 建造者模式 建造者类
 */
public abstract class Builder {
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract Product getResult();
}
