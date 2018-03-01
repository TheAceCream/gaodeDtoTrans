package com.gof.bridge;

/**
 * 桥接模式 目的地B
 */
public class ArriveB implements Arrive {

    @Override
    public void targetAreaB() {
        System.out.println("我要去B");
    }

}
