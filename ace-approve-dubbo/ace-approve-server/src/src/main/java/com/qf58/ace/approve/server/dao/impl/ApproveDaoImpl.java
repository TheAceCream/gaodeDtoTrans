package com.qf58.ace.approve.server.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf58.ace.approve.dto.ApproveDto;
import com.qf58.ace.approve.dto.ApproveSelectDto;
import com.qf58.ace.approve.dto.ListDto;
import com.qf58.ace.approve.entity.Approve;
import com.qf58.ace.approve.enums.ApproveStatusEnum;
import com.qf58.ace.approve.server.dao.ApproveDao;
import com.qf58.ace.approve.server.mapper.ApproveMapper;
import com.qf58.ace.approve.server.mapper.ApproveProcedureMapper;
import com.qf58.ace.util.BigDecimalUtil;
import com.qf58.acepassport.enums.AceBudgetLimitEnum;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/11/17 11:23 Time: 14:15
 */
@Repository
public class ApproveDaoImpl implements ApproveDao {

    @Resource
    private ApproveMapper approveMapper;

    @Resource
    private ApproveProcedureMapper approveProcedureMapper;

    /**
     * 添加审批
     */
    @Override
    public Approve addApprove(ApproveDto approveDto) {
        Approve approve = new Approve();
        BeanUtils.copyProperties(approveDto, approve);
        //添加创建时间
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        approve.setCreateTime(initTime);
        //状态
        approve.setStatus(ApproveStatusEnum.IN_APPROVE.getCode());
        //审批转实体的时候 部门预算 和 订单总额 要乘以1000 转成Long类型
        if (approveDto.getAmount() != null) {
            approve.setAmount(BigDecimalUtil.longMul(approveDto.getAmount(), 1000));
        }
        //如果预算是无限直接填进去
        if (approveDto.getBudget() != null) {
            approve.setBudget(BigDecimalUtil.longMul(approveDto.getBudget(), 1000));
        }
        approveMapper.insertSelective(approve);
        return approve;
    }

    /**
     * 审批详情
     */
    @Override
    public ApproveDto getApproval(Long approvalId) {
        ApproveDto approveDto = new ApproveDto();
        Approve approve = approveMapper.selectByPrimaryKey(approvalId);
        if (approve!=null) {
            BeanUtils.copyProperties(approve, approveDto);
        }
        //金额都除以1000
        if (approve.getAmount() != null) {
            BigDecimal bigDecimal = new BigDecimal(approve.getAmount());
            approveDto.setAmount(bigDecimal.divide(new BigDecimal(1000)));
        }
        //如果预算是无限直接返给前端
        if (approve.getBudget() != null) {
            BigDecimal bigDecimal = new BigDecimal(approve.getBudget());
            if (approve.getBudget().equals(AceBudgetLimitEnum.INFINITE.getMoney())){
                approveDto.setBudget(new BigDecimal(approve.getBudget()));
            }else {
                approveDto.setBudget(bigDecimal.divide(new BigDecimal(1000)));
            }
        }
        return approveDto;
    }

    /**
     * 获取我发起的审批
     *
     * @param approveSelectDto 审批查询DTO
     * @return 审批列表
     */
    @Override
    public ListDto<ApproveDto> getMyLaunchApprovalList(ApproveSelectDto approveSelectDto) {
        ListDto<ApproveDto> approveListDto = new ListDto<>();
        //使用PageHelper对查询进行分页
        boolean flag = true;
        if (approveSelectDto.getPageSize() == null || approveSelectDto.getPageNo() == null) {
            flag = false;
        } else {
            PageHelper.startPage(approveSelectDto.getPageNo(), approveSelectDto.getPageSize());
        }
        List<Approve> approveList = approveMapper.getMyLaunchApprovalList(approveSelectDto);
        if (flag) {
            PageInfo page = new PageInfo(approveList);
            approveListDto.setTotal(page.getTotal());
        } else {
            approveListDto.setTotal((long) approveList.size());
        }
        approveListDto = approveListToApproveDtoList(approveList,approveListDto);
        return approveListDto;
    }


