package com.gof.appearance;

/**
 * 外观类
 */
public class Facade {
    SubSystemOne one;
    SubSystemTwo two;
    SubSystemThree three;
    SubSystemFour four;

    public Facade(){
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }

    public void methodA(){
        System.out.println("方法组A执行-----");
        one.MethodOne();
        two.MethodTwo();
    }

    public void methodB(){
        System.out.println("方法组B执行-----");
        three.MethodThree();
        four.MethodFour();
    }
}
