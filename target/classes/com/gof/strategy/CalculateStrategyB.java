package com.gof.strategy;

/**
 * 具体策略对象B
 */
public class CalculateStrategyB implements Strategy{

    @Override
    public double calculate() {
        System.out.println("策略B 开始计算。。。。。");
        return 0;
    }
}
