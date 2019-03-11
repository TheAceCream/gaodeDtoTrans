package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 15:09
 * Time: 14:15
 *
 * 审批过程类型
 *
 */
@Getter
@AllArgsConstructor
public enum ApproveProcedureTypeEnum {

    APPOINT_STAIR((byte)1, "指定一级"),
    CONTINUOUS_STAIR((byte)2, "连续多级"),
    APPOINT_WORKER((byte)3, "指定员工"),
    APPOINT_ROLE((byte)4, "指定角色"),
    YOURSELF((byte)5, "发起人自己");

    private byte code;
    private String desc;

    public static ApproveProcedureTypeEnum getByCode(byte code) {

        for (ApproveProcedureTypeEnum approveProcedureTypeEnum : ApproveProcedureTypeEnum.values()) {
            if (approveProcedureTypeEnum.getCode()==code) {
                return approveProcedureTypeEnum;
            }
        }
        return null;
    }

    /**
     * 获取描述
     *
     * @param code
     * @return
     */
    public static String getDescByCode(byte code) {
        ApproveProcedureTypeEnum approveProcedureTypeEnum = getByCode(code);
        return approveProcedureTypeEnum != null ? approveProcedureTypeEnum.desc : null;
    }

}
