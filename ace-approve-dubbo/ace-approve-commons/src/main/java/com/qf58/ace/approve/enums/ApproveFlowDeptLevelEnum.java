package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批流部门层级枚举
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月20日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowDeptLevelEnum {
    ONE_DEPT_LEVEL(1,"直接层级"),
    TWO_DEPT_LEVEL(2,"第二层级"),
    THREE_DEPT_LEVEL(3,"第三层级"),
    FOUR_DEPT_LEVEL(4,"第四层级"),
    FIVE_DEPT_LEVEL(5,"第五层级"),
    SIX_DEPT_LEVEL(6,"第六层级"),
    SEVEN_DEPT_LEVEL(7,"第七层级"),
    EIGHT_DEPT_LEVEL(8,"第八层级"),
    NINE_DEPT_LEVEL(9,"第九层级"),
    TEN_DEPT_LEVEL(10,"第十层级");
    private int code;
    private String desc;

}
