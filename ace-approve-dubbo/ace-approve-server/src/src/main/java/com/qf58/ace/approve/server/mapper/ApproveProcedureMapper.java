package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.dto.ApproveProcedureDto;
import com.qf58.ace.approve.dto.ApproveProcedureSelectDto;
import com.qf58.ace.approve.dto.ApproveSelectDto;
import com.qf58.ace.approve.entity.ApproveProcedure;
import com.qf58.ace.approve.mybatis.SqlMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/11/15 16:37 Time: 14:15
 */
@SqlMapper
public interface ApproveProcedureMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ApproveProcedure record);

    int insertSelective(ApproveProcedure record);

    ApproveProcedure selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(ApproveProcedureDto record);

    int updateByPrimaryKey(ApproveProcedure record);

    Long getWaitMeApproveCount(@Param(value = "userId") Long userId);

    List<ApproveProcedure> getApproveProcedureList(ApproveProcedureSelectDto approveProcedureSelectDto);

    List<ApproveProcedure> getApproveProcedureListForDetail(ApproveProcedureSelectDto approveProcedureSelectDto);

    List<Long> getWaitMeApproveList(ApproveSelectDto approveSelectDto);

    List<Long> getMyApprovedList(ApproveSelectDto approveSelectDto);

    int insertApproveProcedureList(List<ApproveProcedure> record);
}
