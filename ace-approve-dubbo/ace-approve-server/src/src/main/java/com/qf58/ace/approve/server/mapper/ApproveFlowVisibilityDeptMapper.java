package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.entity.ApproveFlowVisibilityDept;
import com.qf58.ace.approve.mybatis.SqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

@SqlMapper
public interface ApproveFlowVisibilityDeptMapper {

    int deleteVisibilityDeptById(Long id);

    int addVisibilityDeptList(ApproveFlowVisibilityDept... record);

    int addVisibilityDept(ApproveFlowVisibilityDept record);

    ApproveFlowVisibilityDept getVisibilityDeptById(Long id);

    int updateVisibilityDeptById(ApproveFlowVisibilityDept record);

    /**
     * 根据审批流Id删除可见度数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流ID获取指定部门可见的信息
     * @param approveFlowId
     * @return
     */
    List<Long> selectVisibilityDeptByApproveFlowId(Long approveFlowId);

    /**
     * 根据部门Id集合获取审批流ID集合
     * @param companyId
     * @param deptIds
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByDeptIds(@Param("companyId") long companyId, @Param("array") long... deptIds);


}