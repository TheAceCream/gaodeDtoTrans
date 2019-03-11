package com.qf58.ace.approve.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 15:27
 * Time: 14:15
 */
@Data
public class ApproveDto implements Serializable {

    private static final long serialVersionUID = 4245605678663991736L;

    /**
     * 审批id
     */
    private Long id;

    /**
     * 审批流id
     */
    private Long approveFlowId;

    /**
     * 审批标题
     */
    private String title;

    /**
     * 申请部门id
     */
    private Long departmentId;

    /**
     * 申请部门
     */
    private String departmentDesc;

    /**
     * 部门总预算
     */
    private BigDecimal totalBudget;

    /**
     * 部门预算
     */
    private BigDecimal budget;

    /**
     * 发起人id
     */
    private Long sponsorId;

    /**
     * 发起人姓名
     */
    private String sponsorDesc;

    /**
     * 审批发起时间
     */
    private Date createTime;

    /**
     * 审批修改时间
     */
    private Date updateTime;

    /**
     * 审批结束时间(通过、拒绝、撤销)
     */
    private Date endTime;

    /**
     * 审批状态
     * 1.审批中
     * 2.审批同意
     * 3.审批拒绝
     * 4.审批撤销
     */
    private Byte status;

    /**
     * 订单总额
     */
    private BigDecimal amount;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 一级类目id
     */
    private Long cateId;

    /**
     * 审批类型
     * 1.电商
     */
    private Byte type;

    /**
     * 是否测试数据
     * 1.测试 2.非测试
     */
    private Byte isTest;

}
