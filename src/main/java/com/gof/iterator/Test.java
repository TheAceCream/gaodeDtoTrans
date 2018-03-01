package com.gof.iterator;

/**
 * 迭代器模式
 *
 * 提供一种方法顺序访问一个聚合对象中各个元素，而又不暴露该对象的内部表示
 *
 * 当你需要访问一个聚集对象，而且不管这些对象是什么都需要便利的时候，你就应该考虑迭代器模式。
 *
 * 迭代器模式就是分离了集合对象的便利行为，抽象出一个迭代器来负责，这样既可以做到不暴露集合内部的结构，
 * 又可让外部代码透明地访问集合内部的数据。
 *
 * 代码源自网络 感觉能够真正意义上凸显迭代器模式优势的一个实例
 */
public class Test {
    public static void main(String[] args) {
        //Order order = new JDOrder();
        Order order = new TBOrder();

        for(Item item:order){
            System.out.println(item);
        }
    }
}
