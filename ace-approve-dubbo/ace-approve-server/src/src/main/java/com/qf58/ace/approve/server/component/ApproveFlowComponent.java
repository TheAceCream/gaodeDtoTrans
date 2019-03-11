package com.qf58.ace.approve.server.component;

import com.google.gson.Gson;
import com.qf58.ace.approve.dto.ApproveFlowWriteDto;
import com.qf58.ace.approve.dto.ApproveProcedureCreateDto;
import com.qf58.ace.approve.dto.ApproveVisibilityDto;
import com.qf58.ace.approve.dto.IdAndNameDto;
import com.qf58.ace.approve.entity.ApproveFlow;
import com.qf58.ace.approve.entity.ApproveProcedure;
import com.qf58.ace.approve.entity.ApproveProcess;
import com.qf58.ace.approve.enums.*;
import com.qf58.ace.approve.server.dao.ApproveProcedureDao;
import com.qf58.ace.approve.server.dao.ApproveProcessDao;
import com.qf58.ace.code.APICode;
import com.qf58.ace.util.UniqueIDUtils;
import com.qf58.acepassport.service.AceDeptService;
import com.qf58.acepassport.service.AceRoleService;
import com.qf58.client.support.CollectionResult;
import com.qf58.client.support.DtoResult;
import com.qf58.acepassport.dto.*;
import com.qf58.acepassport.enums.AceApproveRelEnum;
import com.qf58.acepassport.service.AceUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.APPOINT;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.CONTINUITY;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.EMPLOYEE;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.ROLE;
import static com.qf58.ace.approve.enums.ApproveFlowProcessTypeEnum.MYSELF;

import static com.qf58.ace.approve.enums.ApproveFlowVisibilityControlEnum.DEPT_AND_ROLE_LEVEL;
/**
 * 审批流功能组件包
 *
 * @author: HYC
 * @description:
 * @time: 2018年11月19日
 * @modifytime:
 */
@Component
public class ApproveFlowComponent {

    private static final Logger logger = LoggerFactory.getLogger(ApproveFlowComponent.class);

    @Resource
    private ApproveProcessDao approveProcessDao;

    @Resource
    private AceUserService aceUserService;

    @Resource
    private AceDeptService aceDeptService;

    @Resource
    private AceRoleService aceRoleService;

