package com.gof.visitor;

/**
 * Visitor类，为该对象的结构中的ConcreteElement的每一个类声明一个visit()方法
 */
public interface Visitor {
    void visitConcreteElementA(ConcreteElementA A);
    void visitConcreteElementB(ConcreteElementB B);
}
