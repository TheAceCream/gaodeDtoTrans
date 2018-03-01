package com.gof.single;

/**
 * 静态内部类单例
 */
public class StaticSingle {
    private static volatile  boolean  flag = true;

    private static class Holder {
        private static StaticSingle singleton = new StaticSingle();
    }

    private StaticSingle(){
        //解决使用反射来强行调用构造器实例化单例类
        if(flag){
            flag = false;
        }else{
            throw new RuntimeException("The instance  already exists ！");
        }
    }

    public static StaticSingle getSingleton(){
        return Holder.singleton;
    }

    //解决反序列化时多次实例单例类，反序列时直接返回当前INSTANCE
    private Object readResolve() {
        return Holder.singleton;
    }
}
