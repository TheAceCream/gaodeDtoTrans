package com.qf58.ace.approve.server.service;

import com.alibaba.fastjson.JSONObject;
import com.bj58.qf.order.dto.OperationUserDto;
import com.bj58.qf.order.service.ECommerceOrderService;
import com.qf58.ace.approve.api.service.ApproveService;
import com.qf58.ace.approve.dto.*;
import com.qf58.ace.approve.entity.Approve;
import com.qf58.ace.approve.entity.ApproveProcedure;
import com.qf58.ace.approve.enums.ApproveProcedureLotEnum;
import com.qf58.ace.approve.enums.ApproveProcedureStatusEnum;
import com.qf58.ace.approve.enums.ApproveProcedureTypeEnum;
import com.qf58.ace.approve.enums.ApproveStatusEnum;
import com.qf58.ace.approve.server.component.ApproveFlowComponent;
import com.qf58.ace.approve.server.dao.ApproveDao;
import com.qf58.ace.approve.server.dao.ApproveFileDao;
import com.qf58.ace.approve.server.dao.ApproveFlowDao;
import com.qf58.ace.approve.server.dao.ApproveProcedureDao;
import com.qf58.ace.code.APICode;
import com.qf58.ace.util.FileStringUtil;
import com.qf58.ace.util.UniqueIDUtils;
import com.qf58.acepassport.enums.AceBudgetLimitEnum;
import com.qf58.acepassport.service.AceUserService;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;
import com.qf58.client.support.OpResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import rabbitmq58lib.producer.ExchangeProduceServer;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/11/15 16:30 Time: 14:15
 *
 * 审批服务
 */
@Service("approveService")
public class ApproveServiceImpl implements ApproveService {

    private static final Logger logger = LoggerFactory.getLogger(ApproveServiceImpl.class);

    @Resource
    private ApproveDao approveDao;

    @Resource
    private ApproveFlowDao approveFlowDao;

    @Resource
    private ApproveProcedureDao approveProcedureDao;

    @Resource
    private ApproveFileDao approveFileDao;

    @Resource
    private ApproveFlowComponent approveFlowComponent;

    @Resource
    private ECommerceOrderService eCommerceOrderService;

    @Resource
    private AceUserService aceUserService;

    @Value("${MQ_RK_SMS_WAITQUEUE}")
    private String MQ_RK_SMS_WAITQUEUE;

    @Value("${MQ_EXCHANGE}")
    private String MQ_EXCHANGE;

    /**
     * 创建审批
     *
     * @param approveDto 审批dto
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult createApprove(ApproveDto approveDto) {
        //获取审批流名称 来补充审批标题
        String approveFlowName = "";
        //如果没有传审批id,则自己生成审批id
        if (approveDto.getId() == null) {
            approveDto.setId(UniqueIDUtils.getUniqueID());
        }
        //判断审批是否预算无限，若预算无限，则替换审批预算
        if (AceBudgetLimitEnum.INFINITE.getMoney().equals(approveDto.getTotalBudget())) {
            approveDto.setBudget(AceBudgetLimitEnum.INFINITE.getMoney());
        }
        //此时必有审批id
        ApproveFlowExternalGetDto approveFlowExternalGetDto = approveFlowDao.getExternalApproveFlowById(approveDto.getApproveFlowId());
        if (approveFlowExternalGetDto != null) {
            approveFlowName = approveFlowExternalGetDto.getApproveFlowName();
            //保证审批必有公司id 和 类目id ： 如果没有传值，则使用审批流中的公司id和类目id
            if (approveDto.getCompanyId() == null) {
                approveDto.setCompanyId(approveFlowExternalGetDto.getCompanyId());
            }
            if (approveDto.getCateId() == null) {
                approveDto.setCateId(approveFlowExternalGetDto.getCateId());
            }
        }
        String title = approveDto.getSponsorDesc() + "发起的" + approveFlowName;
        approveDto.setTitle(title);
        //创建审批
        Approve approve = approveDao.addApprove(approveDto);
        //创建审批成功
        if (approve != null) {
            if (approve.getDepartmentId() != null && approve.getApproveFlowId() != null && approve.getId() != null) {
                //创建并关联审批过程
                ApproveProcedureCreateDto dto = new ApproveProcedureCreateDto();
                //填充DTO
                dto.setApproveId(approve.getId());
                dto.setApproveFlowId(approve.getApproveFlowId());
                dto.setCompanyId(approve.getCompanyId());
                dto.setDeptId(approve.getDepartmentId());
                dto.setOriginator(approve.getSponsorId());
                dto.setTitle(approve.getTitle());
                dto.setInitiator(approve.getSponsorDesc());
                dto.setIsTest(approve.getIsTest());
                List<ApproveProcedure> approveProcedureList = approveFlowComponent.createApproveProcedure(dto);
                approveProcedureDao.addApproveProcedureList(approveProcedureList);
                //给状态为 审批中 的审批过程的审批人 发送邮件
                if (approveProcedureList != null) {
                    for (ApproveProcedure temp : approveProcedureList) {
                        if (temp.getStatus().equals(ApproveProcedureStatusEnum.APPROVEING.getCode())) {
                            if (temp.getApproveId() != null && temp.getApproverId() != null) {
                                //转为json
                                JSONObject result = new JSONObject();
                                result.put("approveId", temp.getApproveId().toString());
                                result.put("approverId", temp.getApproverId().toString());
                                // MQ通知发送邮件
                                logger.info("通知发送邮件{}", temp.getApproveId().toString());
                                ExchangeProduceServer.SendServerWithRoutingKey(MQ_EXCHANGE, result.toJSONString(), MQ_RK_SMS_WAITQUEUE);
                            }
                        }
                    }
                }
            }
        } else {
            return OpResult.fail(APICode.APPROVEFLOW_PROCESS_EMPTY.getCode(), APICode.APPROVEFLOW_PROCESS_EMPTY.getDesc());
        }
        if (approve.getId() != null) {
            System.out.println(OpResult.ok(approve.getId()));
            return OpResult.ok(approve.getId());
        }
        return OpResult.defaultFail();
    }

    /**
     * 审批详情
     *
     * @param approvalId 审批id
     * @return 审批详情
     */
    @Override
    public DtoResult<ApproveDto> approvalDetail(Long approvalId) {
        DtoResult result = DtoResult.getInstance();
        //审批详情信息
        ApproveDto approveDto = approveDao.getApproval(approvalId);
        if (approveDto != null) {
            result.setDto(approveDto);
        }
        return result;
    }

