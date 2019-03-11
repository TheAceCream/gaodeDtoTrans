package com.qf58.ace.approve.server.dao;

import com.qf58.ace.approve.dto.ApproveProcessWriteDto;
import com.qf58.ace.approve.entity.ApproveProcess;

import java.util.LinkedList;
import java.util.List;

/**
 * 审批过程
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
public interface ApproveProcessDao {

    /**
     * 根据审批流程链表head节点Id删除所有链表数据
     * @param headId
     * @return
     */
    int deleteByLinkHeadId(Long headId);

    /**
     * 添加审批流程链
     * @param entityList
     * @return 审批过程链头ID
     */
    Long addApproveProcess(List<ApproveProcessWriteDto> entityList, Long approveFlowId);

    /**
     * 获取审批流程链表
     * @param approveProcessHeadId
     * @return
     */
    LinkedList<ApproveProcess> selectApproveProcess(Long approveProcessHeadId);

    /**
     * 通过审批流ID获取审批流程链表
     * @param approveFlowId
     * @return
     */
    LinkedList<ApproveProcess> selectApproveProcessByFlowId(Long approveFlowId);
}
