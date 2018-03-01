package com.gof.chainofresponsibility;

/**
 * 项目经理可以批准一天的假期
 */
public class Lead extends LeaveHandler {
    @Override
    public void disposeLeave(int day) {
        if (day <=  1) {
            System.out.println("我叫做胡经理，我可以处理" + day + "天的假期");
        } else {
            // 如果他处理不了就向上传递请求
            System.out.println("胡经理处理不了" + day + "天的假期");
            successor.disposeLeave(day);
        }
    }
}
