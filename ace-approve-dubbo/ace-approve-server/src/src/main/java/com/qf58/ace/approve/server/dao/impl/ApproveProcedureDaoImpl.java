package com.qf58.ace.approve.server.dao.impl;

import com.qf58.ace.approve.dto.ApproveProcedureDto;
import com.qf58.ace.approve.dto.ApproveProcedureSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.Approve;
import com.qf58.ace.approve.entity.ApproveProcedure;
import com.qf58.ace.approve.enums.ApproveProcedureStatusEnum;
import com.qf58.ace.approve.enums.ApproveStatusEnum;
import com.qf58.ace.approve.server.dao.ApproveProcedureDao;
import com.qf58.ace.approve.server.mapper.ApproveMapper;
import com.qf58.ace.approve.server.mapper.ApproveProcedureMapper;
import com.qf58.ace.util.UniqueIDUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/11/17 11:24 Time: 14:15
 */
@Repository
public class ApproveProcedureDaoImpl implements ApproveProcedureDao {

    @Resource
    private ApproveMapper approveMapper;

    @Resource
    private ApproveProcedureMapper approveProcedureMapper;

    /**
     * 添加审批过程
     */
    @Override
    public Long addApproveProcedure(ApproveProcedureDto approveProcedureDto) {
        ApproveProcedure approveProcedure = new ApproveProcedure();
        if (approveProcedureDto.getId() == null) {
            approveProcedure.setId(UniqueIDUtils.getUniqueID());
        }
        BeanUtils.copyProperties(approveProcedureDto, approveProcedure);
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        approveProcedure.setCreateTime(initTime);
        approveProcedureMapper.insertSelective(approveProcedure);
        return approveProcedure.getId();
    }

    @Override
    public int addApproveProcedureList(List<ApproveProcedure> list) {
        return approveProcedureMapper.insertApproveProcedureList(list);
    }


    /**
     * 获取所有审批过程列表
     *
     * @param approveProcedureSelectDto 审批查询Dto
     * @return 审批过程列表
     */
    @Override
    public ListDto<ApproveProcedureDto> getApproveProcedureList(ApproveProcedureSelectDto approveProcedureSelectDto) {
        ListDto<ApproveProcedureDto> result = new ListDto<>();
        List<ApproveProcedureDto> approveProcedureDtoList = new ArrayList<>();
        //拿到审批过程列表
        List<ApproveProcedure> approveProcedureList = approveProcedureMapper.getApproveProcedureList(approveProcedureSelectDto);
        if (approveProcedureList != null) {
            for (ApproveProcedure temp : approveProcedureList) {
                ApproveProcedureDto approveProcedureDto = new ApproveProcedureDto();
                BeanUtils.copyProperties(temp, approveProcedureDto);
                approveProcedureDtoList.add(approveProcedureDto);
            }
            result.setList(approveProcedureDtoList);
        }
        return result;
    }

    /**
     * 获取审批过程列表 (不带无效的审批过程)
     *
     * @param approveProcedureSelectDto 审批查询Dto
     * @return 审批过程列表
     */
    @Override
    public ListDto<ApproveProcedureDto> getApproveProcedureListWithoutInvalid(ApproveProcedureSelectDto approveProcedureSelectDto) {
        ListDto<ApproveProcedureDto> result = new ListDto<>();
        List<ApproveProcedureDto> approveProcedureDtoList = new ArrayList<>();
        //拿到审批过程列表
        List<ApproveProcedure> approveProcedureList = approveProcedureMapper.getApproveProcedureListForDetail(approveProcedureSelectDto);
        if (approveProcedureList != null) {
            Long approveId = approveProcedureList.get(0).getApproveId();
            //审批过程所属的审批
            Approve approve = approveMapper.selectByPrimaryKey(approveId);
            for (ApproveProcedure temp : approveProcedureList) {
                ApproveProcedureDto approveProcedureDto = new ApproveProcedureDto();
                //其中排除掉 无效 状态的 审批过程
                if (!temp.getStatus().equals(ApproveProcedureStatusEnum.INVALID.getCode())) {
                    BeanUtils.copyProperties(temp, approveProcedureDto);
                    approveProcedureDtoList.add(approveProcedureDto);
                }
            }
            //如果是已经撤销的审批, 则末尾添加一条 已撤销
            if (approve.getStatus().equals(ApproveStatusEnum.CANCEL_APPROVE.getCode())) {
                ApproveProcedureDto cancelDto = new ApproveProcedureDto();
                cancelDto.setId(UniqueIDUtils.getUniqueID());
                cancelDto.setApproveTime(approve.getEndTime());
                cancelDto.setStatus(ApproveProcedureStatusEnum.CANCEL.getCode());
                cancelDto.setApproverId(approve.getSponsorId());
                cancelDto.setApprover(approve.getSponsorDesc());
                approveProcedureDtoList.add(cancelDto);
            }
            result.setList(approveProcedureDtoList);
        }
        return result;
    }

    /**
     * 更新审批过程
     *
     * @param approveProcedureDto 审批过程Dto
     * @return 更改条数
     */
    @Override
    public Long updateApproveProcedure(ApproveProcedureDto approveProcedureDto) {
        return approveProcedureMapper.updateByPrimaryKeySelective(approveProcedureDto);
    }

    /**
     * 获取待我审批 数量
     *
     * @param userId 用户id
     */
    @Override
    public Long getWaitMeApproveCount(Long userId) {
        return approveProcedureMapper.getWaitMeApproveCount(userId);
    }


}
