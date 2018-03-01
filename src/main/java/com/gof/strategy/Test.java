package com.gof.strategy;

/**
 * 策略模式 测试方法
 * 策略模式 他定义了算法家族，分别封装起来，让他们之间可以相互替换，此模式让算法的变化，不会影响到使用算法的客户
 *
 *
 * 策略模式是一种定义一系列算法的方法，从概念上来看，所有的这些算法完成的都是相同的工作，
 * 只是实现不同，它可以以相同的方调用泳所有的算法，减少了各种算法类与使用算法类时间的耦合
 *
 */
public class Test {
    public static void main(String[] args) {
     /*
        Context contextA = new Context(new CalculateStrategyA());
        contextA.getCalculationResult();

        Context contextB = new Context(new CalculateStrategyB());
        contextB.getCalculationResult();

        Context contextC = new Context(new CalculateStrategyC());
        contextC.getCalculationResult();*/

        /*----------------------------------------------------------*/

        Context contextAA = new Context(Context.STRATEGY_A);
        contextAA.getCalculationResult();

        Context contextBB = new Context(Context.STRATEGY_B);
        contextBB.getCalculationResult();

        Context contextCC = new Context(Context.STRATEGY_C);
        contextCC.getCalculationResult();

        //具体还有待改进，比如说怎么能够做到上面的Context 不用实例化就能初始化具体策略
    }
}
