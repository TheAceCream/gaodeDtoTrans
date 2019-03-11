package com.qf58.ace.approve.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/14 18:09 Time: 14:15
 */
@Getter
@Setter
@ToString
public class ApproveFileSelectDto implements Serializable {

    private static final long serialVersionUID = -7071346213956955273L;

    /**
     * 审批id
     */
    private Long approvalId;

    /**
     * 审批过程id
     */
    private Long approveProcedureId;

    /**
     * 唯一键
     */
    private String fileKey;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 文件类型(1.图片 2.文件)
     */
    private Byte fileType;

}
