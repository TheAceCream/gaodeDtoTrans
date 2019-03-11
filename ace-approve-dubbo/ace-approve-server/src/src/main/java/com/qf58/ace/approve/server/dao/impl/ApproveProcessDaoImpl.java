package com.qf58.ace.approve.server.dao.impl;

import com.qf58.ace.approve.dto.ApproveProcessWriteDto;
import com.qf58.ace.approve.entity.ApproveProcess;
import com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum;
import com.qf58.ace.approve.server.dao.ApproveProcessDao;
import com.qf58.ace.approve.server.mapper.ApproveFlowMapper;
import com.qf58.ace.approve.server.mapper.ApproveProcessMapper;
import com.qf58.ace.util.UniqueIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * 审批流程Dao
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月17日
 * @modifytime:
 */
@Repository
public class ApproveProcessDaoImpl implements ApproveProcessDao {

    @Resource
    private ApproveProcessMapper approveProcessMapper;

    @Resource
    private ApproveFlowMapper approveFlowMapper;

    /**
     * 根据审批流程链表head节点Id删除所有链表数据
     * @param headId
     * @return
     */
    @Override
    public int deleteByLinkHeadId(Long headId) {
        long id = headId;
        ApproveProcess process = null;
        for(;;){
            if(id == 0){
                break;
            }
            process = approveProcessMapper.getApproveProcessById(id);
            if(process==null){
                break;
            }
            approveProcessMapper.deleteApproveProcessById(id);
            id = process.getNextId();
        }
        return 0;
    }

    /**
     * 添加审批流程链
     * @param processDtos
     * @return 审批过程链头ID
     */
    @Override
    public Long addApproveProcess(List<ApproveProcessWriteDto> processDtos,Long approveFlowId) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        int processSize = processDtos.size();
        ApproveProcess[] processentitys = new ApproveProcess[processDtos.size()];
        ApproveProcess processEntity = null;
        //初始化审批流程单链表参数
        Long nowId = UniqueIDUtils.getUniqueID();//当前过程ID
        Long nextId = UniqueIDUtils.getUniqueID();//下个过程ID
        Long linkFirstId = nowId;//审批过程链表头ID设置为首次的当前过程ID
        for(int i = 0;i<processSize;i++){
            //获取Dto类
            ApproveProcessWriteDto processWriteDto = processDtos.get(i);
            //获取Dto中保存审批流程模块的内部类
            ApproveProcessWriteDto.ApproveModuleDto processDto = processWriteDto.getApprove();
            processEntity = new ApproveProcess();
            if(processDto!=null) {
                BeanUtils.copyProperties(processDto, processEntity);
            }
            //设置审批流程字段
            processEntity.setApproveFlowId(approveFlowId);
            processEntity.setApproveType(processWriteDto.getApproveType());
            processEntity.setApproveTypeName(ApproveFlowProcessTypeEnum.getByCode(processWriteDto.getApproveType()).getDesc());
            processEntity.setCreateTime(initTime);
            processEntity.setUpdateTime(initTime);
            processEntity.setId(nowId);
            if(i!=(processSize-1)) {
                processEntity.setNextId(nextId);
            }
            nowId = nextId;
            nextId = UniqueIDUtils.getUniqueID();
            processentitys[i] = processEntity;
        }
        approveProcessMapper.addApproveProcessList(processentitys);
        return linkFirstId;
    }

    /**
     * 获取审批流程链表
     * @param approveProcessHeadId
     * @return
     */
    @Override
    public LinkedList<ApproveProcess> selectApproveProcess(Long approveProcessHeadId) {
        LinkedList<ApproveProcess> result = new LinkedList<>();
        long selectId = approveProcessHeadId;
        ApproveProcess entity = null;
        for(;;){
            entity = approveProcessMapper.getApproveProcessById(selectId);
            if(entity==null){
                break;
            }
            if((selectId = entity.getNextId())==0L){
                result.add(entity);
                break;
            }
            result.add(entity);
        }
        return result;
    }

    /**
     * 通过审批流ID获取审批流程链表
     * @param approveFlowId
     * @return
     */
    @Override
    public LinkedList<ApproveProcess> selectApproveProcessByFlowId(Long approveFlowId) {
        Long approveProcessHeadId = approveFlowMapper.getProcessIdByApproveFlowId(approveFlowId);
        return selectApproveProcess(approveProcessHeadId);
    }
}
