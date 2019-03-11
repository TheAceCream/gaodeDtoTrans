package com.qf58.ace.approve.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/11 10:58 Time: 14:15
 */
@Getter
@Setter
@ToString
public class ApproveFile {

    /**
     * id
     */
    private Long id;

    /**
     * 审批id
     */
    private Long approveId;

    /**
     * 审批过程id
     */
    private Long approveProcedureId;

    /**
     * 唯一键
     */
    private String fileKey;

    /**
     * 文件类型(1.图片 2.文件)
     */
    private Byte fileType;

    /**
     * 图片url
     */
    private String picUrl;

    /**
     * 原始名称
     */
    private String originName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件上传用户名
     */
    private String userName;

}
