package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 14:57
 * Time: 14:15
 *
 * 审批状态
 *
 */
@Getter
@AllArgsConstructor
public enum ApproveStatusEnum {

    IN_APPROVE((byte)1, "审批中"),
    PASS_APPROVE((byte)2, "审批同意"),
    REFUSE_APPROVE((byte)3, "审批拒绝"),
    CANCEL_APPROVE((byte)4, "审批撤销");

    private byte code;
    private String desc;

    public static ApproveStatusEnum getByCode(byte code) {
        for (ApproveStatusEnum approveStatusEnum : ApproveStatusEnum.values()) {
            if (approveStatusEnum.getCode()==code) {
                return approveStatusEnum;
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
        ApproveStatusEnum approveStatusEnum = getByCode(code);
        return approveStatusEnum != null ? approveStatusEnum.desc : null;
    }



}
