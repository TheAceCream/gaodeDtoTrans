package com.gof.command.commandqueue;

/**
 * 请求接收者Receiver类具体实现对请求的业务处理，它提供了action()方法，用于执行与请求相关的操作，其典型代码如下所示
 */
public class ReceiverA implements Receiver{
    @Override
    public void action() {
        System.out.println("命令执行A");
    }
}
