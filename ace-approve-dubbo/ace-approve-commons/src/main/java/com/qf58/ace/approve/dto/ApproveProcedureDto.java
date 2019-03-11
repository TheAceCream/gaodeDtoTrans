package com.qf58.ace.approve.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/15 15:28
 * Time: 14:15
 */
@Data
public class ApproveProcedureDto implements Serializable {

    private static final long serialVersionUID = 8009246499065445512L;

    /**
     * 审批过程id
     */
    private Long id;

    /**
     * 审批id
     */
    private Long approveId;

    /**
     * 审批人id
     */
    private Long approverId;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批人职位
     */
    private String approverDuty;

    /**
     * 审批过程状态
     * 1.发起审批
     * 2.已同意
     * 3.已拒绝
     * 4.审批中
     * 5.已撤销
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 组
     */
    private Byte groups;

    /**
     * 1.会签
     * 2.或签
     */
    private Byte lot;

    /**
     * 审批过程类型
     * 1.指定一级
     * 2.连续多级
     * 3.指定员工
     * 4.指定角色
     * 5.发起人自己
     */
    private Byte type;

    /**
     * 显示级别
     */
    private Byte watchLevel;

    /**
     * 是否测试
     */
    private Byte isTest;

    /**
     * 审批发起人姓名
     */
    private String initiator;

    /**
     * 审批标题
     */
    private String title;

    /**
     * 备注
     */
    private String remark;
}
