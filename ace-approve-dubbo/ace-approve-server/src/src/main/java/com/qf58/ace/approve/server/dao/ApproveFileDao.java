package com.qf58.ace.approve.server.dao;

import com.qf58.ace.approve.dto.ApproveFileDto;
import com.qf58.ace.approve.dto.ApproveFileSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.ApproveFile;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/14 14:44 Time: 14:15
 */
public interface ApproveFileDao {

    ApproveFile selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    Long insertSelective(ApproveFileDto approveFileDto);

    ListDto<ApproveFileDto> getApproveFileList(ApproveFileSelectDto approveFileSelectDto);

}
