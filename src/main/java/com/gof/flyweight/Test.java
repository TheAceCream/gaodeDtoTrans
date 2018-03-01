package com.gof.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * 享元模式
 *
 * 运用共享技术有效地支持大量细粒度的对象
 *
 * 重点理解单纯享元模式
 *
 * 复合享元模式示例代码个人认为有改造空间，只适合理解复合享元含义，不适合开发中直接使用
 *
 * 示例链接：http://blog.csdn.net/lemon_tree12138/article/details/51241598
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        //test.ordinaryFlyweight();

        test.compositeFlyweight();
    }

    /**
     * 单纯享元模式，以及不共享的祥元模式测试
     */
    public void ordinaryFlyweight(){
        FlyweightFactory factory = new FlyweightFactory();

        Flyweight fly1 = factory.factory("Hello");
        fly1.operation("ExtrinsicState-1");

        Flyweight fly2 = factory.factory("DesignPattern");
        fly2.operation("ExtrinsicState-2");

        Flyweight fly3 = factory.factory("Flyweight");
        fly3.operation("ExtrinsicState-3");

        Flyweight fly4 = factory.factory("Hello");
        fly4.operation("ExtrinsicState-4");

        System.out.println("fly1 == fly2 ? " + (fly1 == fly2));
        System.out.println("fly1 == fly3 ? " + (fly1 == fly3));
        System.out.println("fly1 == fly4 ? " + (fly1 == fly4));

        Flyweight fly5 = new UnsharedConcreteFlyweight("Unshared");
        fly5.operation("ExtrinsicState-5");
    }

    /**
     * 复合享元模式测试
     */
    public void compositeFlyweight() {
        List<String> intrinsicStates = new ArrayList<String>();
        intrinsicStates.add("Hello");
        intrinsicStates.add("Java");
        intrinsicStates.add("DesignPattern");
        intrinsicStates.add("Flyweight");

        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.compositeFactory(intrinsicStates);
        Flyweight flyweight2 = factory.compositeFactory(intrinsicStates);
        System.out.println("flyweight1 == flyweight2 ? " + (flyweight1 == flyweight2));

        flyweight1.operation("复合享元-1");
        flyweight2.operation("复合享元-2");
    }
}
