package com.gof.state;

/**
 * 状态模式 具体状态B
 */
public class ConcreteStateB extends State{
    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateC());
    }
}
