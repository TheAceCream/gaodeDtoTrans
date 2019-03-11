package com.qf58.ace.approve.api.service;

import com.qf58.ace.approve.dto.*;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;
import com.qf58.client.support.OpResult;

import java.util.List;

/**
 * 审批流Api
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月15日
 * @modifytime:
 */
public interface ApproveFlowService {

    /**
     * 添加审批流
     * @param dto
     * @return
     */
    OpResult addApproveFlow(ApproveFlowWriteDto dto);

    /**
     * 查询审批流列表
     * @param dto
     * @return
     */
    CollectionResult<ApproveFlowListDto> selectApproveFlowList(ApproveFlowListSelectDto dto);

    /**
     * 更新审批流信息
     * @param dto
     * @return
     */
    OpResult updateApproveFlow(ApproveFlowWriteDto dto);

    /**
     * 查询审批流数据详情
     * @param approveFlowId
     * @return
     */
    DtoResult<ApproveFlowGetDto> getApproveFlowById(Long approveFlowId);

    /**
     * 更新审批流状态
     * @param approveFlowId
     * @param state
     * @return
     */
    OpResult updateApproveFlowState(Long approveFlowId, Byte state);

    /**
     * 删除审批流
     * @param approveFlowId
     * @return
     */
    OpResult deleteApprovalFlowById(Long approveFlowId);

    /**
     * 对外提供，根据审批流Id获取审批流部分信息
     * （）
     * @param approveFlowId
     * @return
     */
    DtoResult<ApproveFlowExternalGetDto> getExternalApproveFlowById(Long approveFlowId);

    /**
     * 根据当前登录用户获取审批流列表（审批服务发起审批使用）
     * @param userId
     * @return
     */
    CollectionResult<ApproveLaunchListDto> selectApproveList(Long userId, Long companyId);

    /**
     * 通过审批流可见部门，过滤审批人的部门，将审批人不可见的部门过滤掉
     * @param approveFlowId
     * @param deptIds
     * @return
     */
    CollectionResult<Long> filterDeptByApproveFlowId(Long approveFlowId, List<Long> deptIds);
}
