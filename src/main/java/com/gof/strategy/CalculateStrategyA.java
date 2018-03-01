package com.gof.strategy;

/**
 * 具体策略对象A
 */
public class CalculateStrategyA implements Strategy{

    @Override
    public double calculate() {
        System.out.println("策略A 开始计算。。。。。");
        return 0;
    }
}
