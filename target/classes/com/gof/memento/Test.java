package com.gof.memento;

/**
 * 备忘录模式
 *
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象回复到原先保存的状态。
 *
 * 在备忘录模式中，保存细节封装在了Memento中了，Memento模式比较适用于功能比较复杂的，但需要维护或者记录属性历史的类，
 * 或者需要保存的实行知识众多属性中的以下部分时，Originator可以根据保存的Memento信息还原到前一状态
 *
 * 如果在某个系统中使用命令模式时，需要实现命令的插销功能，那么命令模式可以使用备忘录模式来存储可撤销操作的状态
 *
 * 使用备忘录模式可以吧复杂的对象内部信息对其他的对象屏蔽起来
 */
public class Test {
    public static void main(String[] args) {
        Originator o = new Originator();
        //设置初始状态，状态属性为on
        o.setState("on");
        o.show();

        Caretaker c = new Caretaker();
        //保存状态时，由于有了很好的封装，可以隐藏Originator的实现细节
        c.setMemento(o.CreateMemente());

        //Originator改变了状态属性为off
        o.setState("off");
        o.show();

        o.setMemento(c.getMemento());
        o.show();

    }
}
