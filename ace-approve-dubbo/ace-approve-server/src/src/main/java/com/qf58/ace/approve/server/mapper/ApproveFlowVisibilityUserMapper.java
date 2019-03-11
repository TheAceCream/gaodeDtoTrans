package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.entity.ApproveFlowVisibilityUser;
import com.qf58.ace.approve.mybatis.SqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

@SqlMapper
public interface ApproveFlowVisibilityUserMapper {

    int deleteVisibilityUserById(Long id);

    int addVisibilityUserList(ApproveFlowVisibilityUser... record);

    int addVisibilityUser(ApproveFlowVisibilityUser record);

    ApproveFlowVisibilityUser getVisibilityUserById(Long id);

    int updateVisibilityUserById(ApproveFlowVisibilityUser record);

    /**
     * 根据审批流Id删除可见度数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流ID获取指定员工可见的信息
     * @param approveFlowId
     * @return
     */
    List<Long> selectVisibilityUserByApproveFlowId(Long approveFlowId);

    /**
     * 根据员工Id集合获取审批流ID集合
     * @param companyId
     * @param userId
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByUserId(@Param("companyId") long companyId, @Param("userId") long userId);
}