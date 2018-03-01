package com.gof.state;

/**
 * 状态模式 具体状态C
 */
public class ConcreteStateC extends State{
    @Override
    public void handle(Context context) {
        context.setState(new ConcreteStateA());
    }
}
