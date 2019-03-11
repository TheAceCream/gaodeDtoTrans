package com.qf58.ace.approve.server.dao;

import com.qf58.ace.approve.dto.ApproveVisibilityDto;

import java.util.HashSet;
import java.util.List;


/**
 * 可见度Dao,包含指定部门、指定角色、指定人员
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
public interface ApproveFlowVisibilityDao {

    /**
     * 根据审批流Id删除可见度数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流Id删除可见度部门数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityDeptByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流Id删除可见度角色数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityRoleByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流Id删除可见度员工数据
     * @param approveFlowId
     * @return
     */
    int deleteVisibilityUserByApproveFlowId(Long approveFlowId);

    /**
     * 添加可见度记录 指定部门
     * @param record
     * @return
     */
    int addVisibilityDept(ApproveVisibilityDto record, Long approveFlowId);

    /**
     * 添加可见度记录 指定角色
     * @param record
     * @return
     */
    int addVisibilityRole(ApproveVisibilityDto record, Long approveFlowId);

    /**
     * 添加可见度记录 指定人员
     * @param record
     * @return
     */
    int addVisibilityUser(ApproveVisibilityDto record, Long approveFlowId);

    /**
     * 根据审批流ID获取指定部门可见的信息
     * @param approveFlowId
     * @return
     */
    List<Long> selectVisibilityDeptByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流ID获取指定角色可见的信息
     * @param approveFlowId
     * @return
     */
    List<Long> selectVisibilityRoleByApproveFlowId(Long approveFlowId);

    /**
     * 根据审批流ID获取指定员工可见的信息
     * @param approveFlowId
     * @return
     */
    List<Long> selectVisibilityUserByApproveFlowId(Long approveFlowId);


    /**
     * 根据公司ID获取审批流ID集合
     * @param companyId
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByCompId(long companyId);

    /**
     * 根据部门Id集合获取审批流ID集合
     * @param companyId
     * @param deptIds
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByDeptIds(long companyId, long... deptIds);

    /**
     * 根据角色Id集合获取审批流ID集合
     * @param companyId
     * @param roleIds
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByRoleIds(long companyId, long... roleIds);

    /**
     * 根据用户ID获取审批流ID集合
     * @param companyId
     * @param userIds
     * @return
     */
    HashSet<Long> selectApproveFlowIdsByUserId(long companyId, long userId);
}
