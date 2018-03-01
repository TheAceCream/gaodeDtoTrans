package com.gof.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式 具体通知者
 */
public class ConcreteSubject implements Subject {
    private String subjectState;
    //待通知的观察者列表
    private List<Observer> observers = null;

    public ConcreteSubject(){
        observers = new ArrayList<>();
    }

    //增加观察者
    @Override
    public void Attach(Observer observer){
        observers.add(observer);
    }

    //移除观察者
    @Override
    public void Detach(Observer observer){
        observers.remove(observer);
    }

    //通知
    @Override
    public void notifyObserver(){
        for(Observer observer:observers){
            observer.update(subjectState);
        }
    }

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
}
