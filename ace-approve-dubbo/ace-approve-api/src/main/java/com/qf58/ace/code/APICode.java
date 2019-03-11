package com.qf58.ace.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 项目返回码枚举类
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月17日
 * @modifytime:
 */
@Getter
@AllArgsConstructor
public enum APICode {
    //基础返回码，后续每个模块负责人补充
    SUCCESS(0,"成功"),
    FAILD(1,"失败"),
    RUNTIME_EXCEPTION(2,"系统异常"),
    UPLOAD_EXCEPTION(3,"上传文件失败"),
    PARAM_EMPTY(4,"参数异常"),

    //审批模块400000~499999
    APPROVAL_PARAM_EMPTY(400010,"审批参数错误"),
    APPROVAL_LIST_EXCEPTION(400012,"审批列表查询错误"),
    AGREE_EXCEPTION(400013,"同意审批错误"),
    REFUSE_EXCEPTION(400014,"拒绝审批错误"),
    CANCEL_EXCEPTION(400015,"撤销审批错误"),
    HAS_CANCEL_EXCEPTION(400016,"审批已失效"),
    GET_PARAM_EXCEPTION(400017,"参数获取失败"),
    APPROVEFLOW_EXCEPTION(400018,"审批流获取失败"),
    APPROVE_DETAIL_EXCEPTION(400019,"审批详情获取失败"),
    APPROVE_PROCEDURE_EXCEPTION(400020,"审批过程获取失败"),
    APPROVE_ORDER_EXCEPTION(400021,"订单信息获取失败"),

    //审批流600000~699999
    APPROVEFLOW_VISIBILITY_DEPT_EMPTY(600010,"可见范围部门参数错误"),
    APPROVEFLOW_VISIBILITY_ROLE_EMPTY(600011,"可见范围角色参数错误"),
    APPROVEFLOW_VISIBILITY_USER_EMPTY(600012,"可见范围员工参数错误"),
    APPROVEFLOW_PROCESS_EMPTY(600013,"审批过程参数错误"),
    APPROVEFLOW_PARAMETER_ERROR(600014,"操作参数错误"),
    APPROVEFLOW_VISIBILITY_CONTROL_SIGN_PARAMETER_ERROR(600015,"审批流可见度控制参数错误"),
    APPROVEFLOW_ROLE_ERROR(600017,"您无此权限操作该审批流"),
    QIFU_PASSPORT_CLIENT_API_ERR(600016,"ACE员工权限服务接口调用失败"),
    APPROVEFLOW_VISIBILITY_DEPT_ERR(600018,"用户部门与审批可见部门无交集"),
    UNKNOW_PEOPLE(600019,"系统查无此人");
    //预算800000~899999


    //API 返回码
    private int code;
    //API 返回码描述
    private String desc;


}
