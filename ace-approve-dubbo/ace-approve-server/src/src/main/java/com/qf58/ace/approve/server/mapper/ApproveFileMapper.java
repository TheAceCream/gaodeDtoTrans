package com.qf58.ace.approve.server.mapper;

import com.qf58.ace.approve.dto.ApproveFileSelectDto;
import com.qf58.ace.approve.entity.ApproveFile;
import com.qf58.ace.approve.mybatis.SqlMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/11 11:12 Time: 14:15
 */
@SqlMapper
public interface ApproveFileMapper {

    ApproveFile selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    Long insertSelective(ApproveFile record);

    List<ApproveFile> getApproveFileList(ApproveFileSelectDto approveFileSelectDto);

}
