package com.qf58.ace.finance.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: WangZhan
 * @Description:
 * @Date Created in 10:55 2018/11/23.
 */
@Data
public class OrderBillsSearchDto {

    private Long departmentId;

    private Long orderId;

    private Long approveId;

    private Date submitTimeStart;

    private Date submitTimeEnd;

    private Integer pageSize;

    private Integer pageNo;
}
