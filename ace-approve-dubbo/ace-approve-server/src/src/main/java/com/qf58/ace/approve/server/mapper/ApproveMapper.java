package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.dto.ApproveSelectDto;
import com.qf58.ace.approve.entity.Approve;
import com.qf58.ace.approve.mybatis.SqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 16:37
 * Time: 14:15
 */
@SqlMapper
public interface ApproveMapper {

    int deleteByPrimaryKey(Long id);

    Long insert(Approve record);

    Long insertSelective(Approve record);

    Approve selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(Approve record);

    int updateByPrimaryKey(Approve record);

    List<Approve> getMyLaunchApprovalList(ApproveSelectDto approveSelectDto);

    Long getMyLaunchApprovingCount(@Param(value = "userId") Long userId);

    Long getMyApprovedCount(@Param(value = "userId") Long userId);

    List<Approve> getWaitMeApproveList(@Param(value = "waitMeApproveIdList") List<Long> waitMeApproveIdList);

    List<Approve> getMyApprovedList(@Param(value = "myApprovedList") List<Long> myApprovedList);

    List<Approve> getApproveWithoutRole(ApproveSelectDto approveSelectDto);
}
