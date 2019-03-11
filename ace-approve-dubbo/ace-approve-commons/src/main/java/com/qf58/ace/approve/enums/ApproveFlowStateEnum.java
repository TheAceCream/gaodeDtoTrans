package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批流状态枚举
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月20日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowStateEnum {
    AVAILABLE((byte)1,"可用"),
    UNAVAILABLE((byte)2,"不可用"),
    DELETE((byte)3,"已删除");

    private byte code;
    private String desc;
}
