package com.gof.observer.eventHandler;

/**
 * 观察者模式 委托
 */
public class Test {
    public static void main(String[] args) {
        //创建一个尽职尽责的放哨者
        Notifier goodNotifier = new GoodNotifier();

        //创建观察者
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();
        //添加通知
        goodNotifier.addListener(observerA, "methodA", "通知观察者A");
        goodNotifier.addListener(observerB, "methodB", "通知观察者B");

        try {
            //一点时间后
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通知
        goodNotifier.notifyX();
    }
}
