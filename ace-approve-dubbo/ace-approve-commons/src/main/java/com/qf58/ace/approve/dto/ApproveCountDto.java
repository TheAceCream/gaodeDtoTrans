package com.qf58.ace.approve.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 审批数量 包括：
 * 待我审批的审批数量 + 由 我发起的 状态 为审批中 的数量
 * User: weicaijia
 * Date: 2018/11/20 14:18
 * Time: 14:15
 */
@Data
public class ApproveCountDto implements Serializable {

    private static final long serialVersionUID = -3079379694751669133L;

    /**
     * 待我审批数量
     */
    private Long waitMeApprove;

    /**
     * 审批中数量
     */
    private Long approving;

}
