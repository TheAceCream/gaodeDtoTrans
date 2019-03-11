package com.qf58.ace.purchase.dto;

import java.io.Serializable;

/**
 * @author chenboyu
 * @date 2018/11/20
 */
public class GetGoodsBySkuDto implements Serializable {
    private Integer code;

    private String msg;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
