package com.gof.memento;

/**
 * 备忘录模式
 * 负责创建一个备忘录Memento，用以记录当前时刻他的内部状态，并可以使用备忘录回复内部状态。
 * Originator可根据需要决定Memento存储Originator的那些内部状态
 */
public class Originator {
    //需要保存的状态
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento CreateMemente(){
        return new Memento(state);
    }

    public void setMemento(Memento memento){
        state = memento.getState();
    }

    public void show(){
        System.out.println("状态："+state);
    }
}
