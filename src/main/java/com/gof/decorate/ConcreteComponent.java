package com.gof.decorate;

/**
 * 装饰模式 具体操作类
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
