package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审批模式
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月15日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum ApproveFlowModeEnum {
    COUNTERSIGN((byte)0,"会签"),
    ANDSIGN((byte)1,"或签"),
    SYSTEM_AUTO_PASS((byte)2,"系统自动通过");

    private Byte code;
    private String desc;

    /**
     * 根据code获取枚举实体
     * @param code
     * @return
     */
    public static ApproveFlowModeEnum getByCode(byte code){
        switch (code){
            case (byte)0:
                return COUNTERSIGN;
            case (byte)1:
                return ANDSIGN;
            case (byte)2:
                return SYSTEM_AUTO_PASS;
            default:
                return null;
        }
    }

}
