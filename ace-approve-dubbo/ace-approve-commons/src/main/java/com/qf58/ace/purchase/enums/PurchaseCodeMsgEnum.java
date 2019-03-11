package com.qf58.ace.purchase.enums;

/**
 * @author chenboyu
 * @date 2018/11/20
 */
public enum PurchaseCodeMsgEnum {
    SUCCESS(0,"查询成功"),
    FALIED(1,"查询失败");
    private Integer code;
    private String msg;




    PurchaseCodeMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
