package com.gof.single;

/**
 * 枚举单例模式
 * 这里有几个原因关于为什么在Java中宁愿使用一个枚举量来实现单例模式：
 *
 * 1、 自由序列化；
 *
 * 2、 保证只有一个实例（即使使用反射机制也无法多次实例化一个枚举量）；
 *
 * 3、 线程安全；
 *
 * 与静态内部类的区别：
 *      枚举单例为直接加载，静态内部类为懒加载。
 *      两者相比较，静态内部类比较节省资源开销。
 */
public enum EnumSingle {
    INSTANCE;
    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
