package com.gof.templates;

/**
 * 实现模板对象A
 */
public class ConcreteClassA extends Templates{

    @Override
    public void primitiveOperationA() {
        System.out.println("实现模板对象A--具体方法A的实现");
    }

    @Override
    public void primitiveOperationB() {
        System.out.println("实现模板对象A--具体方法B的实现");
    }
}
