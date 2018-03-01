package com.gof.state;

/**
 * 状态模式 上下文Context类
 */
public class Context {
    private State state;

    public Context(State state){
        this.state = state;
        System.out.println("初始状态"+state.getClass().getCanonicalName());
    }

    public State getState() {
        return state;
    }

    //将当前状态设置到此上下文类中
    public void setState(State state) {
        this.state = state;
        System.out.println("当前状态"+state.getClass().getCanonicalName());
    }

    //对请求做处理，并设置一个状态
    public void request(){
        state.handle(this);
    }
}
