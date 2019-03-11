package com.qf58.ace.approve.server.dao;

import com.qf58.ace.approve.dto.*;
import com.qf58.ace.approve.entity.ApproveFlow;

import java.util.List;
import java.util.Set;


/**
 * 审批流Dao
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
public interface ApproveFlowDao {

    /**
     * 添加审批流
     * @param entity
     * @return
     */
    int addApproveFlow(ApproveFlow entity, Long approveFlowId);

    /**
     * 获取审批列表
     * @param dto
     * @return
     */
    ListDto<ApproveFlowListDto> selectApproveFlowList(ApproveFlowListSelectDto dto);

    /**
     * 根据当前登录用户获取审批流列表（审批服务发起审批使用）
     * @param ids
     * @return
     */
    List<ApproveLaunchListDto> selectApproveList(Set<Long> ids, Byte isTest);

    /**
     * 修改审批流信息
     * @param entity
     * @return
     */
    int updateApproveFlow(ApproveFlow entity);

    /**
     * 查询审批流数据详情
     * @param approveFlowId
     * @return
     */
    ApproveFlowGetDto getApproveFlowById(Long approveFlowId);

    /**
     * 对外提供，根据审批流Id获取审批流部分信息
     * @param approveFlowId
     * @return
     */
    ApproveFlowExternalGetDto getExternalApproveFlowById(Long approveFlowId);

    /**
     * 更新审批流状态
     * @param approveFlowId
     * @param state
     * @return
     */
    int updateApproveFlowState(Long approveFlowId, Byte state);

    /**
     * 删除审批流
     * @param approveFlowId
     * @return
     */
    int deleteApprovalFlowById(Long approveFlowId);
}
