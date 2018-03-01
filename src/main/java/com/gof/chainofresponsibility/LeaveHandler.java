package com.gof.chainofresponsibility;

/**
 * 处理人，负责处理请假申请
 */
public abstract class LeaveHandler {
    /*
     * 后面的处理人，用于传递请求
     */
    protected LeaveHandler successor;

    public void setSuccessor(LeaveHandler successor) {
        this.successor = successor;
    }

    /*
     * 处理请假申请
     */
    public abstract void disposeLeave(int day);
}
