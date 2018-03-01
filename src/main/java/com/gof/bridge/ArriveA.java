package com.gof.bridge;

/**
 * 桥接模式 目的地A
 */
public class ArriveA implements Arrive {

    @Override
    public void targetAreaB() {
        System.out.println("我要去A");
    }

}