    /**
     * 根据部门和审批流ID以及审批ID生成审批过程（审批服务使用）
     * @param dto
     * @return
     */
    public List<ApproveProcedure> createApproveProcedure(ApproveProcedureCreateDto dto){
        List<ApproveProcedure> result = new ArrayList<>();
        ApproveProcedure procedure = null;
        //查询审批流审批流程
        LinkedList<ApproveProcess> approveProcesses = approveProcessDao.selectApproveProcessByFlowId(dto.getApproveFlowId());
        //获取发起审批人人信息，在审批过程中设置发起审批人信息
        DtoResult<AceUserDto> userResult = aceUserService.getUserInfoById(dto.getCompanyId(),dto.getOriginator());
        if(userResult.isSuccess()&&!userResult.isEmpty()) {
            ApproveProcedure startMan = assembleApproveProcedure(dto,userResult.getDto(), null, (byte)0, (byte) 1, ApproveProcedureStatusEnum.LAUNCH_APPROVE.getCode());
            startMan.setApproveTime(new Date());
            result.add(startMan);
        }else{
            throw new RuntimeException(APICode.UNKNOW_PEOPLE.getDesc());
        }
        //遍历审批流程，并按照每个流程的类型确定审批人，按组号生成审批过程，人员空缺添加空数据填补
        byte group = 1;
        for(ApproveProcess approveProcess:approveProcesses){
            Byte approveType = approveProcess.getApproveType();

            byte state = group==1?ApproveProcedureStatusEnum.APPROVEING.getCode():ApproveProcedureStatusEnum.WAIT_APPROVE.getCode();
            if(APPOINT.getCode().equals(approveType)){
                CollectionResult<AceUserApproveDto> userDtos = aceUserService.selectByAppointLvl(assembleApproveSearchDto(dto,approveProcess));
                //判断空缺处理
                if(userDtos.isSuccess()&&userDtos.isEmpty()){
                    //如果该层级人员空缺，尝试获取指定层级上一层级人员审批
                    if(ApproveFlowVacancyEnum.AVAILABLE.getCode().equals(approveProcess.getVacancy())) {
                        if (userDtos.isSuccess() && userDtos.isEmpty()) {
                            approveProcess.setApproveDept(approveProcess.getApproveDept() + 1);
                            userDtos = aceUserService.selectByAppointLvl(assembleApproveSearchDto(dto, approveProcess));
                        }
                    }
                }
                if(!userDtos.isSuccess()){
                    throw new RuntimeException(APICode.QIFU_PASSPORT_CLIENT_API_ERR.getDesc() + " : " + userDtos.getMessage());
                }
                //将查询到的审批人生成审批过程
                handlerUserListDto(userDtos,result,dto,approveProcess,group,state);
            }else if(CONTINUITY.getCode().equals(approveType)){
                DtoResult<AceUserMultiLvlListDto> userDtos = aceUserService.getByMultiLvl(assembleApproveSearchDto(dto,approveProcess));
                if(userDtos.isSuccess()){
                    AceUserMultiLvlListDto userLink = userDtos.getDto();
                    byte linkSize = getAceUserMultiLvlListSize(userLink,(byte)1);
                    //将所有审批都设置为等待审批，后续处理特殊情况（针对连续审批为第一组审批），其余情况都为等待审批
                    //返回连续层级中最低层级审批人个数，用于处理特殊情况审批状态
                    int lastsize = assembleApproveProcedure(dto,approveProcess,group,userLink,result,linkSize,ApproveProcedureStatusEnum.WAIT_APPROVE.getCode(),approveProcess.getApproveEnd(),approveProcess.getApproveEnd());
                    //针对连续审批为第一组审批时，将第一审批层级审批状态设置为审批中
                    if(group==1){
                        for(int i = result.size()-1;i>=result.size()-lastsize;i--){
                            ApproveProcedure a = result.get(i);
                            a.setStatus(ApproveProcedureStatusEnum.APPROVEING.getCode());
                        }
                    }
                }else{
                    throw new RuntimeException(APICode.QIFU_PASSPORT_CLIENT_API_ERR.getDesc() + " : " + userDtos.getMessage());
                }
            }else if(EMPLOYEE.getCode().equals(approveType)){
                CollectionResult<AceUserApproveDto> userDtos = aceUserService.selectUserListByIdList(dto.getCompanyId(), conventStringIdsToLongIds(approveProcess.getApproveEmployee()));
                if(!userDtos.isSuccess()){
                    throw new RuntimeException(APICode.QIFU_PASSPORT_CLIENT_API_ERR.getDesc() + " : " + userDtos.getMessage());
                }
                //将查询到的审批人生成审批过程
                handlerUserListDto(userDtos,result,dto,approveProcess,group,state);
            }else if(ROLE.getCode().equals(approveType)){
                CollectionResult<AceUserApproveDto> userDtos = aceUserService.selectUserListByRoleList(dto.getCompanyId(),conventStringIdsToLongIds(approveProcess.getApproveRole()),AceApproveRelEnum.getByCode(approveProcess.getRoleType().intValue()));
                if(!userDtos.isSuccess()){
                    throw new RuntimeException(APICode.QIFU_PASSPORT_CLIENT_API_ERR.getDesc() + " : " + userDtos.getMessage());
                }
                //将查询到的审批人生成审批过程
                handlerUserListDto(userDtos,result,dto,approveProcess,group,state);
            }else if(MYSELF.getCode().equals(approveType)){
                DtoResult<AceUserDto> userDtoResult = aceUserService.getUserInfoById(dto.getCompanyId(), dto.getOriginator());
                if (!userDtoResult.isSuccess()) {
                    throw new RuntimeException(APICode.QIFU_PASSPORT_CLIENT_API_ERR.getDesc() + " : " + userDtoResult.getMessage());
                }
                AceUserDto userDto = userDtoResult.getDto();
                ApproveProcedure myself = assembleApproveProcedure(dto, userDto, approveProcess, group, (byte) 1, ApproveFlowModeEnum.SYSTEM_AUTO_PASS.getCode().equals(approveProcess.getApproveMode())?ApproveProcedureStatusEnum.SYSTEM_AUTO_PASS.getCode():state);
                if(ApproveFlowModeEnum.SYSTEM_AUTO_PASS.getCode().equals(approveProcess.getApproveMode())) {
                    myself.setApproveTime(new Date());
                }
                result.add(myself);
            }
            group++;
        }
        return result;
    }

