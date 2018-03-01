package com.gof.proxy.staticProxy;

/**
 * 代理模式 为其他对象提供一种代理 以控制对这个对象的访问
 *
 * 静态代理测试
 *
 * 静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 *
 * 需要注意的是,代理对象与目标对象要实现相同的接口,然后通过调用相同的方法来调用目标对象的方法
 *
 * 静态代理总结:
 * 1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
 * 2.缺点:
 *
 * 因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
 * 如何解决静态代理中的缺点呢?答案是可以使用动态代理方式
 *
 */
public class Test {
    public static void main(String[] args) {
        //目标对象
        IntObjective target = new Objective();
        //代理对象，把目标对象传给代理，建立代理关系
        Proxy proxy = new Proxy(target);

        proxy.method();
    }
}
