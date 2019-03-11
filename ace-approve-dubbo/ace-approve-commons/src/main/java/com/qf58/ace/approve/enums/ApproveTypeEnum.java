package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 15:00
 * Time: 14:15
 *
 * 审批类型
 *
 */
@Getter
@AllArgsConstructor
public enum ApproveTypeEnum {

    E_COMMERCE((byte)1, "电商");

    private byte code;
    private String desc;


    public static ApproveTypeEnum getByCode(byte code) {
        for (ApproveTypeEnum approveTypeEnum : ApproveTypeEnum.values()) {
            if (approveTypeEnum.getCode()==code) {
                return approveTypeEnum;
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
        ApproveTypeEnum approveTypeEnum = getByCode(code);
        return approveTypeEnum != null ? approveTypeEnum.desc : null;
    }

}
