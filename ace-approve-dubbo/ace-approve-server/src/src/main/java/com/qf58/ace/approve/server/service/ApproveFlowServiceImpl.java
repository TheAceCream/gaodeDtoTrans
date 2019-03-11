package com.qf58.ace.approve.server.service;

import com.qf58.ace.approve.api.service.ApproveFlowService;
import com.qf58.ace.approve.dto.*;
import com.qf58.ace.approve.entity.*;
import com.qf58.ace.approve.enums.ApproveFlowModeEnum;
import com.qf58.ace.approve.enums.ApproveFlowVisibilityControlEnum;
import com.qf58.ace.approve.server.component.ApproveFlowComponent;
import com.qf58.ace.approve.server.dao.ApproveFlowDao;
import com.qf58.ace.approve.server.dao.ApproveFlowVisibilityDao;
import com.qf58.ace.approve.server.dao.ApproveProcessDao;
import com.qf58.ace.code.APICode;
import com.qf58.ace.util.TimeUtil;
import com.qf58.ace.util.UniqueIDUtils;
import com.qf58.cate.dto.method.ACateSelectDto;
import com.qf58.cate.service.SystemCateService;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;
import com.qf58.client.support.OpResult;
import com.qf58.acepassport.dto.*;
import com.qf58.acepassport.enums.AceTestEnum;
import com.qf58.acepassport.service.AceDeptService;
import com.qf58.acepassport.service.AceRoleService;
import com.qf58.acepassport.service.AceUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.APPOINT;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.CONTINUITY;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.EMPLOYEE;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.ROLE;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.MYSELF;
import static com.qf58.ace.approve.enums.ApproveFlowRoleTypeEnum.AND;
import static com.qf58.ace.approve.enums.ApproveFlowRoleTypeEnum.OR;
import static com.qf58.ace.approve.enums.ApproveFlowVisibilityControlEnum.DEPT_AND_ROLE_LEVEL;
import static com.qf58.ace.code.APICode.APPROVEFLOW_VISIBILITY_DEPT_EMPTY;
import static com.qf58.ace.code.APICode.APPROVEFLOW_VISIBILITY_ROLE_EMPTY;
import static com.qf58.ace.code.APICode.APPROVEFLOW_VISIBILITY_USER_EMPTY;
import static com.qf58.ace.code.APICode.APPROVEFLOW_PROCESS_EMPTY;
import static com.qf58.ace.code.APICode.APPROVEFLOW_VISIBILITY_CONTROL_SIGN_PARAMETER_ERROR;
import static com.qf58.ace.code.APICode.QIFU_PASSPORT_CLIENT_API_ERR;
import static com.qf58.ace.code.APICode.APPROVEFLOW_VISIBILITY_DEPT_ERR;


/**
 * 审批流服务
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月16日
 * @modifytime:
 */
@Service("approveFlowService")
public class ApproveFlowServiceImpl implements ApproveFlowService {

    @Resource
    private ApproveFlowDao approveFlowDao;

    @Resource
    private ApproveProcessDao approveProcessDao;

    @Resource
    private ApproveFlowVisibilityDao approveFlowVisibilityDao;

    @Resource
    private ApproveFlowComponent approveFlowComponent;

    @Resource
    private AceDeptService aceDeptService;

    @Resource
    private AceRoleService aceRoleService;

    @Resource
    private AceUserService aceUserService;

    @Resource
    private SystemCateService systemCateService;

