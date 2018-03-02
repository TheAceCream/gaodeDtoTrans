package com.gof.command.revoke;

/**
 * 工作流超级简化类 也作为备忘录的发起类
 */
public class Workflow {
    private int state = 0;

    public Workflow(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //创建Workflow备份
    public Workflow createMemento(){
        //此处创建备份的方法，可以new新对象，也可以采用原型模式，由于例子简单，此处new新对象
        return new Workflow(state);
    }
}
