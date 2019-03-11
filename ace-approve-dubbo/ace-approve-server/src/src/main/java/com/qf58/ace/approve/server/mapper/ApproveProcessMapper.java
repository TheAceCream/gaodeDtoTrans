package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.entity.ApproveProcess;
import com.qf58.ace.approve.mybatis.SqlMapper;

@SqlMapper
public interface ApproveProcessMapper {

    int deleteApproveProcessById(Long id);

    int addApproveProcessList(ApproveProcess... record);

    int addApproveProcess(ApproveProcess record);

    ApproveProcess getApproveProcessById(Long id);

    int updateApproveProcessById(ApproveProcess record);
}