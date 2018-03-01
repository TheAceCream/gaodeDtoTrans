package com.gof.observer;

/**
 * 观察者模式 具体观察者
 */
public class ConcreteObserver implements Observer {
    private String name;
    private String observerState;

    public ConcreteObserver(String name){
        this.name = name;
    }

    @Override
    public void update(String subjectState) {
        observerState = subjectState;
        System.out.println("名称为"+name+"的观察者更新状态为"+observerState);
    }


}