    /**
     * 设置审批流程审批人
     * @param userDtos
     * @param result
     * @param dto
     * @param approveProcess
     * @param group
     * @param state
     */
    private void handlerUserListDto(CollectionResult<AceUserApproveDto> userDtos,List<ApproveProcedure> result,ApproveProcedureCreateDto dto,ApproveProcess approveProcess,byte group,byte state){
        if(userDtos.isSuccess()){
            List<AceUserApproveDto> userlist = (List<AceUserApproveDto>)userDtos.getCollection();
            if(userlist!=null&&userlist.size()>0) {
                for (AceUserApproveDto userDto : userDtos.getCollection()) {
                    result.add(assembleApproveProcedure(dto,userDto,approveProcess,group,(byte) 1,state));
                }
            }else{
                //无审批人员添加空审批过程数据
                result.add(assembleApproveProcedure(dto,approveProcess,group,(byte)1));
            }
        }
    }

    /**
     * 设置可见度描述
     * @param dto
     * @param entity
     */
    public void handlerApproveVisibilityDesc(ApproveFlowWriteDto dto, ApproveFlow entity){
        ApproveVisibilityDto visibilityDto = dto.getVisibility();
        if(visibilityDto==null){
            return;
        }
        if(dto.getControlSign().equals(DEPT_AND_ROLE_LEVEL.getCode())){
            List<Long> deptIds = conventStringIdsToLongIds(visibilityDto.getDepartmentId());
            List<Long> roleIds = conventStringIdsToLongIds(visibilityDto.getRoleId());

            if(deptIds!=null&&deptIds.size()>0){
                if(deptIds.size()==1&&deptIds.get(0)==0L){
                    entity.setVisibilityDesc("部门:全部部门");
                }else{
                    CollectionResult<BaseDto> deptResult = aceDeptService.selectByIds(deptIds);
                    if(deptResult.isSuccess()&&deptResult.getCollection()!=null){
                        List<BaseDto> depts = (List)deptResult.getCollection();
                        StringBuilder departmentDesc = new StringBuilder();
                        departmentDesc.append("部门:");
                        for(int i = 0; i < (depts.size()>3?3:depts.size()); i++){
                            departmentDesc.append(depts.get(i).getName()).append(" ");
                        }
                        if(depts.size()>3) {
                            departmentDesc.append("等"+depts.size()+"个部门");
                        }
                        entity.setVisibilityDesc(departmentDesc.toString());
                    }
                }
            }
            if(roleIds!=null&&roleIds.size()>0){
                if(roleIds.size()==1&&roleIds.get(0)==0L){
                    entity.setVisibilityDesc(entity.getVisibilityDesc()+",角色:全部角色");
                }else{
                    CollectionResult<BaseDto> roleResult = aceRoleService.selectRoleListByIdList(roleIds);
                    if(roleResult.isSuccess()&&roleResult.getCollection()!=null){
                        List<BaseDto> roles = (List)roleResult.getCollection();
                        StringBuilder roleDesc = new StringBuilder();
                        roleDesc.append("角色:");
                        for(int i = 0; i < (roles.size()>3?3:roles.size()); i++){
                            roleDesc.append(roles.get(i).getName()).append(" ");
                        }
                        if(roles.size()>3) {
                            roleDesc.append("等"+roles.size()+"个角色");
                        }
                        entity.setVisibilityDesc(entity.getVisibilityDesc()+","+roleDesc.toString());
                    }
                }
            }
        }else{
            List<Long> userIds = conventStringIdsToLongIds(visibilityDto.getUserId());
            if(userIds!=null&&userIds.size()>0){
                CollectionResult<AceUserApproveDto> userResult = aceUserService.selectUserListByIdList(dto.getCompanyId(),userIds);
                if(userResult.isSuccess()&&userResult.getCollection()!=null){
                    List<AceUserApproveDto> users = (List)userResult.getCollection();
                    StringBuilder userDesc = new StringBuilder();
                    userDesc.append("员工:");
                    for(int i = 0; i < (users.size()>5?5:users.size()); i++){
                        userDesc.append(users.get(i).getName()).append(" ");
                    }
                    if(users.size()>5) {
                        userDesc.append("等"+users.size()+"个员工");
                    }
                    entity.setVisibilityDesc(userDesc.toString());
                }
            }
        }
    }

