package com.gof.state;

/**
 * 状态模式 测试类 当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类
 *
 * 状态模式主要是解决当控制一个对象状态转换的条件表达式过于复杂时的情况。
 * 把状态的判断逻辑转移到表示不同状态的一系列当中，可以吧复杂的判断逻辑简化
 *
 * 状态模式的好处是将于特定状态相关的行为局部化，并且将不同状态的行为分割开来，消除庞大的条件语句分支。
 * 通过把各种状态转移逻辑分布到State的子类之间，来减少相互间依赖
 *
 * 当一个对象的行为取决于他的状态，并且它必须在运行时刻根据状态改变他的行为时，就可以考虑使用转改模式了
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());

        //发起请求，查看下一个状态
        context.request();
        //发起请求，查看下一个状态
        context.request();
        //发起请求，查看下一个状态
        context.request();
        //发起请求，查看下一个状态
        context.request();
    }
}
