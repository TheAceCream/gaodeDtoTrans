package com.gof.visitor.example;

/**
 * 访问者模式 应用实例
 *
 * 来看一个财务方面的简单例子。财务的账本就可以作为一个对象结构，而它其中的元素有两种，收入和支出，这满足我们访问者模式的要求，
 * 即元素的个数是稳定的，因为账本中的元素设定中只有收入和支出。
 *
 * 查看账本的人可能有这样几种，比如老板，会计事务所的注会，财务主管，等等。而这些人在看账本的时候显然目的和行为是不同的。
 *
 * 实例代码来源：https://www.imooc.com/article/16280?block_id=tuijian_wz
 */
public class Test {
    public static void main(String[] args) {
        AccountBook accountBook = new AccountBook();
        //添加两条收入
        accountBook.addBill(new IncomeBill(10000, "卖商品"));
        accountBook.addBill(new IncomeBill(12000, "卖广告位"));
        //添加两条支出
        accountBook.addBill(new ConsumeBill(1000, "工资"));
        accountBook.addBill(new ConsumeBill(2000, "材料费"));
        AccountBookViewer boss = new Boss();
        AccountBookViewer cpa = new CPA();
        //两个访问者分别访问账本
        accountBook.show(cpa);
        accountBook.show(boss);
        ((Boss) boss).getTotalConsume();
        ((Boss) boss).getTotalIncome();
    }
}
