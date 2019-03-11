package com.qf58.ace.approve.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.*;
import lombok.experimental.Accessors;

/**
* @Date : 2018-11-16
*/
@Getter
@Setter
public class ApproveFlowVisibilityRoleDto implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 审批流id
     */
    private Long approveFlowId;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 角色id(0:表示全部)
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}