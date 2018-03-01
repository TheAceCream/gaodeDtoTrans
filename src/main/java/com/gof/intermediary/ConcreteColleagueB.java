package com.gof.intermediary;

/**
 * 具体同事B对象
 */
public class ConcreteColleagueB extends Colleague{

    public ConcreteColleagueB(Mediator mediator){
        super(mediator);
    }

    public void send(String message){
        mediator.send(message,this);
    }

    public void notify(String message){
        System.out.println("同事B得到消息："+message);
    }
}
