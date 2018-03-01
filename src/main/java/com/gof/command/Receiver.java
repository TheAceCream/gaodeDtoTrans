package com.gof.command;

/**
 * 命令模式
 * Receiver类，只待如何实施与执行一个与请求相关的操作，任何类都可能作为一个接收者。
 */
public class Receiver {
    public void action(){
        System.out.println("执行请求");
    }
}
