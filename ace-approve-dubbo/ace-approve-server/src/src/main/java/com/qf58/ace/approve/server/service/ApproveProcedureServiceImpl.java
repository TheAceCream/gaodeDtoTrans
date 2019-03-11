package com.qf58.ace.approve.server.service;

import com.qf58.ace.approve.api.service.ApproveProcedureService;
import com.qf58.ace.approve.dto.ApproveProcedureDto;
import com.qf58.ace.approve.dto.ApproveProcedureSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.server.dao.ApproveProcedureDao;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.OpResult;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/11/15 16:30 Time: 14:15
 *
 * 审批过程服务
 */
@Service("approveProcedureService")
public class ApproveProcedureServiceImpl implements ApproveProcedureService {

    @Resource
    private ApproveProcedureDao approveProcedureDao;

    /**
     * 创建审批过程
     */
    @Override
    public OpResult createApproveProcedure(ApproveProcedureDto approveProcedureDto) {
        Long id;
        id = approveProcedureDao.addApproveProcedure(approveProcedureDto);
        if (id != null) {
            return OpResult.ok(id);
        }
        return OpResult.defaultFail();
    }

    @Override
    public CollectionResult<ApproveProcedureDto> getApproveProcedureListWithoutInvalid(ApproveProcedureSelectDto approveProcedureSelectDto) {
        CollectionResult result = CollectionResult.getInstance();
        ListDto<ApproveProcedureDto> approveProcedureDtoList = approveProcedureDao.getApproveProcedureListWithoutInvalid(approveProcedureSelectDto);
        if (approveProcedureDtoList != null) {
            result.setCollection(approveProcedureDtoList.getList());
        }
        return result;
    }


}
