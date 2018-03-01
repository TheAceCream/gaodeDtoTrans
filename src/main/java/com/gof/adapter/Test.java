package com.gof.adapter;

/**
 * 适配器模式
 *
 * 将一个类的接口转换成客户希望的另一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 *
 * 在想使用一个已经存在的类，但如果他爱的接口，也就是他的方法和你的要求不相同时，就应该考虑用适配器模式。
 * 两个类所做的事情相同或相似，但是具有不同的接口时要使用它，客户代码可以统一调用统一接口就行，可以更简单更直接，更高效。
 */
public class Test {
    public static void main(String[] args) {
        Target target = new Adapter();

        target.request();
    }
}
