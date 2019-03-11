package com.qf58.ace.finance.api.service;

import com.qf58.ace.finance.dto.OrderBillsDto;
import com.qf58.ace.finance.entity.OrderBills;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.OpResult;

/**
 * @Author: WangZhan
 * @Description:
 * @Date Created in 12:53 2018/11/23.
 */
public interface OrderBillsService {
    /**
     * 根据条件筛选办公用品账单列表
     * @param orderBillsDto
     * @return
     */
    CollectionResult<OrderBills> getOrderBillsList(OrderBillsDto orderBillsDto);

    /**
     * 插入办公用品账单
     */
    OpResult addOrderBills(OrderBills orderBills);
}
