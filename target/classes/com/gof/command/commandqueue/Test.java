package com.gof.command.commandqueue;

/**
 * 命令队列的实现
 * 详情见 https://www.cnblogs.com/lfxiao/p/6825644.html
 *
 * 有时候我们需要将多个请求排队，当一个请求发送者发送一个请求时，将不止一个请求接收者产生响应，这些请求接收者将逐个执行业务方法，完成对请求的处理。此时，我们可以通过命令队列来实现。
 *
 * 命令队列与我们常说的“批处理”有点类似。批处理，顾名思义，可以对一组对象（命令）进行批量处理，当一个发送者发送请求后，将有一系列接收者对请求作出响应，命令队列可以用于设计批处理应用程序，
 * 如果请求接收者的接收次序没有严格的先后次序，我们还可以使用多线程技术来并发调用命令对象的execute()方法，从而提高程序的执行效率。
 */
public class Test {
    public static void main(String[] args) {
        Receiver receiverA = new ReceiverA();
        Command commandA = new ConcreteCommand(receiverA);

        Receiver receiverB = new ReceiverB();
        Command commandB = new ConcreteCommand(receiverB);

        CommandQueue commandQueue = new CommandQueue();
        commandQueue.addCommand(commandA);
        commandQueue.addCommand(commandB);

        Invoker invoker = new Invoker(commandQueue);
        invoker.call();
    }
}
