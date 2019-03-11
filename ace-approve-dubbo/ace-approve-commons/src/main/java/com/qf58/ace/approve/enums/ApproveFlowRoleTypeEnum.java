package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批流角色关系类型枚举
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月21日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowRoleTypeEnum {
    AND((byte)0,"与"),
    OR((byte)1,"或");

    private Byte cate;
    private String desc;
}
