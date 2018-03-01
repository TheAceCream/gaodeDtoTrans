package com.gof.state;

/**
 * 状态模式 抽象状态类
 */
public abstract class State {
    public abstract void handle(Context context);
}
