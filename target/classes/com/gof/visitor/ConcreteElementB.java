package com.gof.visitor;

/**
 * ConcreteElementB，具体元素，实现accept操作
 */
public class ConcreteElementB implements Element{

    private String name ;

    ConcreteElementB(String name){
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
