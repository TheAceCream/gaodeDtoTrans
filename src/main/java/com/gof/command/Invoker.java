package com.gof.command;

/**
 * 命令模式
 * Invoker类，要求该命令执行这个请求
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand(){
        command.execute();
    }
}
