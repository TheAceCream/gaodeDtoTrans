package com.qf58.ace.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @Date : 2018-11-23
*/
public class OrderBillsDto implements Serializable{
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
     * 采购部门id
     */
    private List<Long> purchaseDepartmentIdList;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 提交时间始
     */
    private Date submitTimeStart;
    /**
     * 提交时间末
     */
    private Date submitTimeEnd;

    /**
     * 审批通过时间始
     */
    private Date approveAccessTimeStart;
    /**
     * 审批通过时间末
     */
    private Date approveAccessTimeEnd;

    /**
     * 发货时间始
     */
    private Date deliverTimeStart;
    /**
     * 发货时间末
     */
    private Date deliverTimeEnd;

    /**
     * 收货时间始
     */
    private Date receiveTimeStart;
    /**
     * 收货时间末
     */
    private Date receiveTimeEnd;

    /**
     * 支付方式 目前默认0 是月结支付
     */
    private Byte payment;

    /**
     * 支付状态
     */
    private Byte payStatus;

    /**
     * 是否测试 1是 0否
     */
    private Byte isTest;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 页码
     */
    private Integer pageNo;

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

    public List<Long> getPurchaseDepartmentIdList() {
        return purchaseDepartmentIdList;
    }

    public void setPurchaseDepartmentIdList(List<Long> purchaseDepartmentIdList) {
        this.purchaseDepartmentIdList = purchaseDepartmentIdList;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Date getSubmitTimeStart() {
        return submitTimeStart;
    }

    public void setSubmitTimeStart(Date submitTimeStart) {
        this.submitTimeStart = submitTimeStart;
    }

    public Date getSubmitTimeEnd() {
        return submitTimeEnd;
    }

    public void setSubmitTimeEnd(Date submitTimeEnd) {
        this.submitTimeEnd = submitTimeEnd;
    }

    public Date getApproveAccessTimeStart() {
        return approveAccessTimeStart;
    }

    public void setApproveAccessTimeStart(Date approveAccessTimeStart) {
        this.approveAccessTimeStart = approveAccessTimeStart;
    }

    public Date getApproveAccessTimeEnd() {
        return approveAccessTimeEnd;
    }

    public void setApproveAccessTimeEnd(Date approveAccessTimeEnd) {
        this.approveAccessTimeEnd = approveAccessTimeEnd;
    }

    public Date getDeliverTimeStart() {
        return deliverTimeStart;
    }

    public void setDeliverTimeStart(Date deliverTimeStart) {
        this.deliverTimeStart = deliverTimeStart;
    }

    public Date getDeliverTimeEnd() {
        return deliverTimeEnd;
    }

    public void setDeliverTimeEnd(Date deliverTimeEnd) {
        this.deliverTimeEnd = deliverTimeEnd;
    }

    public Date getReceiveTimeStart() {
        return receiveTimeStart;
    }

    public void setReceiveTimeStart(Date receiveTimeStart) {
        this.receiveTimeStart = receiveTimeStart;
    }

    public Date getReceiveTimeEnd() {
        return receiveTimeEnd;
    }

    public void setReceiveTimeEnd(Date receiveTimeEnd) {
        this.receiveTimeEnd = receiveTimeEnd;
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

    public Byte getIsTest() {
        return isTest;
    }

    public void setIsTest(Byte isTest) {
        this.isTest = isTest;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}