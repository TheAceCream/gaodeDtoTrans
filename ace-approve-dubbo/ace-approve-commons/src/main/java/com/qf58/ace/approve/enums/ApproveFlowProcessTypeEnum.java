package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批类型枚举
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月15日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowProcessTypeEnum {
    APPOINT((byte)0,"指定层级"),
    CONTINUITY((byte)1,"连续多级"),
    EMPLOYEE((byte)2,"指定员工"),
    ROLE((byte)3,"指定角色"),
    MYSELF((byte)4,"发起人自己");

    private Byte code;
    private String desc;

    /**
     * 根据code获取枚举实体
     * @param code
     * @return
     */
    public static ApproveFlowProcessTypeEnum getByCode(byte code){
        switch (code){
            case (byte)0:
                return APPOINT;
            case (byte)1:
                return CONTINUITY;
            case (byte)2:
                return EMPLOYEE;
            case (byte)3:
                return ROLE;
            case (byte)4:
                return MYSELF;
            default:
                return null;
        }
    }
}
