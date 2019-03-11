package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 审批流程读操作Dto
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveProcessReadDto implements Serializable {
    /**
     * 审批类型(0:指定层级；1:连续多级；2:指定员工；3:指定角色；4:发起人自己)
     */
    private Byte approveType;

    /**
     * 审批模块
     */
    private ApproveModuleDto approve;

    @Getter
    @Setter
    public static class ApproveModuleDto implements Serializable{
        /**
         * 审批模块ID
         */
        private Long id;

        /**
         * 下级审批id
         */
        private Long nextId;

        /**
         * 审批流id
         */
        private Long approveFlowId;

        /**
         * 审批类型名称
         */
        private String approveTypeName;

        /**
         * 指定审批层级
         */
        private Integer approveDept;

        /**
         * 指定审批终点
         */
        private Integer approveEnd;

        /**
         * 审批人角色
         */
        private List<IdAndNameDto> approveRole;

        /**
         * 审批角色关系（0：与；1：或）
         */
        private Byte roleType;

        /**
         * 审批方式（0：会签；1：或签；2：系统自动通过）
         */
        private Byte approveMode;

        /**
         * 审批方式描述
         */
        private String approveModeDesc;

        /**
         * 空缺处理（0：不处理；1：处理）
         */
        private Byte vacancy;

        /**
         * 指定员工ID拼接串
         */
        private List<IdAndNameDto> approveEmployee;

        /**
         * 描述
         */
        private String approveProcessDesc;
    }
}
