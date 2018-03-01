package com.gof.visitor.example;

/**
 * 账单查看者接口（相当于Visitor）
 * 账本访问者接口，它有两个方法
 *
 * 这两个方法是重载方法，就是在上面的元素类当中用到的，当然你也可以按照访问者模式类图当中的方式去做，
 * 将两个方法分别命名为viewConsumeBill和viewIncomeBill，不过无论怎么写，这并不影响访问者模式的使用。
 */
public interface AccountBookViewer {
    //查看消费的单子
    void view(ConsumeBill bill);

    //查看收入的单子
    void view(IncomeBill bill);
}