    /**
     * 提取Dto集合中的ID
     * @param deptDtos
     * @return
     */
    public long[] getIdsByListDto(List<BaseDto> deptDtos){
        if(deptDtos!=null){
            int deptSize = deptDtos.size();
            long[] ids = new long[deptSize+1];
            BaseDto temp = null;
            for(int i = 0;i<deptSize;i++){
                temp = deptDtos.get(i);
                if(temp.getId()!=null) {
                    ids[i] = temp.getId();
                }
            }
            ids[deptSize] = 0L;
            return ids;
        }

        return new long[]{0L};
    }

    /**
     * BaseDto转IdAndNameDto
     * @param baseDtos
     * @return
     */
    public List<IdAndNameDto> conventBaseDtoToIdAndNameDto(List<BaseDto> baseDtos){
        List<IdAndNameDto> result = new ArrayList<>();
        if(baseDtos!=null&&baseDtos.size()>0){
            IdAndNameDto idAndNameDto = null;
            for(BaseDto baseDto:baseDtos){
                idAndNameDto = new IdAndNameDto(baseDto.getId(),baseDto.getName());
                result.add(idAndNameDto);
            }
        }
        return result;
    }

    /**
     * AceUserApproveDto转IdAndNameDto
     * @param baseDtos
     * @return
     */
    public List<IdAndNameDto> conventAceUserApproveDtoToIdAndNameDto(List<AceUserApproveDto> baseDtos){
        List<IdAndNameDto> result = new ArrayList<>();
        if(baseDtos!=null&&baseDtos.size()>0){
            IdAndNameDto idAndNameDto = null;
            for(AceUserApproveDto baseDto:baseDtos){
                idAndNameDto = new IdAndNameDto(baseDto.getId(),baseDto.getName());
                result.add(idAndNameDto);
            }
        }
        return result;
    }

