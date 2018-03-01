package com.gof.bridge;

/**
 * 桥接模式 目的地C
 */
public class ArriveC implements Arrive {

    @Override
     public void targetAreaB() {
        System.out.println("我要去C");
     }

}
