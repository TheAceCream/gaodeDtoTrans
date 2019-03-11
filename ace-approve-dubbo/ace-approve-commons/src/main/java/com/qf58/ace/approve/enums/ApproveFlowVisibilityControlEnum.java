package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *审批可见度控制位枚举
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月17日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowVisibilityControlEnum {
    COMPANY_LEVEL((byte)0,"指定公司"),
    DEPT_AND_ROLE_LEVEL((byte)1,"指定部门和角色"),
    USER_LEVEL((byte)2,"指定员工");

    private Byte code;
    private String desc;
}