    /**
     * 我发起的审批列表
     *
     * @param approveSelectDto 审批选择Dto
     * @return 审批列表
     */
    @Override
    public CollectionResult<ApproveDto> getMyLaunchApprovalList(ApproveSelectDto approveSelectDto) {
        ListDto<ApproveDto> approveListDto = approveDao.getMyLaunchApprovalList(approveSelectDto);
        logger.info("-------------我发起的审批列表------------入参{}", approveSelectDto);
        return transToApproveList(approveListDto);
    }

    /**
     * 待我审批列表
     *
     * @param approveSelectDto 审批选择Dto
     * @return 审批列表
     */
    @Override
    public CollectionResult<ApproveDto> getWaitMeApprove(ApproveSelectDto approveSelectDto) {
        logger.info("-------------待我审批列表------------入参{}", approveSelectDto);
        ListDto<ApproveDto> approveDtoListDto = approveDao.getWaitMeApprove(approveSelectDto);
        return transToApproveList(approveDtoListDto);
    }

    /**
     * 我已审批列表
     *
     * @param approveSelectDto 审批选择Dto
     * @return 审批列表
     */
    @Override
    public CollectionResult<ApproveDto> getMyApprovedList(ApproveSelectDto approveSelectDto) {
        logger.info("-------------我已审批列表------------入参{}", approveSelectDto);
        ListDto<ApproveDto> approveDtoListDto = approveDao.getMyApprovedList(approveSelectDto);
        return transToApproveList(approveDtoListDto);
    }

