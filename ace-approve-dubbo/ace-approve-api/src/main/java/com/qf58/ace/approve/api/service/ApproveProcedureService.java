package com.qf58.ace.approve.api.service;

import com.qf58.ace.approve.dto.ApproveDto;
import com.qf58.ace.approve.dto.ApproveProcedureDto;
import com.qf58.ace.approve.dto.ApproveProcedureSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.OpResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 15:45
 * Time: 14:15
 */
public interface ApproveProcedureService {

    /**
     * 创建审批过程
     * @param approveProcedureDto 审批过程DTO
     * @return
     */
    OpResult createApproveProcedure(ApproveProcedureDto approveProcedureDto);

    /**
     * 获取审批过程列表
     * @param approveProcedureSelectDto 审批过程查询DTO
     * @return
     */
    CollectionResult<ApproveProcedureDto> getApproveProcedureListWithoutInvalid(ApproveProcedureSelectDto approveProcedureSelectDto);

}
