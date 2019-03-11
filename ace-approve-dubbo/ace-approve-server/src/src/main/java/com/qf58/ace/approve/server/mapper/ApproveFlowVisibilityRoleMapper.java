package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.entity.ApproveFlowVisibilityRole;
import com.qf58.ace.approve.mybatis.SqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

@SqlMapper
public interface ApproveFlowVisibilityRoleMapper {

    int deleteVisibilityRoleById(Long id);

    int addVisibilityRoleList(ApproveFlowVisibilityRole... record);

    int addVisibilityRole(ApproveFlowVisibilityRole record);

    ApproveFlowVisibilityRole getVisibilityRoleById(Long id);

    int updateVisibilityRoleById(ApproveFlowVisibilityRole record);

    /**
     * 根据审批流Id删除可见度数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流ID获取指定角色可见的信息
     * @param approveFlowId
     * @return
     */
    List<Long> selectVisibilityRoleByApproveFlowId(Long approveFlowId);

    /**
     * 根据角色Id集合获取审批流ID集合
     * @param companyId
     * @param roleIds
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByRoleIds(@Param("companyId") long companyId, @Param("array") long... roleIds);
}