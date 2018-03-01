package com.gof.visitor;

/**
 * 访问者模式
 *
 * 表示一个作用对于对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
 *
 * 访问者模式适用于数据结构相对稳定的系统，它将数据结构和作用于结构上的操作之间的耦合解脱开，使得操作集合可以相对自由地演化。
 * 有比较稳定的数据结构，又有易于变化的算法的话，使用访问者模式就是比较适合的，因为访问者模式使得算法变得更加容易。
 *
 * 访问者模式的缺点就是使得增加新的数据结构变得困难了。
 *
 * 实例代码来源：https://www.imooc.com/article/16280?block_id=tuijian_wz
 */
public class Test {
    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.attach(new ConcreteElementA("核能"));
        os.attach(new ConcreteElementA("光能"));
        os.attach(new ConcreteElementB("暗物质"));
        os.attach(new ConcreteElementB("黑洞"));
        Visitor va = new VisitorA("张三");
        Visitor vb = new VisitorB("李四");
        os.accpet(va);
        os.accpet(vb);
    }
}
