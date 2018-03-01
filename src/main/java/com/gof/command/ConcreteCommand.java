package com.gof.command;

/**
 * 命令模式
 * ConcreteCommand类，将一个接受者对象绑定于一个动作，调用接受者相应的造作，以实现execute
 */
public class ConcreteCommand extends Command{

    public ConcreteCommand(Receiver receiver){
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
