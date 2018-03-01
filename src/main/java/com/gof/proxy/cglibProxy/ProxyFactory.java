package com.gof.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子类代理工厂
 * 对 Objective 内存中动态创建一个子类对象
 */
public class ProxyFactory implements MethodInterceptor{
    //维护目标对象
    private Object objective;

    public ProxyFactory(Object objective){
        this.objective = objective;
    }

    //为目标对象创建一个代理对象
    public Object getInstance(){
        //工具类
        Enhancer en = new Enhancer();
        //设置父类
        en.setSuperclass(objective.getClass());
        //设置回调函数
        en.setCallback(this);
        //创建子类（代理对象）
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始执行方法。。。。。");
        //执行目标对象方法
        Object returnValue = method.invoke(objective,objects);

        System.out.println("方法执行结束。。。。。");
        return returnValue;
    }
}
