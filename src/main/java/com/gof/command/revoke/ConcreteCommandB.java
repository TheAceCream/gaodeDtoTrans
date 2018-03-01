package com.gof.command.revoke;

/**
 * Created by Administrator on 2018/2/7.
 */
public class ConcreteCommandB implements Command {

    private Receiver receiver; //维持一个对请求接收者对象的引用

    public ConcreteCommandB(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void undo() {
        System.out.println("撤销命令B");
    }
}
