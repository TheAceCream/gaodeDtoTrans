package com.gof.intermediary;

/**
 * ConcreteMediator 具体中介者类
 */
public class ConcreteMediator extends Mediator{

    private ConcreteColleagueA concreteColleagueA;
    private ConcreteColleagueB concreteColleagueB;

    public ConcreteColleagueA getConcreteColleagueA() {
        return concreteColleagueA;
    }

    public void setConcreteColleagueA(ConcreteColleagueA concreteColleagueA) {
        this.concreteColleagueA = concreteColleagueA;
    }

    public ConcreteColleagueB getConcreteColleagueB() {
        return concreteColleagueB;
    }

    public void setConcreteColleagueB(ConcreteColleagueB concreteColleagueB) {
        this.concreteColleagueB = concreteColleagueB;
    }

    @Override
    public void send(String message, Colleague colleague) {
        if(colleague instanceof ConcreteColleagueA){
            concreteColleagueB.notify(message);
        }else{
            concreteColleagueA.notify(message);
        }
    }
}
