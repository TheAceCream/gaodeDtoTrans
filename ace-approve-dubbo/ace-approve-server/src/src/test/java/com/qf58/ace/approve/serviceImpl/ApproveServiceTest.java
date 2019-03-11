package com.qf58.ace.approve.serviceImpl;

import com.google.gson.Gson;

import com.qf58.ace.SpringBase;
import com.qf58.ace.approve.api.service.ApproveService;
import com.qf58.ace.approve.dto.ApproveDto;
import com.qf58.ace.approve.dto.ApproveSelectDto;
import com.qf58.acepassport.service.AceAuthService;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.OpResult;

import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rabbitmq58lib.utils.TypeConverse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/16 15:09
 * Time: 14:15
 */
public class ApproveServiceTest extends SpringBase {

    @Resource
    private ApproveService approveService;

    @Resource
    private AceAuthService aceAuthService;

    /**
     * 测添加审批
     * 成功跑通
     */
    @Test
//    @Ignore
    public void addApprove() {
        ApproveDto approveDto = new ApproveDto();
        approveDto.setId(2550395244794444L);
        approveDto.setAmount(BigDecimal.valueOf(23322.2));
        approveDto.setApproveFlowId(2674963999918336L);
        approveDto.setSponsorId(2566172320702144L);
        approveDto.setSponsorDesc("测试用户7");
        approveDto.setDepartmentId(17L);
        approveDto.setDepartmentDesc("大客户三部");
        approveDto.setTotalBudget(BigDecimal.valueOf(100000.00));
        approveDto.setBudget(BigDecimal.valueOf(65387.10));
        approveDto.setCompanyId(2570262603470464L);
        approveDto.setCateId(1910797338547712L);
        approveDto.setType((byte) 1);
        approveDto.setIsTest((byte) 2);
        approveService.createApprove(approveDto);
    }

    /**
     * 审批处理
     */
    @Test
//    @Ignore
    public void testAgree(){
        List<Long> ids = new ArrayList<>();
        ids.add(2550395244794444L);
        approveService.agreeApproval(ids,null,null,"a",2566172320702144L);
    }

    /**
     * 测审批详情
     */
    @Test
    @Ignore
    public void getApprove() {
        approveService.approvalDetail(1L);
    }

    /**
     * 测审批列表
     */
    @Test
//    @Ignore
    public void getApproveList() {
        ApproveSelectDto approveSelectDto = new ApproveSelectDto();
        approveSelectDto.setStatus((byte) 4);
//        approveSelectDto.setTitle("crwedc sf34324e ewf''");
//        approveSelectDto.setSponsorId(2556245168119489L);
        approveSelectDto.setUserId(2556245168119489L);
        approveSelectDto.setPageNo(1);
        approveSelectDto.setPageSize(10);
//        approveSelectDto.setApprovalId(2558819777240768L);
//        approveSelectDto.setStatus((byte) 1);

        //我发起的
//        CollectionResult<ApproveDto> approveDto = approveService.getMyLaunchApprovalList(approveSelectDto);
//        待我审批的
//        CollectionResult<ApproveDto> approveDto2 =approveService.getWaitMeApprove(approveSelectDto);
        //我已审批的
        CollectionResult<ApproveDto> approveDto3 = approveService.getMyApprovedList(approveSelectDto);
//        approveDto.getCollection();
//        approveDto.getTotal();

        approveDto3.getCollection();
        approveDto3.getTotal();

//        approveDto3.getCollection();
//        approveDto3.getTotal();


    }

    /**
     * 数量
     * 测通
     */
    @Test
    public void testCount(){
        approveService.getAboutApproveCount(3,123L);
    }



//    @Test
//    public void mail(){
//        //调用发送邮件
//        Map<String,String> tokenMap = new HashMap<>();
//        // 审批id
//        tokenMap.put("approvalId","2569921320230593");
//        // 审批人id
//        tokenMap.put("approverId","2536010217381376");
//        //创建token
//        OpResult opResult = aceAuthService.createTempKey(tokenMap);
//        if (opResult.isSuccess()){
//            String tokenStr = opResult.getExtra();
//            System.out.println(tokenStr);
//            //校验token
//            OpResult opResult2 = aceAuthService.validateTempKey(tokenStr);
//            if (opResult2.isSuccess()) {
//                Map<String, String> map = new Gson().fromJson(opResult2.getExtraObj().toString(),Map.class);
//                approveService.approvalDetail(Long.parseLong(map.get("approvalId")));
//            }
//        }
//    }




}
