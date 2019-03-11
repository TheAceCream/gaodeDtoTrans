package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 发起审批列表DTO
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Getter
@Setter
@ToString
public class ApproveLaunchListDto implements Serializable {
    /**
     * 审批流ID
     */
    private Long id;

    /**
     * 审批流名称
     */
    private String approveFlowName;

    /**
     * icon图片url
     */
    private String icon;

    /**
     * 公司Id
     */
    private Long companyId;

    /**
     * 审批流用途(一级类目)
     */
    private Long cateId;

}
