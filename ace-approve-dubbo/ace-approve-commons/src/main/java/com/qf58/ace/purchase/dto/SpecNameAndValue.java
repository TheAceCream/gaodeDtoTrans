package com.qf58.ace.purchase.dto;

/**
 * @author chenboyu
 * @date 2018/12/03
 */
public class SpecNameAndValue {
    //规格名称
    private String name;
    //规格值
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SpecNameAndValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public SpecNameAndValue() {
    }
}
