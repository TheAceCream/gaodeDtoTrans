package com.gof.proxy.dynamicProxy;

/**
 * 目标对象，必须实现接口
 */
public class Objective implements IntObjective{
    @Override
    public void method(){
        System.out.println("目标类方法");
    }
}
