package com.qf58.ace.approve.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 测试枚举字段
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月19日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum IsTestEnum {

    TEST((byte)1,"测试"),
    UNTEST((byte)2,"非测试");

    private byte code;
    private String desc;

}
