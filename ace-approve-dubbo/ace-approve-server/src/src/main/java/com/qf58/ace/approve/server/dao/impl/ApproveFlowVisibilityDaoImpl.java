package com.qf58.ace.approve.server.dao.impl;

import com.qf58.ace.approve.dto.ApproveVisibilityDto;
import com.qf58.ace.approve.entity.ApproveFlowVisibilityDept;
import com.qf58.ace.approve.entity.ApproveFlowVisibilityRole;
import com.qf58.ace.approve.entity.ApproveFlowVisibilityUser;
import com.qf58.ace.approve.server.dao.ApproveFlowVisibilityDao;
import com.qf58.ace.approve.server.mapper.ApproveFlowVisibilityDeptMapper;
import com.qf58.ace.approve.server.mapper.ApproveFlowVisibilityRoleMapper;
import com.qf58.ace.approve.server.mapper.ApproveFlowVisibilityUserMapper;
import com.qf58.ace.util.UniqueIDUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

/**
 * 可见度Dao实现,包含指定部门、指定角色、指定人员
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Repository
public class ApproveFlowVisibilityDaoImpl implements ApproveFlowVisibilityDao {
    @Resource
    private ApproveFlowVisibilityDeptMapper approveFlowVisibilityDeptMapper;

    @Resource
    private ApproveFlowVisibilityRoleMapper approveFlowVisibilityRoleMapper;

    @Resource
    private ApproveFlowVisibilityUserMapper approveFlowVisibilityUserMapper;

    /**
     * 根据审批流Id删除可见度数据
     * @param approveFlowId
     * @return
     */
    @Override
    public int deleteVisibilityByApproveFlowId(Long approveFlowId) {
        approveFlowVisibilityDeptMapper.deleteVisibilityByApproveFlowId(approveFlowId);
        approveFlowVisibilityRoleMapper.deleteVisibilityByApproveFlowId(approveFlowId);
        approveFlowVisibilityUserMapper.deleteVisibilityByApproveFlowId(approveFlowId);
        return 0;
    }

    /**
     * 根据审批流Id删除可见度部门数据
     * @param approveFlowId
     * @return
     */
    @Override
    public int deleteVisibilityDeptByApproveFlowId(Long approveFlowId) {
        return approveFlowVisibilityDeptMapper.deleteVisibilityByApproveFlowId(approveFlowId);
    }

    /**
     * 根据审批流Id删除可见度角色数据
     * @param approveFlowId
     * @return
     */
    @Override
    public int deleteVisibilityRoleByApproveFlowId(Long approveFlowId) {
        return approveFlowVisibilityRoleMapper.deleteVisibilityByApproveFlowId(approveFlowId);
    }

    /**
     * 根据审批流Id删除可见度员工数据
     * @param approveFlowId
     * @return
     */
    @Override
    public int deleteVisibilityUserByApproveFlowId(Long approveFlowId) {
        return approveFlowVisibilityRoleMapper.deleteVisibilityByApproveFlowId(approveFlowId);
    }

    /**
     * 添加可见度记录 指定部门
     * @param visibilityDto
     * @return
     */
    @Override
    public int addVisibilityDept(ApproveVisibilityDto visibilityDto, Long approveFlowId) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        //创建实体
        ApproveFlowVisibilityDept visibilityDept = null;
        //提取部门和角色Id字符串，并校验
        String deptIdStr = visibilityDto.getDepartmentId();
        //设置部门可见度
        if(deptIdStr.equals("0")){
            visibilityDept = new ApproveFlowVisibilityDept()
                    .setId(UniqueIDUtils.getUniqueID())
                    .setApproveFlowId(approveFlowId)
                    .setCompanyId(visibilityDto.getCompanyId())
                    .setDepartmentId(0L)
                    .setCreateTime(initTime)
                    .setUpdateTime(initTime);
            approveFlowVisibilityDeptMapper.addVisibilityDept(visibilityDept);
        }else{
            String[] deptIds = deptIdStr.split(",");
            ApproveFlowVisibilityDept[] visibilityDeptArr = new ApproveFlowVisibilityDept[deptIds.length];
            for(int i = 0;i<visibilityDeptArr.length;i++){
                visibilityDeptArr[i] = new ApproveFlowVisibilityDept()
                        .setId(UniqueIDUtils.getUniqueID())
                        .setApproveFlowId(approveFlowId)
                        .setCompanyId(visibilityDto.getCompanyId())
                        .setDepartmentId(Long.parseLong(deptIds[i]))
                        .setCreateTime(initTime)
                        .setUpdateTime(initTime);
            }
            approveFlowVisibilityDeptMapper.addVisibilityDeptList(visibilityDeptArr);
        }
        return 0;
    }

    /**
     * 添加可见度记录 指定角色
     * @param visibilityDto
     * @return
     */
    @Override
    public int addVisibilityRole(ApproveVisibilityDto visibilityDto, Long approveFlowId) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        //创建实体
        ApproveFlowVisibilityRole visibilityRole = null;
        //提取部门和角色Id字符串，并校验
        String roleIdStr = visibilityDto.getRoleId();
        //设置角色可见度
        if(null != roleIdStr) {
            if (roleIdStr.equals("0")) {
                visibilityRole = new ApproveFlowVisibilityRole()
                        .setId(UniqueIDUtils.getUniqueID())
                        .setApproveFlowId(approveFlowId)
                        .setCompanyId(visibilityDto.getCompanyId())
                        .setRoleId(0L)
                        .setCreateTime(initTime)
                        .setUpdateTime(initTime);
                approveFlowVisibilityRoleMapper.addVisibilityRole(visibilityRole);
            } else {
                String[] roleIds = roleIdStr.split(",");
                ApproveFlowVisibilityRole[] visibilityDeptArr = new ApproveFlowVisibilityRole[roleIds.length];
                for (int i = 0; i < visibilityDeptArr.length; i++) {
                    visibilityDeptArr[i] = new ApproveFlowVisibilityRole()
                            .setId(UniqueIDUtils.getUniqueID())
                            .setApproveFlowId(approveFlowId)
                            .setCompanyId(visibilityDto.getCompanyId())
                            .setRoleId(Long.parseLong(roleIds[i]))
                            .setCreateTime(initTime)
                            .setUpdateTime(initTime);
                }
                approveFlowVisibilityRoleMapper.addVisibilityRoleList(visibilityDeptArr);
            }
        }
        return 0;
    }

    /**
     * 添加可见度记录 指定人员
     * @param visibilityDto
     * @return
     */
    @Override
    public int addVisibilityUser(ApproveVisibilityDto visibilityDto, Long approveFlowId) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        //提取部门和角色Id字符串，并校验
        String userIdStr = visibilityDto.getUserId();
        String[] userIds = userIdStr.split(",");
        ApproveFlowVisibilityUser[] visibilityUserArr = new ApproveFlowVisibilityUser[userIds.length];
        for(int i = 0;i<visibilityUserArr.length;i++){
            visibilityUserArr[i] = new ApproveFlowVisibilityUser()
                    .setId(UniqueIDUtils.getUniqueID())
                    .setApproveFlowId(approveFlowId)
                    .setCompanyId(visibilityDto.getCompanyId())
                    .setUserId(Long.parseLong(userIds[i]))
                    .setCreateTime(initTime)
                    .setUpdateTime(initTime);
        }
        approveFlowVisibilityUserMapper.addVisibilityUserList(visibilityUserArr);
        return 0;
    }

    /**
     * 根据审批流ID获取指定部门可见的信息
     * @param approveFlowId
     * @return
     */
    @Override
    public List<Long> selectVisibilityDeptByApproveFlowId(Long approveFlowId) {
        return approveFlowVisibilityDeptMapper.selectVisibilityDeptByApproveFlowId(approveFlowId);
    }

    /**
     * 根据审批流ID获取指定角色可见的信息
     * @param approveFlowId
     * @return
     */
    @Override
    public List<Long> selectVisibilityRoleByApproveFlowId(Long approveFlowId) {
        return approveFlowVisibilityRoleMapper.selectVisibilityRoleByApproveFlowId(approveFlowId);
    }

    /**
     * 根据审批流ID获取指定员工可见的信息
     * @param approveFlowId
     * @return
     */
    @Override
    public List<Long> selectVisibilityUserByApproveFlowId(Long approveFlowId) {
        return approveFlowVisibilityUserMapper.selectVisibilityUserByApproveFlowId(approveFlowId);
    }

    /**
     * 根据公司ID获取审批流ID集合
     * @param companyId
     * @return
     */
    @Override
    public HashSet<Long> selectApproveFlowIdsByCompId(long companyId) {
        return null;
    }

    /**
     * 根据部门Id集合获取审批流ID集合
     * @param companyId
     * @param deptIds
     * @return
     */
    @Override
    public HashSet<Long> selectApproveFlowIdsByDeptIds(long companyId, long... deptIds) {
        return approveFlowVisibilityDeptMapper.selectApproveFlowIdsByDeptIds(companyId, deptIds);
    }

    /**
     * 根据角色Id集合获取审批流ID集合
     * @param companyId
     * @param roleIds
     * @return
     */
    @Override
    public HashSet<Long> selectApproveFlowIdsByRoleIds(long companyId, long... roleIds) {
        return approveFlowVisibilityRoleMapper.selectApproveFlowIdsByRoleIds(companyId, roleIds);
    }

    /**
     * 根据员工Id集合获取审批流ID集合
     * @param companyId
     * @param userId
     * @return
     */
    @Override
    public HashSet<Long> selectApproveFlowIdsByUserId(long companyId, long userId) {
        return approveFlowVisibilityUserMapper.selectApproveFlowIdsByUserId(companyId, userId);
    }

}
