package com.gof.adapter;

/**
 * 适配器模式 客户端需要的接口
 */
public class Target {
    public void request(){
        System.out.println("客户端需要方法");
    }
}
