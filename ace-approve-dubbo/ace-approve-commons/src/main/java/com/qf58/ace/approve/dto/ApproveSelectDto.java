package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/19 17:58
 * Time: 14:15
 */
@Getter
@Setter
@ToString
public class ApproveSelectDto implements Serializable {

    private static final long serialVersionUID = -1978436701168031533L;

    /**
     * 审批状态 0.全部 1.审批中 5.审批完成 4.已撤销
     */
    private Byte status;

    /**
     * 审批id
     */
    private Long approvalId;

    /**
     * 审批标题
     */
    private String title;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 审批发起人id
     */
    private Long sponsorId;

    /**
     * 审批发起人
     */
    private String sponsorDesc;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 类目
     */
    private Long cateId;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 页容量
     */
    private Integer pageSize;

    /**
     * 是否测试数据（1：测试；2：非测试）
     */
    private Byte isTest;

}
