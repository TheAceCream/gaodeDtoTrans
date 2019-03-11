package com.qf58.ace.approve.server.dao;

import com.qf58.ace.approve.dto.ApproveProcedureDto;
import com.qf58.ace.approve.dto.ApproveProcedureSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.ApproveProcedure;

import java.util.List;

/**
 * Created with IntelliJ IDEA. Description:
 *
 * 审批过程Dao
 *
 * User: weicaijia Date: 2018/11/17 11:23 Time: 14:15
 */
public interface ApproveProcedureDao {

    /**
     * 添加审批过程
     */
    Long addApproveProcedure(ApproveProcedureDto approveProcedureDto);

    /**
     * 批量添加审批过程
     */
    int addApproveProcedureList(List<ApproveProcedure> list);

    /**
     * 获取所有审批过程列表
     */
    ListDto<ApproveProcedureDto> getApproveProcedureList(ApproveProcedureSelectDto approveProcedureSelectDto);

    /**
     * 获取审批过程列表 不带无效 (给前端)
     */
    ListDto<ApproveProcedureDto> getApproveProcedureListWithoutInvalid(ApproveProcedureSelectDto approveProcedureSelectDto);

    /**
     * 修改审批过程
     */
    Long updateApproveProcedure(ApproveProcedureDto approveProcedureDto);

    /**
     * 待我审批数量
     *
     * @param userId 用户id
     * @return 数量
     */
    Long getWaitMeApproveCount(Long userId);

}
