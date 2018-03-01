package com.gof.flyweight;

/**
 * 与共享的享元对象相对的就是不共享的享元对象
 *
 * 不共享的享元模式 具体实现
 */
public class UnsharedConcreteFlyweight implements Flyweight {

    private String intrinsicState = null;

    public UnsharedConcreteFlyweight(String _intrinsicState) {
        this.intrinsicState = _intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("内蕴状态：" + intrinsicState);
        System.out.println("外蕴状态：" + extrinsicState);
    }
}
