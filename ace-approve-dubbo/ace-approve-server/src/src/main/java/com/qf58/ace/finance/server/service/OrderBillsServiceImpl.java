package com.qf58.ace.finance.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qf58.ace.finance.api.service.OrderBillsService;
import com.qf58.ace.finance.dto.OrderBillsDto;
import com.qf58.ace.finance.entity.OrderBills;
import com.qf58.ace.finance.entity.OrderBillsExample;
import com.qf58.ace.finance.server.mapper.OrderBillsMapper;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.OpResult;
import com.qf58.client.util.Results;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WangZhan
 * @Description:
 * @Date Created in 12:48 2018/11/23.
 */
@Service("orderBillsService")
public class OrderBillsServiceImpl implements OrderBillsService {

    private static final Logger logger = LoggerFactory.getLogger(OrderBillsServiceImpl.class);

    @Autowired
    private OrderBillsMapper orderBillsMapper;

    @Override
    public CollectionResult<OrderBills> getOrderBillsList(OrderBillsDto orderBillsDto) {
        CollectionResult collectionResult = Results.newSucceedCollectionResult();
        List<OrderBills> orderBillsList = new ArrayList<>();
        long total = 0;
        //判断是否有满足权限的部门id
        if (CollectionUtils.isNotEmpty(orderBillsDto.getPurchaseDepartmentIdList())) {
            //判断是否分页，当导出时这两个参数为null，不需要分页
            if (orderBillsDto.getPageNo() != null && orderBillsDto.getPageSize() != null && orderBillsDto.getPageNo() != 0 && orderBillsDto.getPageSize() != 0) {
                Page<OrderBills> orderBillsPage = PageHelper.startPage(orderBillsDto.getPageNo(), orderBillsDto.getPageSize()).doSelectPage(() -> orderBillsMapper.selectByCondition(orderBillsDto));
                orderBillsList = orderBillsPage.getResult();
                total = orderBillsPage.getTotal();
            } else {
                orderBillsList = orderBillsMapper.selectByCondition(orderBillsDto);
                total = orderBillsList.size();
            }
        }
        collectionResult.setCollection(orderBillsList);
        collectionResult.setTotal(total);
        return collectionResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpResult addOrderBills(OrderBills orderBills) {
        OpResult opResult = Results.newSucceedOperationResult();
        opResult = orderBillsMapper.insertSelective(orderBills) == 1 ? opResult : Results.newFailedOperationResult();
        return opResult;
    }
}
