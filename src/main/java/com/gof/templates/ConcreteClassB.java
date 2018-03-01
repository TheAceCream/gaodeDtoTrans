package com.gof.templates;

/**
 * 实现模板对象B
 */
public class ConcreteClassB extends Templates{

    @Override
    public void primitiveOperationA() {
        System.out.println("实现模板对象B--具体方法A的实现");
    }

    @Override
    public void primitiveOperationB() {
        System.out.println("实现模板对象B--具体方法B的实现");
    }
}
