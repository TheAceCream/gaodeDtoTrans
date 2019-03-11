package com.qf58.ace.approve.server.dao;

import com.qf58.ace.approve.dto.ApproveDto;
import com.qf58.ace.approve.dto.ApproveSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.Approve;


/**
 * Created with IntelliJ IDEA. Description:
 *
 * 审批Dao
 *
 * User: weicaijia Date: 2018/11/17 11:21 Time: 14:15
 */
public interface ApproveDao {

    /**
     * 添加审批
     */
    Approve addApprove(ApproveDto approveDto);

    /**
     * 得到审批详情
     */
    ApproveDto getApproval(Long approvalId);

    /**
     * 我发起的审批列表
     *
     * @param approveSelectDto 审批查询DTO
     * @return 审批列表
     */
    ListDto<ApproveDto> getMyLaunchApprovalList(ApproveSelectDto approveSelectDto);

    /**
     * 获取待我审批列表
     *
     * @param approveSelectDto 审批查询DTO
     * @return 审批列表
     */
    ListDto<ApproveDto> getWaitMeApprove(ApproveSelectDto approveSelectDto);

    /**
     * 我已审批列表
     *
     * @param approveSelectDto 审批查询DTO
     * @return 审批列表
     */
    ListDto<ApproveDto> getMyApprovedList(ApproveSelectDto approveSelectDto);

    /**
     * 审批：通用查询
     *
     * @param approveSelectDto 审批查询Dto
     * @return 审批列表
     */
    ListDto<ApproveDto> getApproveWithoutRole(ApproveSelectDto approveSelectDto);

    /**
     * 我发起的审批中的数量
     *
     * @param userId 用户id
     * @return 数量
     */
    Long getMyLaunchApprovingCount(Long userId);

    /**
     * 我已审批的 审批中的数量
     *
     * @return 数量
     */
    Long getMyApprovedCount(Long userId);


    Long updateApprove(ApproveDto approveDto);
}
