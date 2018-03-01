package com.gof.proxy.staticProxy;

/**
 * 代理对象，静态代理
 */
public class Proxy implements IntObjective{

    private IntObjective target;

    public Proxy(IntObjective target){
        this.target = target;
    }

    @Override
    public void method() {
        System.out.println("开始执行方法。。");
        target.method();
        System.out.println("方法执行结束。。");
    }
}
