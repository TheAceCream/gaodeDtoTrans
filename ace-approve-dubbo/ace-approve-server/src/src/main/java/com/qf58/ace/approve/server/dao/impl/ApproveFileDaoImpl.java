package com.qf58.ace.approve.server.dao.impl;

import com.qf58.ace.approve.dto.ApproveFileDto;
import com.qf58.ace.approve.dto.ApproveFileSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.ApproveFile;
import com.qf58.ace.approve.server.dao.ApproveFileDao;
import com.qf58.ace.approve.server.mapper.ApproveFileMapper;
import com.qf58.ace.util.UniqueIDUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/14 14:45 Time: 14:15
 */
@Repository
public class ApproveFileDaoImpl implements ApproveFileDao {

    @Resource
    private ApproveFileMapper approveFileMapper;

    /**
     * 查询
     */
    @Override
    public ApproveFile selectByPrimaryKey(Long id) {
        return approveFileMapper.selectByPrimaryKey(id);
    }


    /**
     * 删
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return approveFileMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加
     */
    @Override
    public Long insertSelective(ApproveFileDto approveFileDto) {
        ApproveFile approveFile = new ApproveFile();
        BeanUtils.copyProperties(approveFileDto, approveFile);
        //添加创建时间
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        approveFile.setCreateTime(initTime);
        approveFile.setId(UniqueIDUtils.getUniqueID());
        return approveFileMapper.insertSelective(approveFile);
    }

    /**
     * 获取审批文件列表
     */
    @Override
    public ListDto<ApproveFileDto> getApproveFileList(ApproveFileSelectDto approveFileSelectDto) {
        List<ApproveFile> approveFileList  = approveFileMapper.getApproveFileList(approveFileSelectDto);
        List<ApproveFileDto> approveFileDtoList  = new ArrayList<>();
        ListDto<ApproveFileDto> result = new ListDto<>();
        if (approveFileList!=null){
            for (ApproveFile approveFile : approveFileList) {
                ApproveFileDto approveFileDto = new ApproveFileDto();
                BeanUtils.copyProperties(approveFile, approveFileDto);
                approveFileDtoList.add(approveFileDto);
            }
            result.setList(approveFileDtoList);
        }
        return result;
    }


}
