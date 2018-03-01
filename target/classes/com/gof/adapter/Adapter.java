package com.gof.adapter;

/**
 * 适配器模式 适配器
 */
public class Adapter extends Target{
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request(){
        adaptee.specificRequest();
    }
}