    /**
     * 添加审批流
     * @param dto
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult addApproveFlow(ApproveFlowWriteDto dto) {
        OpResult result = null;
        //设置审批流实体数据
        ApproveFlow approveFlow = new ApproveFlow();
        BeanUtils.copyProperties(dto,approveFlow);

        //设置审批用途描述
        ACateSelectDto cateDto = systemCateService.getSystemCateById(dto.getCateId());
        approveFlow.setCateDesc("用于" + cateDto.getCateName()+ "审批");

        //提前生成审批流ID
        long approveFlowId = UniqueIDUtils.getUniqueID();

        //设置可见度数据
        ApproveVisibilityDto visibilityDto = dto.getVisibility();
        visibilityDto.setCompanyId(dto.getCompanyId());
        if(dto.getControlSign().equals(ApproveFlowVisibilityControlEnum.COMPANY_LEVEL.getCode())) {//指定公司
            /*预留，暂时没有此需求*/
        }else if(dto.getControlSign().equals(ApproveFlowVisibilityControlEnum.DEPT_AND_ROLE_LEVEL.getCode())) {//指定部门和角色
            //提取部门和角色Id字符串，并校验
            String roleIdStr = visibilityDto.getRoleId();
            if(StringUtils.isEmpty(visibilityDto.getDepartmentId())){
                result = OpResult.fail(APPROVEFLOW_VISIBILITY_DEPT_EMPTY.getCode(),APPROVEFLOW_VISIBILITY_DEPT_EMPTY.getDesc());
                return result;
            }
            if(StringUtils.isEmpty(visibilityDto.getRoleId())){
                result = OpResult.fail(APPROVEFLOW_VISIBILITY_ROLE_EMPTY.getCode(),APPROVEFLOW_VISIBILITY_ROLE_EMPTY.getDesc());
                return result;
            }
            approveFlowVisibilityDao.addVisibilityDept(visibilityDto,approveFlowId);
            approveFlowVisibilityDao.addVisibilityRole(visibilityDto,approveFlowId);
        }else if(dto.getControlSign().equals(ApproveFlowVisibilityControlEnum.USER_LEVEL.getCode())){
            if(StringUtils.isEmpty(visibilityDto.getUserId())){
                result = OpResult.fail(APPROVEFLOW_VISIBILITY_USER_EMPTY.getCode(),APPROVEFLOW_VISIBILITY_USER_EMPTY.getDesc());
                return result;
            }
            approveFlowVisibilityDao.addVisibilityUser(visibilityDto,approveFlowId);
        }
        //设置审批流可见度描述
        approveFlowComponent.handlerApproveVisibilityDesc(dto,approveFlow);
        //设置审批流程
        List<ApproveProcessWriteDto> processDtos = dto.getApproveProcess();
        long linkHeadId = 0L;
        if(processDtos!=null&&processDtos.size()>0) {
            linkHeadId = approveProcessDao.addApproveProcess(processDtos,approveFlowId);
        }else{
            result = OpResult.fail(APPROVEFLOW_PROCESS_EMPTY.getCode(),APPROVEFLOW_PROCESS_EMPTY.getDesc());
            return result;
        }
        //设置审批流流程链head节点Id
        approveFlow.setApproveProcessId(linkHeadId);
        approveFlowDao.addApproveFlow(approveFlow,approveFlowId);
        result = new OpResult();
        return result;
    }

    /**
     * 查询审批流列表
     * @param dto
     * @return
     */
    @Override
    public CollectionResult<ApproveFlowListDto> selectApproveFlowList(ApproveFlowListSelectDto dto) {
        CollectionResult<ApproveFlowListDto> result = new CollectionResult<>();
        ListDto<ApproveFlowListDto> flowList = approveFlowDao.selectApproveFlowList(dto);

        if(flowList.getResult()){
            result.setCollection(flowList.getList());
            result.setTotal(flowList.getTotal());
            for(ApproveFlowListDto flowListDto:flowList.getList()) {
                flowListDto.setUpdateTimeDesc(TimeUtil.Date2String(flowListDto.getUpdateTime()));
                if(!StringUtils.isEmpty(flowListDto.getVisibilityDesc())) {
                    if (flowListDto.getControlSign().equals(DEPT_AND_ROLE_LEVEL.getCode())) {
                        String[] desc = flowListDto.getVisibilityDesc().split(",");
                        flowListDto.setDepartmentDesc(desc[0]);
                        flowListDto.setRoleDesc(desc[1]);
                    } else {
                        flowListDto.setUserDesc(flowListDto.getVisibilityDesc());
                    }
                    flowListDto.setVisibilityDesc(null);
                }
            }
        }
        return result;
    }

    /**
     * 更新审批流信息
     * @param dto
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult updateApproveFlow(ApproveFlowWriteDto dto) {
        OpResult result = null;
        ApproveFlow approveFlow = new ApproveFlow();
        BeanUtils.copyProperties(dto,approveFlow);

        //设置审批用途描述
        ACateSelectDto cateDto = systemCateService.getSystemCateById(dto.getCateId());
        approveFlow.setCateDesc("用于" + cateDto.getCateName()+ "审批");

        //重新设置可见度数据
        ApproveVisibilityDto visibilityDto = dto.getVisibility();
        visibilityDto.setCompanyId(dto.getCompanyId());
        if(dto.getControlSign().equals(ApproveFlowVisibilityControlEnum.COMPANY_LEVEL.getCode())) {//指定公司
            /*预留，暂时没有此需求*/
        }else if(dto.getControlSign().equals(ApproveFlowVisibilityControlEnum.DEPT_AND_ROLE_LEVEL.getCode())) {//指定部门和角色
            //提取部门和角色Id字符串，并校验
            String roleIdStr = visibilityDto.getRoleId();
            if(StringUtils.isEmpty(visibilityDto.getDepartmentId())){
                result = OpResult.fail(APPROVEFLOW_VISIBILITY_DEPT_EMPTY.getCode(),APPROVEFLOW_VISIBILITY_DEPT_EMPTY.getDesc());
                return result;
            }
            if(StringUtils.isEmpty(visibilityDto.getRoleId())){
                result = OpResult.fail(APPROVEFLOW_VISIBILITY_ROLE_EMPTY.getCode(),APPROVEFLOW_VISIBILITY_ROLE_EMPTY.getDesc());
                return result;
            }
            //删除原来的可见度数据
            approveFlowVisibilityDao.deleteVisibilityByApproveFlowId(dto.getId());
            //重新设置新的可见度部门数据
            approveFlowVisibilityDao.addVisibilityDept(visibilityDto,dto.getId());
            //重新设置新的可见度角色数据
            approveFlowVisibilityDao.addVisibilityRole(visibilityDto,dto.getId());
        }else if(dto.getControlSign().equals(ApproveFlowVisibilityControlEnum.USER_LEVEL.getCode())){
            if(StringUtils.isEmpty(visibilityDto.getUserId())){
                result = OpResult.fail(APPROVEFLOW_VISIBILITY_USER_EMPTY.getCode(),APPROVEFLOW_VISIBILITY_USER_EMPTY.getDesc());
                return result;
            }
            //删除原来的可见度数据
            approveFlowVisibilityDao.deleteVisibilityByApproveFlowId(dto.getId());
            //重新设置新的可见度员工数据
            approveFlowVisibilityDao.addVisibilityUser(visibilityDto,dto.getId());
        }
        //设置审批流可见度描述
        approveFlowComponent.handlerApproveVisibilityDesc(dto,approveFlow);
        //重新设置审批流程
        List<ApproveProcessWriteDto> processDtos = dto.getApproveProcess();
        long linkHeadId = approveFlow.getApproveProcessId();
        if(null == processDtos || 0 == processDtos.size()) {
            result = OpResult.fail(APPROVEFLOW_PROCESS_EMPTY.getCode(),APPROVEFLOW_PROCESS_EMPTY.getDesc());
            return result;
        }else{
            approveProcessDao.deleteByLinkHeadId(linkHeadId);
            linkHeadId = approveProcessDao.addApproveProcess(processDtos,dto.getId());
        }
        //设置审批流流程链head节点Id
        approveFlow.setApproveProcessId(linkHeadId);
        approveFlowDao.updateApproveFlow(approveFlow);
        result = new OpResult();
        return result;
    }

    /**
     * 查询审批流数据详情
     * @param approveFlowId
     * @return
     */
    @Override
    public DtoResult<ApproveFlowGetDto> getApproveFlowById(Long approveFlowId) {
        DtoResult<ApproveFlowGetDto> result = new DtoResult<>();
        ApproveFlowGetDto flowDto = approveFlowDao.getApproveFlowById(approveFlowId);
        if(flowDto!=null){
            //判断控制可见度方式
            if(flowDto.getControlSign().equals(ApproveFlowVisibilityControlEnum.DEPT_AND_ROLE_LEVEL.getCode())){
                //获取审批流的可见范围的部门id列表和角色id列表
                List<Long> deptIds = approveFlowVisibilityDao.selectVisibilityDeptByApproveFlowId(approveFlowId);
                List<Long> roleIds = approveFlowVisibilityDao.selectVisibilityRoleByApproveFlowId(approveFlowId);

                //通过部门id列表查询部门信息列表
                if(deptIds!=null&&deptIds.size()>0){
                    if(deptIds.size()==1&&deptIds.get(0).equals(0L)){
                        flowDto.setDeptIsAll(true);
                    }else {
                        CollectionResult<BaseDto> deptDtos = aceDeptService.selectByIds(deptIds);
                        if (deptDtos.isSuccess()) {
                            flowDto.setDepartments(approveFlowComponent.conventBaseDtoToIdAndNameDto((List<BaseDto>) deptDtos.getCollection()));
                        } else {
                            result.failed(QIFU_PASSPORT_CLIENT_API_ERR.getCode(), QIFU_PASSPORT_CLIENT_API_ERR.getDesc());
                            return result;
                        }
                    }
                }
                //通过角色id列表查询角色信息列表
                if(roleIds!=null&&roleIds.size()>0){
                    if(roleIds.size()==1&&roleIds.get(0).equals(0L)){
                        flowDto.setRoleIsAll(true);
                    }else {
                        CollectionResult<BaseDto> roleDtos = aceRoleService.selectRoleListByIdList(roleIds);
                        if (roleDtos.isSuccess()) {
                            flowDto.setRoles(approveFlowComponent.conventBaseDtoToIdAndNameDto((List<BaseDto>) roleDtos.getCollection()));
                        } else {
                            result.failed(QIFU_PASSPORT_CLIENT_API_ERR.getCode(), QIFU_PASSPORT_CLIENT_API_ERR.getDesc());
                            return result;
                        }
                    }
                }
            }else if(flowDto.getControlSign().equals(ApproveFlowVisibilityControlEnum.USER_LEVEL.getCode())){
                //获取审批流的可见范围的员工id列表,通过员工id列表获取实体列表
                List<Long> userIds = approveFlowVisibilityDao.selectVisibilityUserByApproveFlowId(approveFlowId);
                CollectionResult<AceUserApproveDto> userDtos = aceUserService.selectUserListByIdList(flowDto.getCompanyId(),userIds);
                flowDto.setUsers(approveFlowComponent.conventAceUserApproveDtoToIdAndNameDto((List<AceUserApproveDto>) userDtos.getCollection()));
            }else{
                //获取到了错诶的控制可见度方式状态，返回失败
                result.failed(APPROVEFLOW_VISIBILITY_CONTROL_SIGN_PARAMETER_ERROR.getCode(),APPROVEFLOW_VISIBILITY_CONTROL_SIGN_PARAMETER_ERROR.getDesc());
                return result;
            }

            //审批流程链表设置
            //查询审批流程链表数据
            LinkedList<ApproveProcess> processes = approveProcessDao.selectApproveProcess(flowDto.getApproveProcessId());
            LinkedList<ApproveProcessReadDto> processReadDtos = new LinkedList<>();
            ApproveProcessReadDto dto = null;
            ApproveProcessReadDto.ApproveModuleDto moduleDto = null;
            for(ApproveProcess entity:processes){
                dto = new ApproveProcessReadDto();
                moduleDto = new ApproveProcessReadDto.ApproveModuleDto();
                dto.setApprove(moduleDto);
                dto.setApproveType(entity.getApproveType());
                moduleDto.setId(entity.getId());
                moduleDto.setNextId(entity.getNextId());
                moduleDto.setApproveFlowId(entity.getApproveFlowId());
                moduleDto.setApproveTypeName(entity.getApproveTypeName());
                //指定层级
                if(entity.getApproveType().equals(APPOINT.getCode())) {
                    moduleDto.setApproveDept(entity.getApproveDept());
                    moduleDto.setApproveProcessDesc("指定第"+entity.getApproveDept()+"级");
                }
                //连续多级
                if(entity.getApproveType().equals(CONTINUITY.getCode())) {
                    moduleDto.setApproveEnd(entity.getApproveEnd());
                    moduleDto.setApproveProcessDesc("向上不超过第"+entity.getApproveEnd()+"级");
                }
                //设置审批空缺处理方式
                if(entity.getApproveType().equals(APPOINT.getCode())||entity.getApproveType().equals(CONTINUITY.getCode())) {
                    moduleDto.setVacancy(entity.getVacancy());
                }

                if(null != entity.getApproveMode()) {
                    moduleDto.setApproveMode(entity.getApproveMode());
                    moduleDto.setApproveModeDesc(ApproveFlowModeEnum.getByCode(entity.getApproveMode()).getDesc());
                }
                //角色相关
                if(entity.getApproveType().equals(APPOINT.getCode())||entity.getApproveType().equals(CONTINUITY.getCode())||entity.getApproveType().equals(ROLE.getCode())) {
                    moduleDto.setRoleType(entity.getRoleType());
                    //设置角色展示列表
                    List<Long> roleIds = approveFlowComponent.conventStringIdsToLongIds(entity.getApproveRole());
                    CollectionResult<BaseDto> roleDtos = aceRoleService.selectRoleListByIdList(roleIds);
                    if (roleDtos.isSuccess()) {
                        List<BaseDto> roles = (List<BaseDto>) roleDtos.getCollection();
                        moduleDto.setApproveRole(approveFlowComponent.conventBaseDtoToIdAndNameDto(roles));
                        StringBuilder builder = new StringBuilder();
                        if(moduleDto.getApproveProcessDesc()!=null){
                            builder.append(moduleDto.getApproveProcessDesc()).append("<br>");
                        }
                        builder.append("角色(").append(AND.getCate().equals(entity.getRoleType())?"与":"或").append("):");
                        for(int i = 0;i<roles.size();i++){
                            builder.append(roles.get(i).getName()).append(" ");
                        }

                        moduleDto.setApproveProcessDesc(builder.toString());
                    }
                }
                //指定员工
                if(entity.getApproveType().equals(EMPLOYEE.getCode())) {
                    //设置员工展示列表
                    List<Long> userIds = approveFlowComponent.conventStringIdsToLongIds(entity.getApproveEmployee());
                    CollectionResult<AceUserApproveDto> userDtos = aceUserService.selectUserListByIdList(flowDto.getCompanyId(),userIds);
                    if (userDtos.isSuccess()) {
                        List<AceUserApproveDto> users = (List<AceUserApproveDto>) userDtos.getCollection();
                        moduleDto.setApproveEmployee(approveFlowComponent.conventAceUserApproveDtoToIdAndNameDto(users));
                        StringBuilder builder = new StringBuilder();
                        for(int i = 0;i<(users.size()>8?8:users.size());i++){
                            builder.append(users.get(i).getName()).append(" ");
                        }
                        if(users.size()>8){
                            builder.append("等8人");
                        }

                        moduleDto.setApproveProcessDesc(builder.toString());
                    }
                }
                processReadDtos.add(dto);
            }
            flowDto.setApproveProcess(processReadDtos);
            result.setDto(flowDto);
        }
        return result;
    }

    /**
     * 更新审批流状态
     * @param approveFlowId
     * @param state
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult updateApproveFlowState(Long approveFlowId, Byte state) {
        OpResult result = new OpResult();
        int i = approveFlowDao.updateApproveFlowState(approveFlowId, state);
        if(i>0) {
            return result;
        }else{
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * 删除审批流(软删除)
     * @param approveFlowId
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public OpResult deleteApprovalFlowById(Long approveFlowId) {
        OpResult result = new OpResult();
        int i = approveFlowDao.deleteApprovalFlowById(approveFlowId);
        if(i>0) {
            return result;
        }else{
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * 对外提供，根据审批流Id获取审批流部分信息
     * （ACE下单系统）
     * @param approveFlowId
     * @return
     */
    @Override
    public DtoResult<ApproveFlowExternalGetDto> getExternalApproveFlowById(Long approveFlowId) {
        DtoResult<ApproveFlowExternalGetDto> result = new DtoResult<>();
        ApproveFlowExternalGetDto dto = approveFlowDao.getExternalApproveFlowById(approveFlowId);
        result.setDto(dto);
        return result;
    }

    /**
     * 根据当前登录用户获取审批流列表（审批服务发起审批使用）
     * @param userId
     * @return
     */
    @Override
    public CollectionResult<ApproveLaunchListDto> selectApproveList(Long userId,Long companyId) {
        CollectionResult<ApproveLaunchListDto> result = new CollectionResult<>();
        if(userId==null||companyId==null){
            result.setSuccess(false);
            result.setCode(APICode.PARAM_EMPTY.getCode());
            result.setMessage(APICode.PARAM_EMPTY.getDesc());
            return result;
        }
        DtoResult<AceUserDto> userResult = aceUserService.getUserInfoById(companyId,userId);
        if(userResult.isSuccess()){
            AceUserDto userDto = userResult.getDto();
            long[] deptDtoList = approveFlowComponent.getIdsByListDto(userDto.getDeptDtoList());
            long[] roleDtoList = approveFlowComponent.getIdsByListDto(userDto.getRoleDtoList());

            HashSet<Long> selectIds = new HashSet<>();
            selectIds.add(0L);
            HashSet<Long> deptFlowIds = approveFlowVisibilityDao.selectApproveFlowIdsByDeptIds(userDto.getCustomerId(), deptDtoList);
            HashSet<Long> roleFlowIds = approveFlowVisibilityDao.selectApproveFlowIdsByRoleIds(userDto.getCustomerId(), roleDtoList);
            if((deptFlowIds!=null&&deptFlowIds.size()>0)&&(roleFlowIds!=null&&roleFlowIds.size()>0)){
                deptFlowIds.retainAll(roleFlowIds);
                selectIds.addAll(deptFlowIds);
            }
            /*else if((deptFlowIds!=null&&deptFlowIds.size()>0)&&(roleFlowIds==null||roleFlowIds.size()==0)){
                selectIds.addAll(deptFlowIds);
            }else if((deptFlowIds==null||deptFlowIds.size()==0)&&(roleFlowIds!=null&&roleFlowIds.size()>0)){
                selectIds.addAll(roleFlowIds);
            }*/

            HashSet<Long> userFlowIds = approveFlowVisibilityDao.selectApproveFlowIdsByUserId(userDto.getCustomerId(), userId);
            if(userFlowIds!=null&&userFlowIds.size()>0){
                selectIds.addAll(userFlowIds);
            }

            List<ApproveLaunchListDto> listDtos = approveFlowDao.selectApproveList(selectIds, userDto.getIsTest().getCode().byteValue());
            result.setCollection(listDtos);
        }
        return result;
    }

    /**
     * 通过审批流可见部门，过滤审批人的部门，将审批人不可见的部门过滤掉
     * @param approveFlowId
     * @param deptIds
     * @return
     */
    @Override
    public CollectionResult<Long> filterDeptByApproveFlowId(Long approveFlowId, List<Long> deptIds) {
        CollectionResult<Long> result = new CollectionResult<>();
        ApproveFlowGetDto approveFlow = approveFlowDao.getApproveFlowById(approveFlowId);
        if(approveFlow.getControlSign().equals(ApproveFlowVisibilityControlEnum.DEPT_AND_ROLE_LEVEL.getCode())){
            List<Long> depts = approveFlowVisibilityDao.selectVisibilityDeptByApproveFlowId(approveFlowId);
            if(depts.size()==1&&depts.get(0).equals(0L)){
                result.setCollection(deptIds);
            }else{
                deptIds.retainAll(depts);
                if(deptIds.size()==0){
                    result.setSuccess(false);
                    result.setCode(APPROVEFLOW_VISIBILITY_DEPT_ERR.getCode());
                    result.setMessage(APPROVEFLOW_VISIBILITY_DEPT_ERR.getDesc());
                    return result;
                }
                result.setCollection(deptIds);
            }
        }else{
            result.setCollection(deptIds);
        }
        return result;
    }


    /**************************************************************************************MOCK方法**********************************************************************************/

    /**
     * 获取用户信息Mock方法
     * @return
     */
    private DtoResult<AceUserDto> mockAceUserDto(){
        DtoResult<AceUserDto> userResult = new DtoResult<>();
        AceUserDto userDto = new AceUserDto();
        userDto.setCustomerId(1L);
        List<BaseDto> deptDtoList = new ArrayList<>();
        BaseDto deptDto = new BaseDto();
        deptDto.setId(110L);
        deptDtoList.add(deptDto);
        List<BaseDto> roleDtoList = new ArrayList<>();
        BaseDto roleDto = new BaseDto();
        roleDto.setId(10L);
        roleDtoList.add(roleDto);
        userDto.setDeptDtoList(deptDtoList);
        userDto.setRoleDtoList(roleDtoList);
        userResult.setDto(userDto);
        userDto.setIsTest(AceTestEnum.NOTTEST);
        return userResult;
    }

    /**
     * 部门列表查询Mock方法
     * @return
     */
    private CollectionResult mockDeptResult(){
        CollectionResult<BaseDto> deptResult = new CollectionResult<>();
        List<BaseDto> mockDepr = new ArrayList<>();
        BaseDto baseDto = new BaseDto();
        baseDto.setId(1L);
        baseDto.setName("产品部");
        mockDepr.add(baseDto);
        BaseDto baseDto1 = new BaseDto();
        baseDto1.setId(2L);
        baseDto1.setName("平台部");
        mockDepr.add(baseDto1);
        BaseDto baseDto2 = new BaseDto();
        baseDto2.setId(3L);
        baseDto2.setName("用户部");
        mockDepr.add(baseDto2);
        deptResult.setCollection(mockDepr);
        return deptResult;
    }
    /**
     * 部门列表查询Mock方法
     * @return
     */
    private CollectionResult mockDeptResult(List<Long> deptIds){
        CollectionResult<BaseDto> deptResult = new CollectionResult<>();
        List<BaseDto> mockDepr = new ArrayList<>();
        BaseDto baseDto = null;
        for(Long id:deptIds){
            baseDto = new BaseDto();
            baseDto.setId(id);
            baseDto.setName("部门"+id);
            mockDepr.add(baseDto);
        }
        deptResult.setCollection(mockDepr);
        return deptResult;
    }

    /**
     * 角色列表查询Mock方法
     * @return
     */
    private CollectionResult mockRoleResult(){
        CollectionResult<BaseDto> roleResult = new CollectionResult<>();
        List<BaseDto> mockDepr = new ArrayList<>();
        BaseDto baseDto = new BaseDto();
        baseDto.setId(1L);
        baseDto.setName("主管");
        mockDepr.add(baseDto);
        BaseDto baseDto1 = new BaseDto();
        baseDto1.setId(2L);
        baseDto1.setName("行政");
        mockDepr.add(baseDto1);
        BaseDto baseDto2 = new BaseDto();
        baseDto2.setId(3L);
        baseDto2.setName("开发");
        mockDepr.add(baseDto2);
        roleResult.setCollection(mockDepr);
        return roleResult;
    }
    /**
     * 角色列表查询Mock方法
     * @return
     */
    private CollectionResult mockRoleResult(List<Long> roleIds){
        CollectionResult<BaseDto> roleResult = new CollectionResult<>();
        List<BaseDto> mockRole = new ArrayList<>();
        BaseDto baseDto = null;
        for(Long id:roleIds){
            baseDto = new BaseDto();
            baseDto.setId(id);
            baseDto.setName("角色"+id);
            mockRole.add(baseDto);
        }
        roleResult.setCollection(mockRole);
        return roleResult;
    }

    /**
     * 用户列表查询Mock方法
     * @return
     */
    private CollectionResult mockUserResult(){
        CollectionResult<AceUserApproveDto> userResult = new CollectionResult<>();
        List<AceUserApproveDto> mockDepr = new ArrayList<>();
        AceUserApproveDto baseDto = new AceUserApproveDto();
        baseDto.setId(1L);
        baseDto.setName("小胡");
        mockDepr.add(baseDto);
        AceUserApproveDto baseDto1 = new AceUserApproveDto();
        baseDto1.setId(2L);
        baseDto1.setName("小魏");
        mockDepr.add(baseDto1);
        AceUserApproveDto baseDto2 = new AceUserApproveDto();
        baseDto2.setId(3L);
        baseDto2.setName("小王");
        mockDepr.add(baseDto2);
        userResult.setCollection(mockDepr);
        return userResult;
    }

    /**
     * 用户列表查询Mock方法
     * @return
     */
    private CollectionResult mockUserResult(List<Long> userIds){
        CollectionResult<AceUserApproveDto> userResult = new CollectionResult<>();
        List<AceUserApproveDto> mockUser = new ArrayList<>();
        AceUserApproveDto baseDto = null;
        for(Long id:userIds){
            baseDto = new AceUserApproveDto();
            baseDto.setId(id);
            baseDto.setName("用户"+id);
            mockUser.add(baseDto);
        }
        userResult.setCollection(mockUser);
        return userResult;
    }
}
