package com.gof.visitor;

/**
 * 具体访问者A
 */
public class VisitorB implements Visitor{
    private String name ;

    public VisitorB(String name) {
        this.name = name ;
    }

    @Override
    public void visitConcreteElementA(ConcreteElementA A) {
        System.out.println(this.getName()+"尝试研究"+A.getName());
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB B) {
        System.out.println(this.getName()+"尝试研究"+B.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
