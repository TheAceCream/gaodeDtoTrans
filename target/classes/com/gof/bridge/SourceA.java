package com.gof.bridge;

/**
 * 桥接模式 来源A
 */
public class SourceA extends Source {

    public SourceA(Arrive qiao){
        super.qiao = qiao;
    }

    @Override
    void fromAreaA() {
        System.out.println("我来自A");
    }

}
