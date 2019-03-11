package com.qf58.ace.approve.server.service;

import com.qf58.ace.approve.api.service.ApproveFileService;
import com.qf58.ace.approve.dto.ApproveFileDto;
import com.qf58.ace.approve.dto.ApproveFileSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.ApproveFile;
import com.qf58.ace.approve.server.dao.ApproveFileDao;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/14 18:39 Time: 14:15
 */
@Service("approveFileService")
public class ApproveFileServiceImpl implements ApproveFileService {

    @Resource
    private ApproveFileDao approveFileDao;

    @Override
    public DtoResult<ApproveFileDto> getApproveFile(Long id) {
        DtoResult result = DtoResult.getInstance();
        ApproveFileDto approveFileDto = new ApproveFileDto();
        ApproveFile approveFile = approveFileDao.selectByPrimaryKey(id);
        BeanUtils.copyProperties(approveFile, approveFileDto);
        result.setDto(approveFileDto);
        return result;
    }

    @Override
    public Long addApproveFile(ApproveFileDto approveFileDto) {
        return approveFileDao.insertSelective(approveFileDto);
    }

    @Override
    public CollectionResult<ApproveFileDto> getApproveFileList(ApproveFileSelectDto approveFileSelectDto) {
        CollectionResult result = CollectionResult.getInstance();
        ListDto<ApproveFileDto> approveFileDtoListDto = approveFileDao.getApproveFileList(approveFileSelectDto);
        if (approveFileDtoListDto!=null){
            result.setCollection(approveFileDtoListDto.getList());
        }
        return result;
    }

}
