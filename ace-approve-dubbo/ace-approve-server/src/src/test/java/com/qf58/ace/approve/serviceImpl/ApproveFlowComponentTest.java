package com.qf58.ace.approve.serviceImpl;

import com.qf58.ace.SpringBase;
import com.qf58.ace.approve.dto.ApproveProcedureCreateDto;
import com.qf58.ace.approve.entity.ApproveProcedure;
import com.qf58.ace.approve.server.component.ApproveFlowComponent;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月21日
 * @modifytime:
 */
//@Ignore
public class ApproveFlowComponentTest extends SpringBase {
    @Resource
    private ApproveFlowComponent approveFlowComponent;

    @Test
    public void createApproveProcedureTest(){
        ApproveProcedureCreateDto dto = new ApproveProcedureCreateDto();
        dto.setApproveFlowId(2567518107803008L);
        dto.setApproveId(3306L);
        dto.setCompanyId(1514236500132480L);
        dto.setDeptId(41L);
        dto.setOriginator(2566172320702144L);
        dto.setIsTest((byte)2);
        List<ApproveProcedure> result = approveFlowComponent.createApproveProcedure(dto);
        if(result!=null&&result.size()>0) {
            for (ApproveProcedure e : result) {
                System.out.println(e);
            }
        }
    }
}
