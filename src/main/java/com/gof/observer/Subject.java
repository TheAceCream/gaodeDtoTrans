package com.gof.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式 通知者接口
 */
public interface Subject {

    //增加观察者
    void Attach(Observer observer);

    //移除观察者
    void Detach(Observer observer);

    //通知
    void notifyObserver();
}