    /**
     * 字符串id转id集合
     * @param ids
     * @return
     */
    public List<Long> conventStringIdsToLongIds(String ids){
        List<Long> result = new ArrayList<>();
        if(!StringUtils.isEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String idStr : idArr) {
                result.add(Long.parseLong(idStr));
            }
        }
        return result;
    }

    /**
     * 连续多级审批过程实体类组装
     * @param dto 审批过程创建入参dto
     * @param approveProcess 审批流程
     * @param group 过程分组
     * @param userLink 人员入参
     * @param result 过程结果list
     * @param watchLevel 同一分组中 观看等级
     * @param state 审批状态
     * @param approveEnd 连续多级开始部门（从高层级----->到低层级）
     * @param deptLength 部门连续长度
     * 一开始deptLength = approveEnd = watchLevel ，后续逻辑递归后，每次递归approveEnd 和 watchLevel 依次减一，但始终approveEnd = watchLevel
     */
    private int assembleApproveProcedure(ApproveProcedureCreateDto dto,ApproveProcess approveProcess,byte group,AceUserMultiLvlListDto userLink,List<ApproveProcedure> result,byte watchLevel,byte state,int approveEnd,int deptLength){
        byte thisWatchLevel = watchLevel;
        int deptEnd = approveEnd;
        AceUserMultiLvlDto userMultiLvlDto = null;
        if(null!=userLink&&(userMultiLvlDto = userLink.getUserMultiLvlDto())!=null){
            List<AceUserApproveDto> approveDtos = userMultiLvlDto.getUserList();
            if(approveDtos!=null&&approveDtos.size()>0){
                for (AceUserApproveDto userDto : approveDtos) {
                    result.add(assembleApproveProcedure(dto,userDto,approveProcess,group, thisWatchLevel,state));
                }
            }else{
                //如果采用空缺处理，则进行空缺处理
                if(ApproveFlowVacancyEnum.AVAILABLE.getCode().equals(approveProcess.getVacancy())){
                    approveProcess.setApproveType(ApproveFlowProcessTypeEnum.APPOINT.getCode());
                    approveProcess.setApproveDept(deptEnd+1);
                    CollectionResult<AceUserApproveDto> upLevelUserDtos = aceUserService.selectByAppointLvl(assembleApproveSearchDto(dto,approveProcess));
                    //此处查询出错会影响审批后续关键流程，此处需要记录日志
                    if(!upLevelUserDtos.isSuccess()){
                        logger.error("空缺处理，查询上级审批人员异常，异常信息： "+upLevelUserDtos.getCode()+" "+upLevelUserDtos.getMessage()+" 参数："+new Gson().toJson(assembleApproveSearchDto(dto,approveProcess)));
                        return 0;
                    }
                    //将查询数据同步到approveDtos变量中，目的是让返回结果计算方便
                    approveDtos = (ArrayList<AceUserApproveDto>)upLevelUserDtos.getCollection();
                    //如果上一级人员也空缺，则设置审批人空缺
                    if(upLevelUserDtos.isSuccess()&&upLevelUserDtos.isEmpty()){
                        //审批人空缺，添加空缺审批过程
                        result.add(assembleApproveProcedure(dto, approveProcess, group, thisWatchLevel));
                    }else if(deptLength == deptEnd){
                        //如果当前处理的层级为查询的最高层级，则需要将查询的上一层级数据设置到结果中（例如查询连续3级，第3层级人员为空，那我此时upLevelUserDtos为第4层级数据，需要添加处理）
                        for (AceUserApproveDto userDto : upLevelUserDtos.getCollection()) {
                            result.add(assembleApproveProcedure(dto, userDto, approveProcess, group, thisWatchLevel, state));
                        }
                    }else{
                        //如果上一级人员不空缺，则不作处理，防止重复审批
                        //(由于当前审批人员不是查询最高层级，那么在查询范围内的审批层级之前都有设置了，例如第2层级人员空缺，应该找第3层级，那么之前第3层级人员已经设置过，不需要再重复设置)
                        thisWatchLevel+=1;//防止发生WatchLevel不连续的情况
                    }
                }else {
                    //审批人空缺，添加空缺审批过程
                    result.add(assembleApproveProcedure(dto, approveProcess, group, thisWatchLevel));
                }
            }
            if(userLink.getLeafNode()){
                return assembleApproveProcedure(dto,approveProcess,group,userLink.getChildren(),result, (byte) (thisWatchLevel-1),state,(deptEnd-1),deptEnd);
            }else{
                return userMultiLvlDto.getUserList()==null?0:approveDtos.size();
            }
        }
        return 0;
    }

    /**
     * 获取连续多级用户链表长度
     * @param userLink
     * @param size
     * @return
     */
    private byte getAceUserMultiLvlListSize(AceUserMultiLvlListDto userLink,byte size){
        if(null != userLink){
            if(userLink.getLeafNode()){
                size = getAceUserMultiLvlListSize(userLink.getChildren(), (byte) (size+1));
            }
            return size;
        }
        return 0;
    }


    /**
     * 指定一级审批过程实体类组装
     * @param dto
     * @param userDto
     * @param approveProcess
     * @param group
     * @param watchLevel
     * @return
     */
    private ApproveProcedure assembleApproveProcedure(ApproveProcedureCreateDto dto,AceUserDto userDto,ApproveProcess approveProcess,byte group,byte watchLevel,byte state){
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        ApproveProcedure procedure = new ApproveProcedure();
        procedure.setId(UniqueIDUtils.getUniqueID());
        procedure.setApproveId(dto.getApproveId());
        procedure.setApproverId(userDto.getId());
        procedure.setApprover(userDto.getUserName());
        procedure.setApproverDuty(userDto.getTitle());
        procedure.setGroups(group);
        if(approveProcess!=null) {
            procedure.setLot(approveProcess.getApproveMode() == null ? ApproveProcedureLotEnum.OR_SIGN.getCode() : (byte) (approveProcess.getApproveMode() + 1));
            procedure.setType((byte) (approveProcess.getApproveType() + 1));
        }
        procedure.setWatchLevel(watchLevel);
        procedure.setCreateTime(initTime);
        procedure.setIsTest(dto.getIsTest());
        procedure.setStatus(state);
        procedure.setInitiator(dto.getInitiator());
        procedure.setTitle(dto.getTitle());
        return procedure;
    }

    /**
     * 指定一级审批过程实体类组装
     * @param dto
     * @param userDto
     * @param approveProcess
     * @param group
     * @param watchLevel
     * @return
     */
    private ApproveProcedure assembleApproveProcedure(ApproveProcedureCreateDto dto,AceUserApproveDto userDto,ApproveProcess approveProcess,byte group,byte watchLevel,byte state) {
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        ApproveProcedure procedure = new ApproveProcedure();
        procedure.setId(UniqueIDUtils.getUniqueID());
        procedure.setApproveId(dto.getApproveId());
        procedure.setApproverId(userDto.getId());
        procedure.setApprover(userDto.getName());
        procedure.setApproverDuty(userDto.getTitle());
        procedure.setGroups(group);
        procedure.setLot(approveProcess.getApproveMode()==null? ApproveProcedureLotEnum.OR_SIGN.getCode():(byte)(approveProcess.getApproveMode()+1));
        procedure.setType((byte)(approveProcess.getApproveType()+1));
        procedure.setWatchLevel(watchLevel);
        procedure.setCreateTime(initTime);
        procedure.setIsTest(dto.getIsTest());
        procedure.setStatus(state);
        procedure.setInitiator(dto.getInitiator());
        procedure.setTitle(dto.getTitle());
        return procedure;
    }

    /**
     * 审批用户搜索Dto组装
     * @param dto
     * @param approveProcess
     * @return
     */
    private ApproveSearchDto assembleApproveSearchDto(ApproveProcedureCreateDto dto,ApproveProcess approveProcess){
        ApproveSearchDto approveSearchDto = new ApproveSearchDto();
        approveSearchDto.setCompanyId(dto.getCompanyId());
        approveSearchDto.setDeptId(dto.getDeptId());
        approveSearchDto.setLevel(APPOINT.getCode().equals(approveProcess.getApproveType())?approveProcess.getApproveDept():approveProcess.getApproveEnd());
        approveSearchDto.setRoleIdList(conventStringIdsToLongIds(approveProcess.getApproveRole()));
        approveSearchDto.setRelation(AceApproveRelEnum.getByCode(approveProcess.getRoleType().intValue()));
        return approveSearchDto;
    }

    /**
     * 返回空缺审批过程
     * @return
     */
    private ApproveProcedure assembleApproveProcedure(ApproveProcedureCreateDto dto,ApproveProcess approveProcess,byte group,byte watchLevel){
        Timestamp initTime = new Timestamp(System.currentTimeMillis());
        ApproveProcedure procedure = new ApproveProcedure();
        procedure.setId(UniqueIDUtils.getUniqueID());
        procedure.setApproveId(dto.getApproveId());
        procedure.setGroups(group);
        procedure.setType((byte)(approveProcess.getApproveType()+1));
        procedure.setWatchLevel(watchLevel);
        procedure.setStatus(ApproveProcedureStatusEnum.WAIT_APPROVE.getCode());
        procedure.setCreateTime(initTime);
        procedure.setInitiator(dto.getInitiator());
        procedure.setTitle(dto.getTitle());
        return procedure;
    }

    /*********************************************************************MOCK方法**************************************************************/

    /**
     * mock员工列表轻量Dto
     * @return
     */
    private CollectionResult<AceUserApproveDto> mockSmallUserDtos(){
        CollectionResult<AceUserApproveDto> userDtos = new CollectionResult<>();
        List<AceUserApproveDto> list = new ArrayList<>();
        AceUserApproveDto aceUserApproveDto = null;

        for(long i = 0;i<4;i++){
            aceUserApproveDto = new AceUserApproveDto();
            aceUserApproveDto.setId(i);
            aceUserApproveDto.setName("员工"+i);
            aceUserApproveDto.setTitle("职位"+i);
            list.add(aceUserApproveDto);
        }
        userDtos.setCollection(list);
        return userDtos;
    }

    /**
     * mock员工列表全量Dto
     * @return
     */
    private CollectionResult<AceUserDto> mockUserDtos(){
        CollectionResult<AceUserDto> userDtos = new CollectionResult<>();
        List<AceUserDto> list = new ArrayList<>();
        AceUserDto aceUserApproveDto = null;

        for(long i = 0;i<4;i++){
            aceUserApproveDto = new AceUserDto();
            aceUserApproveDto.setId(i);
            aceUserApproveDto.setUserName("员工"+i);
            aceUserApproveDto.setTitle("职位"+i);
            list.add(aceUserApproveDto);
        }
        userDtos.setCollection(list);
        return userDtos;
    }

    /**
     * mock员工全量Dto
     * @return
     */
    private DtoResult<AceUserDto> mockUserDto(){
        DtoResult<AceUserDto> userDtos = new DtoResult<>();
        AceUserDto AceUserDto = new AceUserDto();
        AceUserDto.setId(10L);
        AceUserDto.setUserName("员工10");
        AceUserDto.setTitle("职位10");
        userDtos.setDto(AceUserDto);
        return userDtos;
    }

    /**
     * mock带有按层级划分的用户等级
     * @return
     */
    private DtoResult<AceUserMultiLvlListDto> mockMultiLvluserDto(){
        DtoResult<AceUserMultiLvlListDto> result = new DtoResult<>();
        AceUserMultiLvlListDto one = new AceUserMultiLvlListDto();
        AceUserMultiLvlDto oneUser = new AceUserMultiLvlDto();
        oneUser.setDeptId(100L);
        oneUser.setDeptName("部门100");

        List<AceUserApproveDto> userList = new ArrayList<>();
        AceUserApproveDto aceUserApproveDto = null;
        for(long i = 1;i<4;i++){
            aceUserApproveDto = new AceUserApproveDto();
            aceUserApproveDto.setId(i*100);
            aceUserApproveDto.setName("员工"+(i*100));
            aceUserApproveDto.setTitle("职位"+(i*100));
            userList.add(aceUserApproveDto);
        }
        oneUser.setUserList(userList);
        one.setUserMultiLvlDto(oneUser);
        one.setLeafNode(true);

        AceUserMultiLvlListDto two = new AceUserMultiLvlListDto();
        AceUserMultiLvlDto twoUser = new AceUserMultiLvlDto();
        twoUser.setDeptId(200L);
        twoUser.setDeptName("部门200");

        List<AceUserApproveDto> userList2 = new ArrayList<>();
        AceUserApproveDto aceUserApproveDto2 = null;
        for(long i = 1;i<4;i++){
            aceUserApproveDto2 = new AceUserApproveDto();
            aceUserApproveDto2.setId(i*111);
            aceUserApproveDto2.setName("员工"+(i*111));
            aceUserApproveDto2.setTitle("职位"+(i*111));
            userList2.add(aceUserApproveDto2);
        }
        twoUser.setUserList(userList2);
        two.setUserMultiLvlDto(twoUser);
        two.setLeafNode(true);
        one.setChildren(two);

        AceUserMultiLvlListDto three = new AceUserMultiLvlListDto();
        AceUserMultiLvlDto threeUser = new AceUserMultiLvlDto();
        threeUser.setDeptId(300L);
        threeUser.setDeptName("部门300");

        List<AceUserApproveDto> userList3 = new ArrayList<>();
        AceUserApproveDto aceUserApproveDto3 = null;
        for(long i = 1;i<4;i++){
            aceUserApproveDto3 = new AceUserApproveDto();
            aceUserApproveDto3.setId(i*300);
            aceUserApproveDto3.setName("员工"+(i*300));
            aceUserApproveDto3.setTitle("职位"+(i*300));
            userList3.add(aceUserApproveDto3);
        }
        threeUser.setUserList(userList3);
        three.setUserMultiLvlDto(threeUser);
        three.setLeafNode(false);
        two.setChildren(three);

        result.setDto(one);
        return result;
    }
}
