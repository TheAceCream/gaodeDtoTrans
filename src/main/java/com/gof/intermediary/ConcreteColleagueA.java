package com.gof.intermediary;

/**
 * 具体同事A对象
 */
public class ConcreteColleagueA extends Colleague{

    public ConcreteColleagueA(Mediator mediator){
        super(mediator);
    }

    public void send(String message){
        mediator.send(message,this);
    }

    public void notify(String message){
        System.out.println("同事A得到消息："+message);
    }
}
