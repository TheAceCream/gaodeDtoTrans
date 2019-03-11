package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批流列表Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月19日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveFlowListDto implements Serializable {
    /**
     * 审批流ID
     */
    private Long id;

    /**
     * 公司Id
     */
    private Long companyId;

    /**
     * 审批流名称
     */
    private String approveFlowName;

    /**
     * 用途Id
     */
    private Long cateId;

    /**
     * 用途描述
     */
    private String cateDesc;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新时间格式化
     */
    private String updateTimeDesc;

    /**
     * 控制状态（0：公司；1：部门和角色；2：员工）
     */
    private Byte controlSign;

    /**
     * 可见范围描述
     */
    private String visibilityDesc;

    /**
     * 可用状态（1：可用；2：不可用）
     */
    private Byte state;

    /**
     * 可见部门描述
     */
    private String departmentDesc;

    /**
     * 可见角色描述
     */
    private String roleDesc;

    /**
     * 可见人员描述
     */
    private String userDesc;
}
