package com.qf58.ace.approve.entity;

import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

/**
* @Date : 2018-11-16
*/
@Getter
@Setter
public class ApproveFlow {
    /**
     * 
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

    /**
     * 用途描述
     */
    private String cateDesc;

    /**
     * 控制状态（0：公司；1：部门和角色；2：员工）
     */
    private Byte controlSign;

    /**
     * 可见范围描述
     */
    private String visibilityDesc;

    /**
     * 审批流程
     */
    private Long approveProcessId;

    /**
     * 可用状态（1：可用；2：不可用）
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否测试数据（1：测试；2：非测试）
     */
    private Byte isTest;

}