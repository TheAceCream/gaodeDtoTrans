package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.dto.ApproveFlowExternalGetDto;
import com.qf58.ace.approve.dto.ApproveFlowListDto;
import com.qf58.ace.approve.dto.ApproveLaunchListDto;
import com.qf58.ace.approve.entity.ApproveFlow;
import com.qf58.ace.approve.mybatis.SqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@SqlMapper
public interface ApproveFlowMapper {

    int deleteApproveFlowById(Long id);

    /**
     * 通过审批流id获取审批流程id
     * @param id
     * @return
     */
    Long getProcessIdByApproveFlowId(Long id);

    /**
     * 添加审批流
     * @param entity
     * @return
     */
    int addApproveFlow(ApproveFlow entity);

    /**
     * 查询审批流数据详情
     * @param id
     * @return
     */
    ApproveFlow getApproveFlowById(Long id);

    /**
     * 修改审批流信息
     * @param entity
     * @return
     */
    int updateApproveFlow(ApproveFlow entity);

    /**
     * 审批流列表查询
     * @param companyId
     * @param state
     * @param approveFlowName
     * @return
     */
    List<ApproveFlowListDto> selectApproveFlowList(@Param("companyId") Long companyId, @Param("state") Byte state, @Param("approveFlowName") String approveFlowName, @Param("isTest") Byte isTest);

    /**
     * 对外提供，根据审批流Id获取审批流部分信息
     * @param approveFlowId
     * @return
     */
    ApproveFlowExternalGetDto getExternalApproveFlowById(Long approveFlowId);

    /**
     * 根据当前登录用户获取审批流列表（审批服务发起审批使用）
     * @param ids
     * @return
     */
    List<ApproveLaunchListDto> selectApproveList(@Param("set") Set<Long> ids, @Param("isTest") Byte isTest);
}