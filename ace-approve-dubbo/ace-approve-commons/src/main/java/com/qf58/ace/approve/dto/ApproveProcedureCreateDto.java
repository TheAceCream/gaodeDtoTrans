package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 生成审批过程方法入参Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月21日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveProcedureCreateDto implements Serializable {

    /**
     * 公司Id
     */
    private Long companyId;

    /**
     * 部门Id
     */
    private Long deptId;

    /**
     * 发起人Id
     */
    private Long originator;

    /**
     * 审批Id
     */
    private Long approveId;

    /**
     * 发起人名称
     */
    private String initiator;

    /**
     * 审批标题
     */
    private String title;

    /**
     * 审批流ID
     */
    private Long approveFlowId;

    /**
     * 是否测试
     */
    private Byte isTest;
}
