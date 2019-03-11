package com.qf58.ace.finance.server.mapper;

import com.qf58.ace.approve.mybatis.SqlMapper;
import com.qf58.ace.finance.dto.OrderBillsDto;
import com.qf58.ace.finance.entity.OrderBills;
import com.qf58.ace.finance.entity.OrderBillsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SqlMapper
public interface OrderBillsMapper {
    long countByExample(OrderBillsExample example);

    int deleteByExample(OrderBillsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderBills record);

    int insertSelective(OrderBills record);

    List<OrderBills> selectByExample(OrderBillsExample example);
    List<OrderBills> selectByCondition(OrderBillsDto orderBillsDto);

    OrderBills selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderBills record, @Param("example") OrderBillsExample example);

    int updateByExample(@Param("record") OrderBills record, @Param("example") OrderBillsExample example);

    int updateByPrimaryKeySelective(OrderBills record);

    int updateByPrimaryKey(OrderBills record);
}