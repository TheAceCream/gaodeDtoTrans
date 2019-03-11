package com.qf58.ace.approve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 创建或
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Getter
@Setter
public class ApproveVisibilityDto implements Serializable {

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 角色id字符串
     */
    private String roleId;

    /**
     * 用户id字符串
     */
    private String userId;

}
