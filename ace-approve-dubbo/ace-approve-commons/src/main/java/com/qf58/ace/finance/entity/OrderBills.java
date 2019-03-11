package com.qf58.ace.finance.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* @Date : 2018-11-23
*/

public class OrderBills implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 审批ID
     */
    private Long approveId;

    /**
     * 
     */
    private String approveName;

    /**
     * 采购部门id
     */
    private Long purchaseDepartmentId;

    /**
     * 采购部门
     */
    private String purchaseDepartment;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 订单总额乘以1000
     */
    private Long orderAmount;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 审批通过时间
     */
    private Date approveAccessTime;

    /**
     * 发货时间
     */
    private Date deliverTime;

    /**
     * 收货时间
     */
    private Date receiveTime;

    /**
     * 支付方式 目前默认0 是月结支付
     */
    private Byte payment;

    /**
     * 支付状态
     */
    private Byte payStatus;

    /**
     * 
     */
    private Date gmtTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 是否测试 1是 0否
     */
    private Byte isTest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getApproveId() {
        return approveId;
    }

    public void setApproveId(Long approveId) {
        this.approveId = approveId;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public Long getPurchaseDepartmentId() {
        return purchaseDepartmentId;
    }

    public void setPurchaseDepartmentId(Long purchaseDepartmentId) {
        this.purchaseDepartmentId = purchaseDepartmentId;
    }

    public String getPurchaseDepartment() {
        return purchaseDepartment;
    }

    public void setPurchaseDepartment(String purchaseDepartment) {
        this.purchaseDepartment = purchaseDepartment;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getApproveAccessTime() {
        return approveAccessTime;
    }

    public void setApproveAccessTime(Date approveAccessTime) {
        this.approveAccessTime = approveAccessTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Byte getPayment() {
        return payment;
    }

    public void setPayment(Byte payment) {
        this.payment = payment;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Date getGmtTime() {
        return gmtTime;
    }

    public void setGmtTime(Date gmtTime) {
        this.gmtTime = gmtTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Byte getIsTest() {
        return isTest;
    }

    public void setIsTest(Byte isTest) {
        this.isTest = isTest;
    }
}