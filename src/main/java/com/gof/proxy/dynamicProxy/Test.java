package com.gof.proxy.dynamicProxy;

/**
 * 代理模式 为其他对象提供一种代理 以控制对这个对象的访问
 *
 * 动态代理测试
 *
 * 1.代理对象,不需要实现接口
 * 2.代理对象的生成,是利用JDK的API,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)
 * 3.动态代理也叫做:JDK代理,接口代理
 *
 * 代理类所在包:java.lang.reflect.Proxy
 * JDK实现代理只需要使用newProxyInstance方法,但是该方法需要接收三个参数,完整的写法是:
 *
 * static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
 * 注意该方法是在Proxy类中是静态方法,且接收的三个参数依次为:
 *
 * ClassLoader loader,:指定当前目标对象使用类加载器,获取加载器的方法是固定的
 * Class<?>[] interfaces,:目标对象实现的接口的类型,使用泛型方式确认类型
 * InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
 *
 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
 */
public class Test {
    public static void main(String[] args) {
        //目标对象
        IntObjective intObjective = new Objective();
        //打印目标类型
        System.out.println(intObjective.getClass());
        //创建目标对象的代理对象
        IntObjective proxy = (IntObjective)new ProxyFactory(intObjective).getProxyInstance();
        //打印内存中生成的动态代理类型
        System.out.println(proxy.getClass());

        proxy.method();
    }
}
