package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 审批流信息查询DTO
* @Date : 2018-11-16
*/
@Getter
@Setter
public class ApproveFlowGetDto implements Serializable {
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

    /**
     * 控制状态（0：公司；1：部门和角色；2：员工）
     */
    private Byte controlSign;

    /**
     * 审批流程Id
     */
    private Long approveProcessId;

    /**
     * 可见员工
     */
    private List<IdAndNameDto> users;

    /**
     * 部门是否已全选
     */
    private boolean deptIsAll;

    /**
     * 可见部门
     */
    private List<IdAndNameDto> departments;

    /**
     * 角色是否已全选
     */
    private boolean roleIsAll;

    /**
     * 可见角色
     */
    private List<IdAndNameDto> roles;

    /**
     * 审批过程
     */
    private List<ApproveProcessReadDto> approveProcess;
}