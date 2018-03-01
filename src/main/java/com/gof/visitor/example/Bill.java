package com.gof.visitor.example;

/**
 * 首先我们给出账本的接口，它只有一个方法accept。
 *
 * 单个单子的接口（相当于Element）
 *
 * 其中的方法参数AccountBookViewer是一个账本访问者接口
 */
public interface Bill {
    void accept(AccountBookViewer viewer);
}
