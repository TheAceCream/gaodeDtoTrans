package com.gof.adapter;

/**
 * 适配器模式 需要适配的类
 */
public class Adaptee {
    public void specificRequest(){
        System.out.println("需要适配的方法");
    }
}