    /**
     * 获取待我审批列表
     *
     * @param approveSelectDto 审批查询DTO
     * @return 审批列表
     */
    @Override
    public ListDto<ApproveDto> getWaitMeApprove(ApproveSelectDto approveSelectDto) {
        ListDto<ApproveDto> approveListDto = new ListDto<>();
        //首先通过审批关系获取 待我审批id 列表
        //使用PageHelper对查询进行分页
        boolean flag = true;
        if (approveSelectDto.getPageSize() == null || approveSelectDto.getPageNo() == null) {
            flag = false;
        } else {
            PageHelper.startPage(approveSelectDto.getPageNo(), approveSelectDto.getPageSize());
        }
        List<Long> waitMeApproveIdList = approveProcedureMapper.getWaitMeApproveList(approveSelectDto);
        if (flag) {
            PageInfo page = new PageInfo(waitMeApproveIdList);
            approveListDto.setTotal(page.getTotal());
        } else {
            approveListDto.setTotal((long) waitMeApproveIdList.size());
        }
        if (waitMeApproveIdList != null && !waitMeApproveIdList.isEmpty()) {
            List<Approve> approveList = approveMapper.getWaitMeApproveList(waitMeApproveIdList);
            approveListDto = approveListToApproveDtoList(approveList, approveListDto);
        }
        return approveListDto;
    }

    /**
     * 获取我已审批列表
     *
     * @param approveSelectDto 审批查询DTO
     */
    @Override
    public ListDto<ApproveDto> getMyApprovedList(ApproveSelectDto approveSelectDto) {
        ListDto<ApproveDto> approveListDto = new ListDto<>();
        boolean flag = true;
        if (approveSelectDto.getPageSize() == null || approveSelectDto.getPageNo() == null) {
            flag = false;
        } else {
            PageHelper.startPage(approveSelectDto.getPageNo(), approveSelectDto.getPageSize());
        }
        List<Long> myApprovedList = approveProcedureMapper.getMyApprovedList(approveSelectDto);
        if (flag) {
            PageInfo page = new PageInfo(myApprovedList);
            approveListDto.setTotal(page.getTotal());
        } else {
            approveListDto.setTotal((long) myApprovedList.size());
        }
        if (myApprovedList != null && !myApprovedList.isEmpty()) {
            List<Approve> approveList = approveMapper.getMyApprovedList(myApprovedList);
            //实体给转化成DTO
            approveListDto = approveListToApproveDtoList(approveList,approveListDto);
        }
        return approveListDto;
    }

    /**
     * 审批：通用查询
     *
     * @param approveSelectDto 审批查询Dto
     * @return 审批列表
     */
    @Override
    public ListDto<ApproveDto> getApproveWithoutRole(ApproveSelectDto approveSelectDto) {
        ListDto<ApproveDto> approveListDto = new ListDto<>();
        boolean flag = true;
        if (approveSelectDto.getPageSize() == null || approveSelectDto.getPageNo() == null) {
            flag = false;
        } else {
            PageHelper.startPage(approveSelectDto.getPageNo(), approveSelectDto.getPageSize());
        }
        List<Approve> approveList = approveMapper.getApproveWithoutRole(approveSelectDto);
        if (flag) {
            PageInfo page = new PageInfo(approveList);
            approveListDto.setTotal(page.getTotal());
        } else {
            approveListDto.setTotal((long) approveList.size());
        }
        approveListDto = approveListToApproveDtoList(approveList,approveListDto);
        return approveListDto;
    }

    /**
     * 我发起的 审批中 数量
     *
     * @param userId 用户id
     * @return 数量
     */
    @Override
    public Long getMyLaunchApprovingCount(Long userId) {
        return approveMapper.getMyLaunchApprovingCount(userId);
    }

    /**
     * 我已审批 审批中 数量
     *
     * @param userId 用户id
     * @return 数量
     */
    @Override
    public Long getMyApprovedCount(Long userId) {
        return approveMapper.getMyApprovedCount(userId);
    }

    @Override
    public Long updateApprove(ApproveDto approveDto) {
        Approve approve = new Approve();
        BeanUtils.copyProperties(approveDto, approve);
        return approveMapper.updateByPrimaryKeySelective(approve);
    }

    /**
     * 审批实体列表转化为审批Dto 列表
     *
     * @param approveList 审批实体list
     * @return 审批Dto list
     */
    private static ListDto<ApproveDto> approveListToApproveDtoList(List<Approve> approveList,ListDto<ApproveDto> approveListDto) {
        if (approveList != null) {
            //金额数据除以1000 转成double类型
            List<ApproveDto> resultList = new ArrayList<>();
            if (approveList.size() != 0) {
                for (Approve temp : approveList) {
                    ApproveDto approveDto = new ApproveDto();
                    //填充
                    BeanUtils.copyProperties(temp, approveDto);
                    if (temp.getBudget() != null) {
                        approveDto.setBudget(new BigDecimal(BigDecimalUtil.doubleDiv(temp.getBudget(), 1000L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    if (temp.getAmount() != null) {
                        approveDto.setAmount(new BigDecimal(BigDecimalUtil.doubleDiv(temp.getAmount(), 1000L)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    resultList.add(approveDto);
                }
            }
            approveListDto.setList(resultList);
        }
        return approveListDto;
    }


}
