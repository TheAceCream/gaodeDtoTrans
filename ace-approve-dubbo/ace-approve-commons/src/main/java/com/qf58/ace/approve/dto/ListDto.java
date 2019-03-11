package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 承接Dao列表数据的Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月19日
 * @modifytime:
 */
@Getter
@Setter
public class ListDto<T> {

    private List<T> list;

    private Long total;

    private Boolean result = true;

    private Boolean empty = false;

    public void empty(){
        empty = true;
    }

    public void fail(){
        result = false;
    }
}
