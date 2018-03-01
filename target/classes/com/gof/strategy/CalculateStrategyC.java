package com.gof.strategy;

/**
 * 具体策略对象C
 */
public class CalculateStrategyC implements Strategy{

    @Override
    public double calculate() {
        System.out.println("策略C 开始计算。。。。。");
        return 0;
    }
}
