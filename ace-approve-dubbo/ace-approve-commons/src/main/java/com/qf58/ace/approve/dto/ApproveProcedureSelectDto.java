package com.qf58.ace.approve.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 审批过程查询DTO
 * User: weicaijia
 * Date: 2018/11/21 14:36
 * Time: 14:15
 */
@Data
public class ApproveProcedureSelectDto implements Serializable {

    private static final long serialVersionUID = 4123585277894540982L;

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
     * 审批人姓名
     */
    private String approver;

    /**
     * 审批过程状态
     * 1发起审批 2已同意 3已拒绝 4审批中 5已撤销
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
     * 1.会签 2.或签
     */
    private Byte lot;

    /**
     * 审批过程类型
     * 1指定一级 2连续多级 3指定员工 4指定角色 5发起人自己
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

}
