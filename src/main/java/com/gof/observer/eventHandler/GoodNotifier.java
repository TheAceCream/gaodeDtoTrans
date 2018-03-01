package com.gof.observer.eventHandler;

/**
 * 通知者GoodNotifier
 */
public class GoodNotifier extends Notifier {

    @Override
    public void addListener(Object object, String methodName, Object... args) {
        System.out.println("有观察者委托通知!");
        EventHandler handler = this.getEventHandler();
        handler.addEvent(object, methodName, args);
    }

    @Override
    public void notifyX() {
        System.out.println("通知所有观察者");
        try{
            this.getEventHandler().notifyX();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}