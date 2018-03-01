package com.gof.command.commandqueue;

/**
 * 对于请求发送者即调用者而言，将针对抽象命令类进行编程，可以通过构造注入或者设值注入的方式在运行时传入具体命令类对象，并在业务方法中调用命令对象的execute()方法，其典型代码如下所示
 *
 * 在增加了命令队列类CommandQueue以后，请求发送者类Invoker将针对CommandQueue编程，代码修改如下
 */
public class Invoker{
        private CommandQueue commandQueue; //维持一个CommandQueue对象的引用

        //构造注入
        public Invoker(CommandQueue commandQueue) {
            this. commandQueue = commandQueue;
        }

        //设值注入
        public void setCommandQueue(CommandQueue commandQueue) {
            this.commandQueue = commandQueue;
        }

        //调用CommandQueue类的execute()方法
        public void call() {
            commandQueue.execute();
        }
}
