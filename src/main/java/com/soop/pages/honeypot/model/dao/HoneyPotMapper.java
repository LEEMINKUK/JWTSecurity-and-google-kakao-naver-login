package com.soop.pages.honeypot.model.dao;

import com.soop.pages.honeypot.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HoneyPotMapper {

    // 허니팟 등록하기
    void insertHoneypot(HoneypotDTO honeypotDTO);

    // 허니팟 전체 조회
    List<HoneypotDTO> findAllHoneypots();

    // 허니팟 상세 조회
    HoneypotAndInterestAndLinkBeeUserDTO findByHoneypotCode(int honeypotCode);

    // 상세페이지 수정용 임시 상세조회
    HoneypotDTO temporaryFindByHoneypotCode(int honeypotCode);

    // 상세페이지 수정
    void modifyHoneypot(HoneypotDTO modifyHoneypot);

    // 댓글 전체 조회
    List<CommentAndLinkBeeUserDTO> findAllComments();

    // 댓글 코드로 조회
    CommentAndLinkBeeUserDTO findCommentByCommentCode(int commentCode);

    // 댓글 코드로 조회(임시)
    CommentDTO temporaryFindCommentByCommentCode(int commentCode);

    // 댓글 수정
    void modifyComment(CommentDTO modifyComment);

    // 댓글 삭제
    void deleteCommentByCommentCode(int commentCode);

    // 댓글 등록
    void insertComment(CommentDTO comment);

    // 댓글 등록
    void registComment(CommentAndLinkBeeUserDTO newComment);

    // 참가신청 등록
    void insertApplication(ApplicationDTO newApplication);

    // 참가신청 시 승인여부에 등록
    void insertApprovalStatus(ApprovalStatusDTO approvalStatusDTO);


    // 해당 허니팟 참가신청 목록 조회
    List<ApprovalStatusDTO> findApplicationsByHoneypotCode(int honeypotCode);

    // 참가신청 코드로 상세조회
    ApprovalStatusDTO findApplicationByHoneypotCodeAndApplicationCode(int honeypotCode, int applicationCode);

    void updateApplicationData(int honeypotCode, int applicationCode, ApprovalStatusDTO updateApprovalStatus);

    List<HoneypotAndApplicationAndApprovalStatusDTO> getHoneyPotApprovedList();

    // 허니팟 Status 변경(모임날짜가 지났을 때 진행완료로 변경)
    void updateClosureStatus();

    void deleteHoneypotByHoneypotCode(int honeypotCode);

    // 허니팟 신고 기능
    void reportHoneypot(int honeypotCode);
}


