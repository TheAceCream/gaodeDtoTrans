package com.gof.builder;

/**
 * 建造者模式 测试类
 *
 * 建造者模式 讲一个复杂对象的构建与他的表示分离，使得同样的构建过程可以创建不同的表示
 *
 * 主要用于创建一些复杂的对象，这些对象内部构建间的构建顺序通常是稳定的，但对象内部的个构建通常面临着复杂的变化
 *
 * 建造者模式的好处就是使得建造代码与表示代码分离，由于建造者隐藏了该产品是如何组装的，所以若需要修改一个产品的内部表示，
 * 只需要在定义一个具体的建造者就可以了
 *
 * 建造者模式是当在创建复杂对象的算法应该独立于该对象的组成部分以及他们的装配方式时适用的模式
 */
public class Test {
    public static void main(String[] args) {
        //初始化执行者
        Director director = new Director();
        //初始化构造着
        Builder b1 = new ConcreteBuilderA();
        Builder b2 = new ConcreteBuilderB();

        //执行构建 b1
        director.Construct(b1);
        //获取构建结果
        Product p1 = b1.getResult();
        p1.show();

        //执行构建 b2
        director.Construct(b2);
        //获取构建结果
        Product p2 = b2.getResult();
        p2.show();
    }
}
