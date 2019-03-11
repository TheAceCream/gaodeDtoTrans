package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 审批流对外开放查询Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveFlowExternalGetDto implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 审批流名称
     */
    private String approveFlowName;

    /**
     * 公司Id
     */
    private Long companyId;

    /**
     * 审批流用途(一级类目Id)
     */
    private Long cateId;

    /**
     * 审批流用途(一级类目名称)
     */
    private String cateName;

    /**
     * 可用状态（1：可用；2：不可用；3：删除）
     */
    private Byte state;
}
