package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

/**
 * 审批流列表查询Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月19日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveFlowListSelectDto implements Serializable {

    private Long companyId;
    @NonNull
    private Byte state;

    private String approveFlowName;
    @NonNull
    private Byte isTest;
    @NonNull
    private Integer pageSize;
    @NonNull
    private Integer pageNo;

}
