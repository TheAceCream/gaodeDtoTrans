package com.gof.visitor;

/**
 * ConcreateElementA，具体元素，实现accept操作
 */
public class ConcreteElementA implements Element{

    private String name ;

    ConcreteElementA(String name){
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