    /**
     * 审批列表获取
     *
     * @param approveListDto 审批列表
     */
    private CollectionResult transToApproveList(ListDto<ApproveDto> approveListDto) {
        CollectionResult result = CollectionResult.getInstance();
        if (approveListDto.getList() != null) {
            result.setCollection(approveListDto.getList());
            result.setTotal(approveListDto.getTotal());
            result.setSuccess(true);
        } else {
            //未查到数据
            result.setTotal(0);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 通用审批列表查询
     *
     * @param approveSelectDto 审批查询DTO
     * @return 审批列表
     */
    @Override
    public CollectionResult<ApproveDto> getApproveWithoutRole(ApproveSelectDto approveSelectDto) {
        CollectionResult result = CollectionResult.getInstance();
        ListDto<ApproveDto> approveDtoListDto = approveDao.getApproveWithoutRole(approveSelectDto);
        if (approveDtoListDto != null) {
            result.setCollection(approveDtoListDto.getList());
            result.setTotal(approveDtoListDto.getTotal());
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 获取审批相关的数量
     *
     * @param myApprovalStatus 审批查询类型：1：我发起的，2：待我审批的，3：我已审批的
     * @param userId           用户id
     */
    @Override
    public DtoResult<ApproveCountDto> getAboutApproveCount(Integer myApprovalStatus, Long userId) {
        DtoResult result = DtoResult.getInstance();
        //先查询待我审批的数量
        Long waitMeApproveCount = approveProcedureDao.getWaitMeApproveCount(userId);
        Long approving = null;
        if (myApprovalStatus != null) {
            if (myApprovalStatus == 1) {
                //我发起的 处于 审批中
                approving = approveDao.getMyLaunchApprovingCount(userId);
            } else if (myApprovalStatus == 3) {
                //我已审批的 处于 审批中
                approving = approveDao.getMyApprovedCount(userId);
            }
        }
        ApproveCountDto approveCountDto = new ApproveCountDto();
        if (waitMeApproveCount != null || approving != null) {
            approveCountDto.setWaitMeApprove(waitMeApproveCount);
            approveCountDto.setApproving(approving);
            result.setDto(approveCountDto);
        } else {
            result.setCode(APICode.GET_PARAM_EXCEPTION.getCode());
            result.setMessage(APICode.GET_PARAM_EXCEPTION.getDesc());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 批量同意审批 条件：审批处于审批中
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult agreeApproval(List<Long> approveIdList, List<FileDto> annexList, List<FileDto> picList, String remark, Long userId) {
        if (approveIdList != null) {
            for (Long approveId : approveIdList) {
                //校验是否审批已失效
                boolean hasDisabled = checkApproveDisabled(approveId);
                if (hasDisabled) {
                    //已失效则直接返回
                    return OpResult.fail(APICode.HAS_CANCEL_EXCEPTION.getCode(), APICode.HAS_CANCEL_EXCEPTION.getDesc());
                }
                //同意审批
                agreeOne(approveId, userId, annexList, picList, remark);
            }
            return OpResult.ok();
        }
        return OpResult.fail(APICode.AGREE_EXCEPTION.getCode(), APICode.AGREE_EXCEPTION.getDesc());
    }

    /**
     * 同意审批
     *
     * @param approveId 审批id
     * @param userId    用户id
     */
    private void agreeOne(Long approveId, Long userId, List<FileDto> annexList, List<FileDto> picList, String remark) {
        //获取该审批的 审批过程
        ApproveProcedureSelectDto approveProcedureSelectDto = new ApproveProcedureSelectDto();
        approveProcedureSelectDto.setApproveId(approveId);
        ListDto<ApproveProcedureDto> allProcedureListDto = approveProcedureDao.getApproveProcedureList(approveProcedureSelectDto);
        if (allProcedureListDto != null) {
            //该审批下 所有审批过程
            List<ApproveProcedureDto> allList = allProcedureListDto.getList();
            approveProcedureSelectDto.setStatus(ApproveProcedureStatusEnum.APPROVEING.getCode());
            //获取该审批下 审批中 的审批过程
            List<ApproveProcedureDto> approvingList = getApprovingProcedureList(allList);
            dealApprovingList(approvingList, allList, userId, approveId, annexList, picList, remark);
        }
    }

    /**
     * 处理审批过程
     *
     * @param approvingList 审批中的审批过程
     * @param allList       所有审批过程
     * @param userId        用户id
     * @param approveId     审批id
     */
    private void dealApprovingList(List<ApproveProcedureDto> approvingList, List<ApproveProcedureDto> allList, Long userId, Long approveId, List<FileDto> annexList, List<FileDto> picList, String remark) {
        if (!approvingList.isEmpty()) {
            //操作人的审批过程
            ApproveProcedureDto myApproveProcedure = getMyApproveProcedure(approvingList, userId);
            // 开始给这个审批过程添加备注
            if (remark != null) {
                myApproveProcedure.setRemark(remark);
            }
            //查看 该组的 审批类型
            ApproveProcedureTypeEnum approveProcedureTypeEnum = getType(approvingList);
            //该组所在组数
            Integer groups = getGroup(approvingList);
            //获取这组的所有审批过程
            List<ApproveProcedureDto> groupsList = getGroupsListFromAll(allList, groups);
            if (myApproveProcedure != null && approveProcedureTypeEnum != null) {
                if (approveProcedureTypeEnum.equals(ApproveProcedureTypeEnum.CONTINUOUS_STAIR)) {
                    //类型为连续多级
                    dealWithContinuousAgree(myApproveProcedure, approveId, groupsList, allList, approvingList, userId);
                } else {
                    //类型不是连续多级
                    dealWithNormalAgree(myApproveProcedure, approveId, groupsList, allList, userId);
                }
            }
            if (myApproveProcedure != null) {
                // 审批过程id
                Long approveProcedureId = myApproveProcedure.getId();
                //审批人姓名
                String approverName = myApproveProcedure.getApprover();
                // 记录 图片、附件信息入库
                // 附件信息入库
                if (annexList != null) {
                    for (FileDto annexFile : annexList) {
                        // 附件
                        ApproveFileDto annexFileDto = new ApproveFileDto();
                        // 审批id
                        annexFileDto.setApproveId(approveId);
                        // 审批过程id
                        annexFileDto.setApproveProcedureId(approveProcedureId);
                        //审批人信息
                        annexFileDto.setUserName(approverName);
                        // 文件类型是 2.文件
                        annexFileDto.setFileType((byte) 2);
                        // 附件原始名称
                        if (annexFile.getName() != null) {
                            annexFileDto.setOriginName(annexFile.getName());
                        }
                        if (annexFile.getSize() != null) {
                            // 文件大小
                            String size = FileStringUtil.convertFileSize(annexFile.getSize());
                            annexFileDto.setSize(size);
                        }
                        // 路径
                        if (annexFile.getUrl() != null) {
                            annexFileDto.setPicUrl(annexFile.getUrl());
                            annexFileDto.setFileKey(annexFile.getUrl());
                        }
                        //添加记录
                        approveFileDao.insertSelective(annexFileDto);
                    }
                }

                // 图片 信息入库
                if (picList != null) {
                    for (FileDto picFile : picList) {
                        ApproveFileDto picFileDto = new ApproveFileDto();
                        picFileDto.setApproveId(approveId);
                        picFileDto.setApproveProcedureId(approveProcedureId);
                        //审批人信息
                        picFileDto.setUserName(approverName);
                        //文件类型是 1.图片
                        picFileDto.setFileType((byte) 1);
                        if (picFile.getName() != null) {
                            picFileDto.setOriginName(picFile.getName());
                        }
                        if (picFile.getSize() != null) {
                            // 文件大小
                            String size = FileStringUtil.convertFileSize(picFile.getSize());
                            picFileDto.setSize(size);
                        }
                        // 键 和 地址
                        if (picFile.getUrl() != null) {
                            picFileDto.setPicUrl(picFile.getUrl());
                            picFileDto.setFileKey(picFile.getUrl());
                        }
                        approveFileDao.insertSelective(picFileDto);
                    }
                }
            }

        }
    }

    /**
     * 撤销审批 条件：审批处于审批中
     *
     * @param approvalId 审批id
     * @param userId     用户id
     * @return 是否成功
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult cancelApproval(Long approvalId, Long userId) {
        //校验是否审批已失效
        boolean hasDisabled = checkApproveDisabled(approvalId);
        if (hasDisabled) {
            //已撤销则直接返回
            return OpResult.fail(APICode.HAS_CANCEL_EXCEPTION.getCode(), APICode.HAS_CANCEL_EXCEPTION.getDesc());
        }
        //获取该审批的 审批过程
        ApproveProcedureSelectDto approveProcedureSelectDto = new ApproveProcedureSelectDto();
        approveProcedureSelectDto.setApproveId(approvalId);
        ListDto<ApproveProcedureDto> allProcedureListDto = approveProcedureDao.getApproveProcedureList(approveProcedureSelectDto);
        if (allProcedureListDto != null) {
            //该审批下 所有审批过程
            List<ApproveProcedureDto> allList = allProcedureListDto.getList();
            //审批中的审批过程
            List<ApproveProcedureDto> approvingList = getApprovingProcedureList(allList);
            if (getGroup(approvingList) == null) {
                //如果一开始就无法继续审批 需要走特殊流程
                //审批 状态改为 4.审批撤销
                ApproveDto approveDto = new ApproveDto();
                approveDto.setId(approvalId);
                approveDto.setStatus(ApproveStatusEnum.CANCEL_APPROVE.getCode());
                Timestamp initTime = new Timestamp(System.currentTimeMillis());
                approveDto.setUpdateTime(initTime);
                approveDto.setEndTime(initTime);
                approveDao.updateApprove(approveDto);
                //除了发起的那一条 所有审批过程 变为 7.失效
                List<ApproveProcedureDto> needList = new ArrayList<>();
                for (ApproveProcedureDto temp : allList) {
                    if (temp.getStatus().intValue() != 1) {
                        needList.add(temp);
                    }
                }
                approveToInvalid(needList);
                //订单失效
                OperationUserDto operationUserDto = new OperationUserDto();
                operationUserDto.setId(userId);
                eCommerceOrderService.cancelAceOrder(approvalId, "撤销审批", true, operationUserDto);
                return OpResult.ok();
            }
            //审批中处于哪一组
            int approvingGroup = getGroup(approvingList);
            //拿到审批中的那一组，包括了这组里之前有人审批通过
            List<ApproveProcedureDto> approving = getGroupsListFromAll(allList, approvingGroup);
            //该审批下 待审批 的审批过程
            List<ApproveProcedureDto> needList = getWaitProcedureList(allList);
            //取并集
            needList.addAll(approving);
            //审批中和待审批 的全部变成 无效
            approveToInvalid(needList);
            //审批状态改为 撤销审批
            ApproveDto approveDto = new ApproveDto();
            approveDto.setId(approvalId);
            approveDto.setStatus(ApproveStatusEnum.CANCEL_APPROVE.getCode());
            Timestamp initTime = new Timestamp(System.currentTimeMillis());
            approveDto.setUpdateTime(initTime);
            approveDto.setEndTime(initTime);
            approveDao.updateApprove(approveDto);
            //订单失效
            OperationUserDto operationUserDto = new OperationUserDto();
            operationUserDto.setId(userId);
            eCommerceOrderService.cancelAceOrder(approvalId, "撤销审批", true, operationUserDto);
            return OpResult.ok();
        }
        return OpResult.fail(APICode.AGREE_EXCEPTION.getCode(), APICode.AGREE_EXCEPTION.getDesc());
    }

    /**
     * 校验审批是否 已经失效
     *
     * @param approvalId 审批id
     * @return true审批已经失效 false是处于审批中
     */
    private boolean checkApproveDisabled(Long approvalId) {
        ApproveDto approveDto = approveDao.getApproval(approvalId);
        if (approveDto != null) {
            //审批属于审批状态
            return !approveDto.getStatus().equals(ApproveStatusEnum.IN_APPROVE.getCode());
        }
        return true;
    }

    /**
     * 获取 待审批 的审批过程
     */
    private List<ApproveProcedureDto> getWaitProcedureList(List<ApproveProcedureDto> allList) {
        List<ApproveProcedureDto> approveProcedureDtoList = new ArrayList<>();
        for (ApproveProcedureDto temp : allList) {
            if (temp.getStatus().equals(ApproveProcedureStatusEnum.WAIT_APPROVE.getCode())) {
                approveProcedureDtoList.add(temp);
            }
        }
        return approveProcedureDtoList;
    }

    /**
     * 批量拒绝审批 条件：审批处于审批中
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult refuseApproval(List<Long> approveIdList, List<FileDto> annexList, List<FileDto> picList, String remark, Long userId) {
        if (approveIdList != null) {
            for (Long approveId : approveIdList) {
                //校验是否审批已撤销
                boolean hasDisabled = checkApproveDisabled(approveId);
                if (hasDisabled) {
                    //已撤销则直接返回
                    return OpResult.fail(APICode.HAS_CANCEL_EXCEPTION.getCode(), APICode.HAS_CANCEL_EXCEPTION.getDesc());
                }
                refuseOne(approveId, userId, annexList, picList, remark);
            }
            return OpResult.ok();
        }
        return OpResult.fail(APICode.AGREE_EXCEPTION.getCode(), APICode.AGREE_EXCEPTION.getDesc());
    }

    /**
     * 拒绝审批
     *
     * @param approveId 审批id
     */
    private void refuseOne(Long approveId, Long userId, List<FileDto> annexList, List<FileDto> picList, String remark) {
        //获取该审批的 审批过程
        ApproveProcedureSelectDto approveProcedureSelectDto = new ApproveProcedureSelectDto();
        approveProcedureSelectDto.setApproveId(approveId);
        ListDto<ApproveProcedureDto> allProcedureListDto = approveProcedureDao.getApproveProcedureList(approveProcedureSelectDto);
        if (allProcedureListDto != null) {
            List<ApproveProcedureDto> allList = allProcedureListDto.getList();
            approveProcedureSelectDto.setStatus(ApproveProcedureStatusEnum.APPROVEING.getCode());
            //该审批下 审批中 的审批过程
            ListDto<ApproveProcedureDto> approvingListDto = approveProcedureDao.getApproveProcedureList(approveProcedureSelectDto);
            if (approvingListDto != null) {
                List<ApproveProcedureDto> approvingList = approvingListDto.getList();
                ApproveProcedureDto myApproveProcedure = getMyApproveProcedure(approvingList, userId);
                //拒绝
                if (remark != null) {
                    myApproveProcedure.setRemark(remark);
                }
                //更改remark
                approveProcedureDao.updateApproveProcedure(myApproveProcedure);
                //审批中 处于哪一组
                int approvingGroup = getGroup(approvingList);
                //拿到审批中的那一组，包括了这组里之前有人审批通过
                List<ApproveProcedureDto> approving = getGroupsListFromAll(allList, approvingGroup);
                //审批中 包括这组之前审批同意的人 状态全部改为 已拒绝
                approveToRefuse(approving);
                //获取所有待审批的审批过程
                List<ApproveProcedureDto> waitForApprovingList = getWaitApproveProcedureList(allList);
                //待审批 状态全部改为 无效
                approveToInvalid(waitForApprovingList);
                //根据审批id 更改审批状态为 审批拒绝
                ApproveDto approveDto = new ApproveDto();
                approveDto.setId(approveId);
                approveDto.setStatus(ApproveStatusEnum.REFUSE_APPROVE.getCode());
                Timestamp initTime = new Timestamp(System.currentTimeMillis());
                approveDto.setUpdateTime(initTime);
                approveDto.setEndTime(initTime);
                approveDao.updateApprove(approveDto);
                //订单失效
                OperationUserDto operationUserDto = new OperationUserDto();
                operationUserDto.setId(userId);
                eCommerceOrderService.cancelAceOrder(approveId, "审批拒绝", true, operationUserDto);


                // 审批过程id
                Long approveProcedureId = myApproveProcedure.getId();
                //审批人姓名
                String approverName = myApproveProcedure.getApprover();
                // 记录 图片、附件信息入库
                // 附件信息入库
                if (annexList != null) {
                    for (FileDto annexFile : annexList) {
                        // 附件
                        ApproveFileDto annexFileDto = new ApproveFileDto();
                        // 审批id
                        annexFileDto.setApproveId(approveId);
                        //审批人信息
                        annexFileDto.setUserName(approverName);
                        // 文件类型是 2.文件
                        annexFileDto.setFileType((byte) 2);
                        // 附件原始名称
                        if (annexFile.getName() != null) {
                            annexFileDto.setOriginName(annexFile.getName());
                        }
                        if (annexFile.getSize() != null) {
                            // 文件大小
                            String size = FileStringUtil.convertFileSize(annexFile.getSize());
                            annexFileDto.setSize(size);
                        }
                        // 路径
                        if (annexFile.getUrl() != null) {
                            annexFileDto.setPicUrl(annexFile.getUrl());
                            annexFileDto.setFileKey(annexFile.getUrl());
                        }
                        //添加记录
                        approveFileDao.insertSelective(annexFileDto);
                    }
                }

                // 图片 信息入库
                if (picList != null) {
                    for (FileDto picFile : picList) {
                        ApproveFileDto picFileDto = new ApproveFileDto();
                        picFileDto.setApproveId(approveId);
                        picFileDto.setApproveProcedureId(approveProcedureId);
                        //审批人信息
                        picFileDto.setUserName(approverName);
                        //文件类型是 1.图片
                        picFileDto.setFileType((byte) 1);
                        if (picFile.getName() != null) {
                            picFileDto.setOriginName(picFile.getName());
                        }
                        if (picFile.getSize() != null) {
                            // 文件大小
                            String size = FileStringUtil.convertFileSize(picFile.getSize());
                            picFileDto.setSize(size);
                        }
                        // 键 和 地址
                        if (picFile.getUrl() != null) {
                            picFileDto.setPicUrl(picFile.getUrl());
                            picFileDto.setFileKey(picFile.getUrl());
                        }
                        approveFileDao.insertSelective(picFileDto);
                    }
                }
            }
        }
    }

    /**
     * 类型为 连续多级 的同意审批
     *
     * @param myApproveProcedure 审批中操作人的审批过程
     * @param approveId          审批id
     * @param groupsList         该审批下 处于该组的审批过程
     * @param allList            该审批下所有的审批过程
     * @param approvingList      该组内处于 审批中 的审批过程
     * @param userId             操作人(审批人)id
     */
    private void dealWithContinuousAgree(ApproveProcedureDto myApproveProcedure, Long approveId, List<ApproveProcedureDto> groupsList, List<ApproveProcedureDto> allList, List<ApproveProcedureDto> approvingList, Long userId) {
        //查看我要处理的 审批过程 或签还是会签
        Byte lot = myApproveProcedure.getLot();
        //查看自己所在第几组
        int groups = myApproveProcedure.getGroups().intValue();
        //查看watch_level 自己这条审批过程的可见级别 这直接证明了处于 审批中 状态的审批过程的可见级别
        int watchLevel = myApproveProcedure.getWatchLevel().intValue();
        if (lot.equals(ApproveProcedureLotEnum.COUNTERSIGN.getCode())) {
            //会签 只更改属于自己的审批过程 为 已同意
            myApproveProcedure.setStatus(ApproveProcedureStatusEnum.APPLY.getCode());
            Timestamp initTime = new Timestamp(System.currentTimeMillis());
            myApproveProcedure.setApproveTime(initTime);
            myApproveProcedure.setUpdateTime(initTime);
            approveProcedureDao.updateApproveProcedure(myApproveProcedure);
            //查看本watch_level下(处于审批中的) 除了自己还是否有 审批中的
            boolean haveApproving = isHaveApproving(approvingList, userId);
            if (!haveApproving) {
                //查看是否还有下一组审批过程
                boolean ifNextGroup = isHaveNextGroup(groups, allList);
                //查看本组这个watch_level后面 是否还有下一Watch_level审批过程
                boolean isNextWatchLevel = isHaveNextWatchLevel(watchLevel, groupsList);
                manageNextWatchLevelOrGroup(isNextWatchLevel, ifNextGroup, groups, watchLevel, groupsList, allList, approveId);
            }
        } else {
            //或签 更改同watch_level的所有审批过程为 已同意
            approveToAgree(approvingList);
            //查看是否还有下一组审批过程
            boolean ifNextGroup = isHaveNextGroup(groups, allList);
            //查看本组这个watch_level后面 是否还有下一Watch_level审批过程
            boolean isNextWatchLevel = isHaveNextWatchLevel(watchLevel, groupsList);
            manageNextWatchLevelOrGroup(isNextWatchLevel, ifNextGroup, groups, watchLevel, groupsList, allList, approveId);
        }

    }

    /**
     * 处理下一个WatchLevel 或者是 Group
     *
     * @param isNextWatchLevel 后面是否还有 watch_level
     * @param isNextGroup      是否还有下一组
     * @param groups           本组是第几组
     * @param watchLevel       当前审批过程的watch_level
     * @param groupsList       审批到的那组 审批过程
     * @param allList          该审批的全部审批过程
     * @param approveId        审批id
     */
    private void manageNextWatchLevelOrGroup(boolean isNextWatchLevel, boolean isNextGroup, int groups, int watchLevel, List<ApproveProcedureDto> groupsList, List<ApproveProcedureDto> allList, Long approveId) {
        if (isNextWatchLevel) {
            //有 而且由于是同一组的 所以必然也是连续多级
            //下一可见级别
            int nextWatchLevel = watchLevel + 1;
            List<ApproveProcedureDto> nextWatchLevelList = getWatchLevelListFromGroups(groupsList, nextWatchLevel);
            //更改 下一级别审批过程的状态
            updateProcedureByType(false, nextWatchLevelList, approveId);

        } else {
            //没有
            //根据是否有下一组审批过程 去处理
            manageNextGroupOrApprove(isNextGroup, groups, allList, approveId);
        }

    }

    /**
     * 获取某一组审批过程里面，某一可见级别的 审批过程列表
     *
     * @param groupsList     某一组审批过程(必须是同一组)
     * @param nextWatchLevel 下一审批级别
     * @return 审批过程列表
     */
    private List<ApproveProcedureDto> getWatchLevelListFromGroups(List<ApproveProcedureDto> groupsList, Integer nextWatchLevel) {
        //获取某一可见级别的的所有审批过程
        List<ApproveProcedureDto> watchLevelList = new ArrayList<>();
        for (ApproveProcedureDto temp : groupsList) {
            if (nextWatchLevel.equals(temp.getWatchLevel().intValue())) {
                watchLevelList.add(temp);
            }
        }
        return watchLevelList;

    }

    /**
     * 是否后面还有watchLevel
     *
     * @param watchLevel 可见级别
     * @param groupsList 这一组的审批过程
     * @return 是否还有watchLevel
     */
    private boolean isHaveNextWatchLevel(int watchLevel, List<ApproveProcedureDto> groupsList) {
        boolean ifNextWatchLevel = false;
        int nextWatchLevel = watchLevel + 1;
        for (ApproveProcedureDto temp : groupsList) {
            if (temp.getWatchLevel().intValue() == nextWatchLevel) {
                ifNextWatchLevel = true;
                break;
            }
        }
        return ifNextWatchLevel;
    }


    /**
     * 类型为 非连续多级 的同意审批
     *
     * @param myApproveProcedure 审批中操作人的审批过程
     * @param approveId          审批id
     * @param groupsList         该审批下 处于该组的审批过程
     * @param allList            该审批下所有的审批过程
     * @param userId             操作人(审批人)id
     */
    private void dealWithNormalAgree(ApproveProcedureDto myApproveProcedure, Long approveId, List<ApproveProcedureDto> groupsList, List<ApproveProcedureDto> allList, Long userId) {
        if (myApproveProcedure != null) {
            //查看 会签 or 或签
            byte lot = myApproveProcedure.getLot();
            //第几组
            int groups = myApproveProcedure.getGroups().intValue();
            boolean ifNextGroup = isHaveNextGroup(groups, allList);
            if (lot == ApproveProcedureLotEnum.COUNTERSIGN.getCode()) {
                //会签 只更改属于自己的审批过程 为 已同意
                myApproveProcedure.setStatus(ApproveProcedureStatusEnum.APPLY.getCode());
                Timestamp initTime = new Timestamp(System.currentTimeMillis());
                myApproveProcedure.setUpdateTime(initTime);
                myApproveProcedure.setApproveTime(initTime);
                approveProcedureDao.updateApproveProcedure(myApproveProcedure);
                //查看本组 除了自己这条审批过程 是否还有 其他过程处于 审批中 状态
                boolean haveApproving = isHaveApproving(groupsList, userId);
                if (!haveApproving) {
                    //查看是否还有下一组审批过程
                    manageNextGroupOrApprove(ifNextGroup, groups, allList, approveId);
                }
            } else if (lot == ApproveProcedureLotEnum.OR_SIGN.getCode()) {
                //或签 更改该组所有的审批过程 为 已同意
                approveToAgree(groupsList);
                //查看现在所处位置是否为最后一组
                //查看是否还有下一组审批过程
                manageNextGroupOrApprove(ifNextGroup, groups, allList, approveId);
            } else if (lot == ApproveProcedureLotEnum.SYSTEM_AUTO_PASS.getCode()) {
                // 系统自动通过
                manageNextGroupOrApprove(ifNextGroup, groups, allList, approveId);
            }
        }
    }

    /**
     * 批量将非系统自动通过的审批过程 状态转为 已同意
     *
     * @param groupsList 审批过程列表
     */
    private void approveToAgree(List<ApproveProcedureDto> groupsList) {
        for (ApproveProcedureDto temp : groupsList) {
            if (!temp.getStatus().equals(ApproveProcedureStatusEnum.SYSTEM_AUTO_PASS.getCode())) {
                temp.setStatus(ApproveProcedureStatusEnum.APPLY.getCode());
                Timestamp initTime = new Timestamp(System.currentTimeMillis());
                temp.setApproveTime(initTime);
                temp.setUpdateTime(initTime);
                approveProcedureDao.updateApproveProcedure(temp);
            }
        }
    }

    /**
     * 批量将审批过程 状态转为 已拒绝
     *
     * @param groupsList 审批过程列表
     */
    private void approveToRefuse(List<ApproveProcedureDto> groupsList) {
        for (ApproveProcedureDto temp : groupsList) {
            temp.setStatus(ApproveProcedureStatusEnum.REFUSE.getCode());
            Timestamp initTime = new Timestamp(System.currentTimeMillis());
            temp.setApproveTime(initTime);
            temp.setUpdateTime(initTime);
            approveProcedureDao.updateApproveProcedure(temp);
        }
    }

    /**
     * 批量将审批过程 状态转为 无效
     *
     * @param groupsList 审批过程列表
     */
    private void approveToInvalid(List<ApproveProcedureDto> groupsList) {
        for (ApproveProcedureDto temp : groupsList) {
            temp.setStatus(ApproveProcedureStatusEnum.INVALID.getCode());
            Timestamp initTime = new Timestamp(System.currentTimeMillis());
            temp.setApproveTime(initTime);
            temp.setUpdateTime(initTime);
            approveProcedureDao.updateApproveProcedure(temp);
        }
    }

    /**
     * 同意审批的动作结束后：如果审批过程还有下一组 则根据情况更改下一组审批过程的状态，如果没有下一组 则更改审批状态
     *
     * @param ifNextGroup 是否有下一组审批过程
     * @param groups      现在是第几组
     * @param allList     该审批的全部审批过程
     * @param approveId   审批id
     */
    private void manageNextGroupOrApprove(boolean ifNextGroup, int groups, List<ApproveProcedureDto> allList, Long approveId) {
        //最后一组的组数
        int lastGroupCount = allList.size()-1;
        if (ifNextGroup) {
            //还有下一组
            //考虑到复杂情况下 可能中间会有 系统自动通过审批 那么 不能单一的处理下一组,需要跳过自动审批的分组
            //获取到下一个非自动处理的组 并更改状态
            int trueNextGroup = getTrueNextGroup(groups,lastGroupCount,allList);
            List<ApproveProcedureDto> trueNextGroupList = getGroupsListFromAll(allList, trueNextGroup);
            // 同时考虑到最后连续自动审批 trueNextGroup 可能到了最后一组审批过程了，可依然是自动审批的情况
            if (trueNextGroupList.size()==1){
                //很难达到的情况： 系统自动通过，同时是最后一组
                if (trueNextGroupList.get(0).getStatus()==ApproveProcedureStatusEnum.SYSTEM_AUTO_PASS.getCode() && trueNextGroupList.get(0).getGroups()==lastGroupCount){
                    // 直接让订单生效
                    ApproveDto approveDto = new ApproveDto();
                    approveDto.setId(approveId);
                    approveDto.setStatus(ApproveStatusEnum.PASS_APPROVE.getCode());
                    Timestamp initTime = new Timestamp(System.currentTimeMillis());
                    approveDto.setUpdateTime(initTime);
                    approveDto.setEndTime(initTime);
                    approveDao.updateApprove(approveDto);
                    // 订单生效
                    eCommerceOrderService.confirmAceOrder(approveId);
                    return;
                }
            }
            //如果最后的 是否 为连续多级
            boolean isContinuous = isContinuous(trueNextGroupList);
            //根据是否为连续多级，更改 审批过程的状态
            updateProcedureByType(isContinuous, trueNextGroupList, approveId);
        } else {
            //该组为最后一组
            //根据审批id 更改审批状态为 通过审批
            ApproveDto approveDto = new ApproveDto();
            approveDto.setId(approveId);
            approveDto.setStatus(ApproveStatusEnum.PASS_APPROVE.getCode());
            Timestamp initTime = new Timestamp(System.currentTimeMillis());
            approveDto.setUpdateTime(initTime);
            approveDto.setEndTime(initTime);
            approveDao.updateApprove(approveDto);
            //订单生效
            eCommerceOrderService.confirmAceOrder(approveId);
        }
    }

    /**
     * @param groups  当前组数
     * @param allList 所有审批过程
     */
    private int getTrueNextGroup(int groups,int lastGroupCount, List<ApproveProcedureDto> allList) {
        //获取到最后一组的组数
        int nextGroups = groups + 1;
        while (nextGroups<lastGroupCount){
            List<ApproveProcedureDto> nextGroupList = getGroupsListFromAll(allList, nextGroups);
            if (nextGroupList!=null){
                if (nextGroupList.size()==1){
                    if(ApproveProcedureStatusEnum.SYSTEM_AUTO_PASS.getCode()==nextGroupList.get(0).getStatus()){
                        //如果是 系统自动通过审批 则跳过这组
                        nextGroups+=1;
                    }
                }else {
                    //如果这组不是 自动通过审批
                    break;
                }
            }
        }
        return nextGroups;
    }

    /**
     * 根据是否为连续多级，更改 一组审批过程的状态 是连续多级： 更改watch_level为最低 的状态 不是连续多级： 更改所有状态
     *
     * @param isContinuous  是否是连续多级
     * @param nextGroupList 某一组审批过程
     */
    private void updateProcedureByType(boolean isContinuous, List<ApproveProcedureDto> nextGroupList, Long approveId) {
        List<Long> approverIdList = new ArrayList<>();
        if (isContinuous) {
            //取个最大值
            int count = Integer.MAX_VALUE;
            //count 取下一级别中 watch_level 最小值
            if (nextGroupList != null) {
                for (ApproveProcedureDto temp : nextGroupList) {
                    if (temp.getWatchLevel() < count) {
                        count = temp.getWatchLevel();
                    }
                }
            }
            if (count != Integer.MAX_VALUE) {
                //将这些审批过程里面 中watch_level为最小的 状态变为4(审批中)
                for (ApproveProcedureDto temp : nextGroupList) {
                    if (temp.getWatchLevel().intValue() == count) {
                        temp.setStatus(ApproveProcedureStatusEnum.APPROVEING.getCode());
                        approveProcedureDao.updateApproveProcedure(temp);
                        if (temp.getApproveId() != null && temp.getApproverId() != null) {
                            //转为json
                            JSONObject result = new JSONObject();
                            result.put("approveId", temp.getApproveId().toString());
                            result.put("approverId", temp.getApproverId().toString());
                            // MQ通知发送邮件
                            logger.info("通知发送邮件");
                            ExchangeProduceServer.SendServerWithRoutingKey(MQ_EXCHANGE, result.toJSONString(), MQ_RK_SMS_WAITQUEUE);
                        }
                    }
                }
            }
        } else {
            //不为连续多级 则这些审批过程 非自动通过的 状态都变为4(审批中)
            for (ApproveProcedureDto temp : nextGroupList) {
                //判断 状态不是自动通过审批的 状态变为4
                if (ApproveProcedureStatusEnum.SYSTEM_AUTO_PASS.getCode() != temp.getStatus()) {
                    temp.setStatus(ApproveProcedureStatusEnum.APPROVEING.getCode());
                    approveProcedureDao.updateApproveProcedure(temp);
                    approverIdList.add(temp.getApproverId());
                    if (temp.getApproveId() != null && temp.getApproverId() != null) {
                        //转为json
                        JSONObject result = new JSONObject();
                        result.put("approveId", temp.getApproveId().toString());
                        result.put("approverId", temp.getApproverId().toString());
                        // MQ通知发送邮件
                        logger.info("通知发送邮件");
                        ExchangeProduceServer.SendServerWithRoutingKey(MQ_EXCHANGE, result.toJSONString(), MQ_RK_SMS_WAITQUEUE);
                    }
                }
            }
        }
    }


    /**
     * 是否为连续多级
     *
     * @param nextGroupList 审批过程List
     * @return 是否连续多级
     */
    private boolean isContinuous(List<ApproveProcedureDto> nextGroupList) {
        return nextGroupList.get(0).getType().equals(ApproveProcedureTypeEnum.CONTINUOUS_STAIR.getCode());
    }

    /**
     * 查看对于一组审批过程 ： 除了自己这条审批过程 是否还有 其他过程处于 审批中 状态
     *
     * @param groupsList 审批过程列表
     * @return 是否有处于 审批中
     */
    private boolean isHaveApproving(List<ApproveProcedureDto> groupsList, Long userId) {
        boolean flag = false;
        for (ApproveProcedureDto temp : groupsList) {
            if (!temp.getApproverId().equals(userId)) {
                if (temp.getStatus().equals(ApproveProcedureStatusEnum.APPROVEING.getCode())) {
                    //还有处于审批中的
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 此组后面是否还有 下一组 审批过程
     */
    private boolean isHaveNextGroup(int groups, List<ApproveProcedureDto> allList) {
        boolean ifNextGroup = false;
        int nextGroups = groups + 1;
        for (ApproveProcedureDto temp : allList) {
            if (temp.getGroups().intValue() == nextGroups) {
                ifNextGroup = true;
                break;
            }
        }
        return ifNextGroup;
    }


    /**
     * 获取操作人处于 审批中 的审批过程
     */
    private ApproveProcedureDto getMyApproveProcedure(List<ApproveProcedureDto> approvingList, Long userId) {
        ApproveProcedureDto myApproveProcedure = null;
        //找到处于审批中 自己的 审批过程
        for (ApproveProcedureDto temp : approvingList) {
            if (temp.getApproverId().equals(userId)) {
                myApproveProcedure = temp;
                break;
            }
        }
        return myApproveProcedure;
    }

    /**
     * 获取一组审批过程 所属的 类型
     *
     * @param approvingListDto 审批过程
     * @return 类型枚举
     */
    private ApproveProcedureTypeEnum getType(List<ApproveProcedureDto> approvingListDto) {
        Byte type = null;
        if (approvingListDto != null && !approvingListDto.isEmpty()) {
            //查看 该组的 审批类型
            type = approvingListDto.get(0).getType();
        }
        if (type == null) {
            return null;
        }
        return ApproveProcedureTypeEnum.getByCode(type);
    }

    /**
     * 获取本组 在过程中属于 第几组
     *
     * @param approvingListDto 审批过程ListDto (list中数据必须在同一组)
     * @return 第几组
     */
    private Integer getGroup(List<ApproveProcedureDto> approvingListDto) {
        Integer groups = null;
        if (approvingListDto != null && !approvingListDto.isEmpty()) {
            //该组所在组数
            groups = approvingListDto.get(0).getGroups().intValue();
        }
        return groups;
    }

    /**
     * 获取某一组的审批过程
     *
     * @param allList 所有审批过程
     * @param groups  某一组
     * @return 某一组的审批过程
     */
    private List<ApproveProcedureDto> getGroupsListFromAll(List<ApproveProcedureDto> allList, Integer groups) {
        //获取某一组的所有审批过程
        List<ApproveProcedureDto> groupsList = new ArrayList<>();
        if (allList != null) {
            for (ApproveProcedureDto temp : allList) {
                if (groups.equals(temp.getGroups().intValue())) {
                    groupsList.add(temp);
                }
            }
        }
        return groupsList;
    }

    /**
     * 在审批过程列表中 选择 状态为审批中的 审批过程
     *
     * @param allList 审批过程列表
     * @return 审批中的 审批过程
     */
    private List<ApproveProcedureDto> getApprovingProcedureList(List<ApproveProcedureDto> allList) {
        List<ApproveProcedureDto> approvingList = new ArrayList<>();
        //获取 审批中 的 审批过程
        for (ApproveProcedureDto temp : allList) {
            if (temp.getStatus().equals(ApproveProcedureStatusEnum.APPROVEING.getCode())) {
                approvingList.add(temp);
            }
        }
        return approvingList;
    }

    /**
     * 获取 待审批的 审批过程
     *
     * @param allList 审批过程列表
     * @return 待审批的审批过程
     */
    private List<ApproveProcedureDto> getWaitApproveProcedureList(List<ApproveProcedureDto> allList) {
        List<ApproveProcedureDto> waitApproveList = new ArrayList<>();
        //获取 审批中 的 审批过程
        for (ApproveProcedureDto temp : allList) {
            if (temp.getStatus().equals(ApproveProcedureStatusEnum.WAIT_APPROVE.getCode())) {
                waitApproveList.add(temp);
            }
        }
        return waitApproveList;
    }


}
