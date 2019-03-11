package com.qf58.ace.approve.server.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf58.ace.approve.dto.*;
import com.qf58.ace.approve.entity.ApproveFlow;
import com.qf58.ace.approve.enums.ApproveFlowStateEnum;
import com.qf58.ace.approve.server.dao.ApproveFlowDao;
import com.qf58.ace.approve.server.mapper.ApproveFlowMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
@Repository
public class ApproveFlowDaoImpl implements ApproveFlowDao {

    @Resource
    private ApproveFlowMapper approveFlowMapper;

    /**
     * 添加审批流
     * @param entity
     * @return
     */
    @Override
    public int addApproveFlow(ApproveFlow entity, Long approveFlowId) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        entity.setId(approveFlowId);
        entity.setCreateTime(initTime);
        entity.setUpdateTime(initTime);
        return approveFlowMapper.addApproveFlow(entity);
    }

    /**
     * 审批流列表查询
     * @param dto
     * @return
     */
    @Override
    public ListDto<ApproveFlowListDto> selectApproveFlowList(ApproveFlowListSelectDto dto) {
        ListDto<ApproveFlowListDto> result = new ListDto<>();

        try {
            //使用PageHelper对查询进行分页
            PageHelper.startPage(dto.getPageNo(),dto.getPageSize());
            List<ApproveFlowListDto> approveFlowsList = approveFlowMapper.selectApproveFlowList(dto.getCompanyId(), dto.getState(), dto.getApproveFlowName(), dto.getIsTest());
            PageInfo page = new PageInfo(approveFlowsList);
            if(approveFlowsList==null||approveFlowsList.size()==0){
                result.empty();
            }
            result.setList(approveFlowsList);
            result.setTotal(page.getTotal());
        }catch(Exception e){
            result.fail();
        }
        return result;
    }

    /**
     * 可发起审批列表查询
     * @param ids
     * @return
     */
    @Override
    public List<ApproveLaunchListDto> selectApproveList(Set<Long> ids, Byte isTest) {
        return approveFlowMapper.selectApproveList(ids,isTest);
    }

    /**
     * 修改审批流信息
     * @param entity
     * @return
     */
    @Override
    public int updateApproveFlow(ApproveFlow entity) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        entity.setUpdateTime(initTime);
        return approveFlowMapper.updateApproveFlow(entity);
    }

    /**
     * 查询审批流数据详情
     * @param approveFlowId
     * @return
     */
    @Override
    public ApproveFlowGetDto getApproveFlowById(Long approveFlowId) {
        ApproveFlow entity = approveFlowMapper.getApproveFlowById(approveFlowId);
        ApproveFlowGetDto dto = new ApproveFlowGetDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    /**
     * 对外提供，根据审批流Id获取审批流部分信息
     * @param approveFlowId
     * @return
     */
    @Override
    public ApproveFlowExternalGetDto getExternalApproveFlowById(Long approveFlowId) {
        return approveFlowMapper.getExternalApproveFlowById(approveFlowId);
    }

    /**
     * 更新审批流
     * @param approveFlowId
     * @param state
     * @return
     */
    @Override
    public int updateApproveFlowState(Long approveFlowId, Byte state) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        ApproveFlow entity = new ApproveFlow();
        entity.setId(approveFlowId);
        entity.setState(state);
        entity.setUpdateTime(initTime);
        return approveFlowMapper.updateApproveFlow(entity);
    }

    /**
     * 删除审批流
     * @param approveFlowId
     * @return
     */
    @Override
    public int deleteApprovalFlowById(Long approveFlowId) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        ApproveFlow entity = new ApproveFlow();
        entity.setId(approveFlowId);
        entity.setState(ApproveFlowStateEnum.DELETE.getCode());
        entity.setUpdateTime(initTime);
        return approveFlowMapper.updateApproveFlow(entity);
    }

}
