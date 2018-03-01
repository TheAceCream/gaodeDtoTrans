package com.gof.chainofresponsibility;

/**
 * 老板，只要他同意，你可以无限期休假
 */
public class CEO extends LeaveHandler {
    @Override
    public void disposeLeave(int day) {
        //因为这里所有的假期他都可以处理所以没有判断
        System.out.println("我叫做CEO，我可以处理" + day + "的假期");
    }
}
