package com.gof.decorate;

/**
 * 装饰类 抽象类
 */
public class Decorator implements Component{
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if(component!=null){
            component.operation();
        }
    }
}
