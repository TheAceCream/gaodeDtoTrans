package com.qf58.ace.approve.api.service;

import com.qf58.ace.approve.dto.ApproveCountDto;
import com.qf58.ace.approve.dto.ApproveDto;
import com.qf58.ace.approve.dto.ApproveSelectDto;
import com.qf58.ace.approve.dto.FileDto;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;
import com.qf58.client.support.OpResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/11/15 15:44 Time: 14:15
 */
public interface ApproveService {

    /**
     * 提交审批
     */
    OpResult createApprove(ApproveDto approveDto);

    /**
     * 获取审批详情
     */
    DtoResult<ApproveDto> approvalDetail(Long approvalId);

    /**
     * 获取我发起的审批列表
     *
     * @param approveSelectDto 查询Dto
     */
    CollectionResult<ApproveDto> getMyLaunchApprovalList(ApproveSelectDto approveSelectDto);

    /**
     * 获取待我审批列表
     *
     * @param approveSelectDto 查询Dto
     * @return 审批列表
     */
    CollectionResult<ApproveDto> getWaitMeApprove(ApproveSelectDto approveSelectDto);

    /**
     * 获取我已审批列表
     *
     * @param approveSelectDto 查询Dto
     * @return 审批列表
     */
    CollectionResult<ApproveDto> getMyApprovedList(ApproveSelectDto approveSelectDto);

    /**
     * 通过查询条件获取审批列表
     *
     * @param approveSelectDto 查询Dto
     * @return 审批列表
     */
    CollectionResult<ApproveDto> getApproveWithoutRole(ApproveSelectDto approveSelectDto);

    /**
     * 获取审批相关的数量
     *
     * @param myApprovalStatus 审批查询类型：1：我发起的，2：待我审批的，3：我已审批的
     * @param userId           用户id
     * @return 数量
     */
    DtoResult<ApproveCountDto> getAboutApproveCount(Integer myApprovalStatus, Long userId);

    /**
     * 批量同意审批
     *
     * @param approveIdList 审批id列表
     * @param userId        审批人
     * @return 是否成功
     */
    OpResult agreeApproval(List<Long> approveIdList, List<FileDto> annexList, List<FileDto> picList, String remark, Long userId);

    /**
     * 批量拒绝审批
     *
     * @param approveIdList 审批id列表
     * @param userId        审批人
     * @return 是否成功
     */
    OpResult refuseApproval(List<Long> approveIdList, List<FileDto> annexList, List<FileDto> picList, String remark, Long userId);

    /**
     * 撤销审批
     *
     * @param approvalId 审批id
     * @param userId     用户id
     * @return 是否成功
     */
    OpResult cancelApproval(Long approvalId, Long userId);
}

