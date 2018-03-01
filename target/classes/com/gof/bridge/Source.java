package com.gof.bridge;

/**
 * Created by Administrator on 2018/2/6.
 */
public abstract class Source {
    //引用桥接口
    protected Arrive qiao;
    //来源地
    abstract void fromAreaA();
}
