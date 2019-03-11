package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 15:06
 * Time: 14:15
 *
 * 审批过程状态
 *
 */
@Getter
@AllArgsConstructor
public enum ApproveProcedureStatusEnum {

    LAUNCH_APPROVE((byte)1, "发起审批"),
    APPLY((byte)2, "已同意"),
    REFUSE((byte)3, "已拒绝"),
    APPROVEING((byte)4, "审批中"),
    //待审批前端不显示
    WAIT_APPROVE((byte)5, ""),
    CANCEL((byte)6, "已撤销"),
    INVALID((byte)7, "无效数据"),
    SYSTEM_AUTO_PASS((byte)8,"系统自动通过");

    private byte code;
    private String desc;

    public static ApproveProcedureStatusEnum getByCode(byte code) {
        for (ApproveProcedureStatusEnum approveProcedureStatusEnum : ApproveProcedureStatusEnum.values()) {
            if (approveProcedureStatusEnum.getCode()==code) {
                return approveProcedureStatusEnum;
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
        ApproveProcedureStatusEnum approveProcedureStatusEnum = getByCode(code);
        return approveProcedureStatusEnum != null ? approveProcedureStatusEnum.desc : null;
    }


}
