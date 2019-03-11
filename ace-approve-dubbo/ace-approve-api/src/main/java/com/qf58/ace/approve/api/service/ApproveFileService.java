package com.qf58.ace.approve.api.service;

import com.qf58.ace.approve.dto.ApproveFileDto;
import com.qf58.client.support.CollectionResult;
import com.qf58.ace.approve.dto.ApproveFileSelectDto;
import com.qf58.ace.approve.entity.ApproveFile;
import com.qf58.client.support.DtoResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/14 18:37 Time: 14:15
 */
public interface ApproveFileService {

    DtoResult<ApproveFileDto> getApproveFile(Long id);

    Long addApproveFile(ApproveFileDto approveFileDto);

    CollectionResult<ApproveFileDto> getApproveFileList(ApproveFileSelectDto approveFileSelectDto);

}
