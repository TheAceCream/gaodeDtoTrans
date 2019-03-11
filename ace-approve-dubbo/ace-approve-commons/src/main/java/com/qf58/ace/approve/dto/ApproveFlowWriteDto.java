package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 添加、修改审批流Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveFlowWriteDto implements Serializable {
    /**
     * 审批流ID
     */
    private Long id;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 审批流名称
     */
    private String approveFlowName;

    /**
     * icon图片url
     */
    private String icon;

    /**
     * 用途/类目
     */
    private Long cateId;

    /**
     * 可见控制状态
     * 0:公司;1:部门和角色;2:员工
     */
    private Byte controlSign = 0;

    /**
     * 审批流程Id
     */
    private Long approveProcessId;

    /**
     * 可见度
     */
    private ApproveVisibilityDto visibility;

    /**
     * 审批流程
     */
    private List<ApproveProcessWriteDto> approveProcess;

    /**
     * 测试标记（1测试 2非测试）
     */
    private Byte isTest;
}
