package com.qf58.ace.finance.serviceImpl;

import com.qf58.ace.SpringBase;
import com.qf58.ace.finance.api.service.OrderBillsService;
import com.qf58.ace.finance.dto.OrderBillsDto;
import com.qf58.ace.finance.entity.OrderBills;
import com.qf58.client.support.CollectionResult;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WangZhan
 * @Description:
 * @Date Created in 16:23 2018/11/24.
 */
public class OrderBillsServiceImplTest extends SpringBase {
    @Resource
    private OrderBillsService orderBillsService;

    @Test
    public void addOrderBills() {
        for (int i = 0;i<10;i++){
            OrderBills orderBills = new OrderBills();
            orderBills.setOrderId(1215L+i);
            orderBills.setApproveId(123L+i);
            orderBills.setApproveName("测试审核"+i+2);
            orderBills.setPurchaseDepartment("测试部门"+i+3);
            orderBills.setPurchaseDepartmentId(i+3L);
            orderBills.setSubmitter("wangzhan"+i+2);
            orderBills.setSubmitTime(new Date());
            orderBills.setOrderAmount(124221L);
            orderBills.setApproveAccessTime(new Date());
            orderBills.setDeliverTime(new Date());
            orderBills.setReceiveTime(new Date());
            orderBills.setPayment((byte) 4);
            orderBills.setPayStatus((byte) 1);
            orderBills.setGmtTime(new Date());
            orderBills.setModifyTime(new Date());
            System.out.println(orderBillsService.addOrderBills(orderBills));
        }
    }
    @Test
    public void getOrderBillsList(){
        OrderBillsDto orderBillsDto = new OrderBillsDto();
        //初始化，只有页码和有权限的部门
//        orderBillsDto.setPageSize(10);
//        orderBillsDto.setPageNo(1);
        orderBillsDto.setPurchaseDepartmentIdList(Arrays.asList());
//        orderBillsDto.setPurchaseDepartmentIdList(Arrays.asList(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L,11L,12L));
        CollectionResult<OrderBills> result =  orderBillsService.getOrderBillsList(orderBillsDto);
        System.out.println();

    }
}
