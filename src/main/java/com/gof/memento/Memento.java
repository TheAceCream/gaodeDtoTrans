package com.gof.memento;

/**
 * 备忘录模式
 * 负责存储Originator对象的内部状态，并客防止Originator意外的其他对象访问备忘录Memento。
 * 备忘录有两个接口，Caretaker只能看到备忘录的窄接口，它只能将备忘录传递给其他对象Originator能
 * 看到一个宽接口，允许它访问返回到先前状态所需的所有数据。
 */
public class Memento {
    //需要保存的状态
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
