package com.gof.bridge;

/**
 * 桥接模式 来源B
 */
public class SourceB extends Source {

    public SourceB(Arrive qiao){
        super.qiao = qiao;
    }

    @Override
    void fromAreaA() {
        System.out.println("我来自B");
    }

}
