package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/21 15:36
 * Time: 14:15
 */
@Getter
@AllArgsConstructor
public enum ApproveProcedureLotEnum {

    COUNTERSIGN((byte) 1, "会签"),
    OR_SIGN((byte) 2, "或签"),
    SYSTEM_AUTO_PASS((byte) 3, "系统自动通过");

    private byte code;
    private String desc;

    public static ApproveProcedureLotEnum getByCode(byte code) {
        for (ApproveProcedureLotEnum approveProcedureLotEnum : ApproveProcedureLotEnum.values()) {
            if (approveProcedureLotEnum.getCode() == code) {
                return approveProcedureLotEnum;
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
        ApproveProcedureLotEnum approveProcedureLotEnum = getByCode(code);
        return approveProcedureLotEnum != null ? approveProcedureLotEnum.desc : null;
    }

}
