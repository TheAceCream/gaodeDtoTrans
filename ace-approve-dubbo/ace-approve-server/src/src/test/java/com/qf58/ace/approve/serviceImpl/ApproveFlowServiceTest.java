package com.qf58.ace.approve.serviceImpl;

import com.qf58.ace.SpringBase;
import com.qf58.ace.approve.api.service.ApproveFlowService;
import com.qf58.ace.approve.dto.ApproveFlowExternalGetDto;
import com.qf58.ace.approve.dto.ApproveFlowListDto;
import com.qf58.ace.approve.dto.ApproveFlowListSelectDto;
import com.qf58.ace.approve.dto.ApproveLaunchListDto;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月20日
 * @modifytime:
 */
@Ignore
public class ApproveFlowServiceTest extends SpringBase {

    @Resource
    private ApproveFlowService approveFlowService;

    @Test
    public void selectApproveListTest(){
        CollectionResult<ApproveLaunchListDto> result = approveFlowService.selectApproveList(2536714076528128L,1514236500132480L);
        System.out.println(result.getCollection());
    }

    @Test
    public void selectApproveFlowList(){
        ApproveFlowListSelectDto dto = new ApproveFlowListSelectDto();
        dto.setCompanyId(1514236500132480L);
        dto.setPageSize(10);
        dto.setPageNo(1);
        CollectionResult<ApproveFlowListDto> result = approveFlowService.selectApproveFlowList(dto);
        System.out.println(result.getCollection());
    }

    @Test
    public void getExternalApproveFlowById(){
        DtoResult<ApproveFlowExternalGetDto> result = approveFlowService.getExternalApproveFlowById(2528917422726400L);
        System.out.println(result.getDto());
    }
}
