package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批流空缺处理枚举
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月20日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowVacancyEnum {
    UNAVAILABLE((byte)0,"不处理"),
    AVAILABLE((byte)1,"处理");

    private Byte code;
    private String desc;
}
