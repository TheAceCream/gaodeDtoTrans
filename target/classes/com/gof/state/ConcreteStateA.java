package com.gof.state;

/**
 * 状态模式 具体状态A
 */
public class ConcreteStateA extends State{
    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateB());
    }
}
