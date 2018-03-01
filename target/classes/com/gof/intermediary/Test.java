package com.gof.intermediary;

/**
 * 中介者模式
 * 用一个中介对象来封装一系列的对象交互。中介者使个对象不需要显示地相互引用，从而使其松耦合松散，而且可以独立地改变它们之间的交互。
 *
 * 中介者模式很容易在系统中应用，也很容易在系统中误用。当系统出现了‘多对多’交互复杂的对象群时，不要急于使用中介者模式，而要反思你的系统在设计上是不是不合理。
 *
 * 中介者模式一般应用于一组对象以定义良好但是复杂的方式进行通信的场合，以及定制一个分布在多个类中的行为，而又不想生成太多子类的场合
 */
public class Test {
    public static void main(String[] args) {
        ConcreteMediator m = new ConcreteMediator();
        ConcreteColleagueA a = new ConcreteColleagueA(m);
        ConcreteColleagueB b = new ConcreteColleagueB(m);

        m.setConcreteColleagueA(a);
        m.setConcreteColleagueB(b);

        a.send("吃饭了吗？");
        b.send("没有呢，你打算请客？");
    }
}
