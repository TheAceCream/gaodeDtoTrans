package com.gof.observer;

/**
 * 观察者模式/发布订阅模式
 *
 * 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同事监听某一肢体对象。
 * 这个主题对象在状态发生变化时会通知所有观察者对象，使他们能够自动更新自己。
 *
 *
 */
public class Test {
    public static void main(String[] args) {
        ConcreteSubject s = new ConcreteSubject();

        s.Attach(new ConcreteObserver("X"));
        s.Attach(new ConcreteObserver("Y"));
        s.Attach(new ConcreteObserver("Z"));

        s.setSubjectState("ABC");

        s.notifyObserver();
    }
}
