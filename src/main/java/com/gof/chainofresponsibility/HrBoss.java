package com.gof.chainofresponsibility;

/**
 * 人事部门老大可以批准一个星期内的假期
 */
public class HrBoss extends LeaveHandler {
    @Override
    public void disposeLeave(int day) {
        if (day <= 5) {
            System.out.println("我叫做张老大，我可以处理" + day + "天内的假期");
        } else {
            // 如果他处理不了就向上传递请求
            System.out.println("张老大处理不了" + day + "天的假期");
            successor.disposeLeave(day);
        }
    }
}
