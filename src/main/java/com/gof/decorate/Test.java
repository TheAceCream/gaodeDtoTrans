package com.gof.decorate;

/**
 * 装饰模式 测试类
 *
 * 动态的给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活
 *
 */
public class Test {
    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();

        d1.setComponent(c);
        d2.setComponent(d1);
        d2.operation();
    }
}
