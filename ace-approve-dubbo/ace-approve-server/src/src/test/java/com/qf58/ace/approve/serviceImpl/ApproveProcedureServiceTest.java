package com.qf58.ace.approve.serviceImpl;

import com.qf58.ace.SpringBase;
import com.qf58.ace.approve.api.service.ApproveProcedureService;
import com.qf58.ace.approve.dto.ApproveProcedureDto;
import com.qf58.ace.approve.dto.ApproveProcedureSelectDto;
import com.qf58.client.support.CollectionResult;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/17 15:29
 * Time: 14:15
 */
public class ApproveProcedureServiceTest extends SpringBase {

    @Resource
    private ApproveProcedureService approveProcedureService;

    @Test
    @Ignore
    public void addApproveProcedure() {
        ApproveProcedureDto approveProcedureDto = new ApproveProcedureDto();
        approveProcedureDto.setId(11111141241L);
        approveProcedureDto.setStatus((byte) 1);
        approveProcedureDto.setType((byte)1);
        approveProcedureDto.setApproveId(123124125L);
        approveProcedureDto.setApprover("哈哈哈单元测");
        approveProcedureDto.setApproverDuty("额测测测");
        approveProcedureDto.setApproveTime(new Timestamp(System.currentTimeMillis()));
        approveProcedureDto.setGroups((byte) 1);
        approveProcedureDto.setLot((byte) 1);
        approveProcedureDto.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        approveProcedureDto.setWatchLevel((byte) 1);
        approveProcedureService.createApproveProcedure(approveProcedureDto);
    }


    @Test
    public void getApproveProcedures() {
        ApproveProcedureSelectDto approveProcedureSelectDto = new ApproveProcedureSelectDto();
        approveProcedureSelectDto.setApproveId(2605993308412224L);
        CollectionResult<ApproveProcedureDto> result  =  approveProcedureService.getApproveProcedureListWithoutInvalid(approveProcedureSelectDto);
        System.out.println(result.getCollection());

    }


}
