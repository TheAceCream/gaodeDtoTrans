//package com.qf58.ace.approve.serviceImpl;
//
//import com.qf58.ace.SpringBase;
//import com.qf58.ace.approve.api.service.ApproveFileService;
//import com.qf58.ace.approve.dto.ApproveDto;
//import com.qf58.ace.approve.dto.ApproveFileDto;
//import com.qf58.ace.approve.dto.ApproveFileSelectDto;
//import com.qf58.client.support.CollectionResult;
//import com.qf58.client.support.DtoResult;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//
//import javax.annotation.Resource;
//
///**
// * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2019/1/14 19:03 Time: 14:15
// */
//public class ApproveFileServiceTest  extends SpringBase {
//
//    @Resource
//    private ApproveFileService approveFileService;
//
//    @Test
////    @Ignore
//    public void addApproveFile() {
//        ApproveFileDto approveFileDto = new ApproveFileDto();
//        approveFileDto.setApproveId(22L);
//        approveFileDto.setApproveProcedureId(223L);
//        approveFileDto.setFileKey("1123");
//        approveFileDto.setFileType((byte) 1);
//        approveFileDto.setOriginName("jojo");
//        approveFileDto.setSize("15k");
//        approveFileDto.setPicUrl("asfasf/asfasf");
//        approveFileDto.setUserName("hahskladh");
//        approveFileService.addApproveFile(approveFileDto);
//    }
//
//    @Test
//    public void getApproveFilListe() {
//        ApproveFileSelectDto approveFileSelectDto = new ApproveFileSelectDto();
//        approveFileSelectDto.setApprovalId(1L);
//        CollectionResult<ApproveFileDto> collectionResult = approveFileService.getApproveFileList(approveFileSelectDto);
//        System.out.println(collectionResult.getCollection());
//    }
//    @Test
//    public void getApproveFile() {
//        DtoResult<ApproveFileDto> dtoDtoResult = approveFileService.getApproveFile(2550395244794422L);
//        dtoDtoResult.getDto();
//    }
//
//
//}
