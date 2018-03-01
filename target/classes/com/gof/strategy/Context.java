package com.gof.strategy;

/**
 * 策略模式上下文，用于调用具体算法
 */
public class Context {

    private Strategy strategy;

    public static final CalculateStrategyA STRATEGY_A = new CalculateStrategyA();

    public static final CalculateStrategyB STRATEGY_B = new CalculateStrategyB();

    public static final CalculateStrategyC STRATEGY_C = new CalculateStrategyC();

    //初始化时传入具体策略对象
    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    //调用具体策略对象的计算结果
    public double getCalculationResult(){
        return strategy.calculate();
    }


}
