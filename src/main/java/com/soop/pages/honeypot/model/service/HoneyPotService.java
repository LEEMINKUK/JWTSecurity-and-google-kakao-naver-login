package com.soop.pages.honeypot.model.service;

import com.soop.pages.honeypot.model.dao.HoneyPotMapper;
import com.soop.pages.honeypot.model.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HoneyPotService {

    private HoneyPotMapper honeyPotMapper;

    public HoneyPotService(HoneyPotMapper honeyPotMapper) {
        this.honeyPotMapper = honeyPotMapper;
    }

    // 허니팟 등록하기
    public HoneypotDTO saveHoneypot(HoneypotDTO honeypotDTO) {
        honeyPotMapper.insertHoneypot(honeypotDTO);
        return honeypotDTO;
    }

    // 허니팟 전체 조회
    public List<HoneypotDTO> findAllHoneypots() {
        return honeyPotMapper.findAllHoneypots();
    }

    // 허니팟 상세조회(허니팟코드)
    public HoneypotAndInterestAndLinkBeeUserDTO findByHoneypotCode(int honeypotCode) {
        return honeyPotMapper.findByHoneypotCode(honeypotCode);
    }

    // 상세페이지 수정용 임시 상세조회
    public HoneypotDTO temporaryFindByHoneypotCode(int honeypotCode) {
        return honeyPotMapper.temporaryFindByHoneypotCode(honeypotCode);
    }

    // 상세페이지 수정
    public HoneypotDTO modifyHoneypot(int honeypotCode, HoneypotDTO honeypotDTO) {

        // 기존 허니팟 정보 가져오기
        HoneypotDTO modifyHoneypot = temporaryFindByHoneypotCode(honeypotCode);

        // 수정할 항목들
        modifyHoneypot.setHoneypotTitle(honeypotDTO.getHoneypotTitle());
        modifyHoneypot.setHoneypotContent(honeypotDTO.getHoneypotContent());
        modifyHoneypot.setEventDate(honeypotDTO.getEventDate());
        modifyHoneypot.setEndDate(honeypotDTO.getEndDate());
        modifyHoneypot.setTotalMember(honeypotDTO.getTotalMember());
        modifyHoneypot.setClosureStatus(honeypotDTO.getClosureStatus());

        honeyPotMapper.modifyHoneypot(modifyHoneypot);

        return modifyHoneypot;
    }

    // 댓글 전체 조회
    public List<CommentAndLinkBeeUserDTO> findAllComments() {
        return honeyPotMapper.findAllComments();
    }

    // 댓글 코드로 조회
    public CommentAndLinkBeeUserDTO findCommentByCommentCode(int commentCode) {
        return honeyPotMapper.findCommentByCommentCode(commentCode);
    }
    // 댓글 코드로 조회(임시)
    public CommentDTO temporaryFindCommentByCommentCode(int commentCode) {
        return honeyPotMapper.temporaryFindCommentByCommentCode(commentCode);
    }


    public CommentDTO modifyComment(int commentCode, CommentDTO commentDTO) {

        // 기존 댓글 정보 가져오기
        CommentDTO modifyComment = temporaryFindCommentByCommentCode(commentCode);

        // 수정할 항목들
        modifyComment.setContent(commentDTO.getContent());
        modifyComment.setUpdateTime(commentDTO.getUpdateTime());

        honeyPotMapper.modifyComment(modifyComment);

        return modifyComment;

    }

    // 댓글 삭제
    public void deleteCommentByCommentCode(int commentCode) {
        honeyPotMapper.deleteCommentByCommentCode(commentCode);
    }

    // 댓글 등록
    public CommentDTO insertComment(CommentDTO comment) {
        honeyPotMapper.insertComment(comment);
        return comment;
    }

    // 댓글 등록 테스트
    public CommentAndLinkBeeUserDTO registComment(CommentAndLinkBeeUserDTO newComment) {
        honeyPotMapper.registComment(newComment);
        return newComment;
    }

    // 참가신청 등록
    @Transactional
    public ApplicationDTO registApplication(ApplicationDTO newApplication) {
        honeyPotMapper.insertApplication(newApplication);

        ApprovalStatusDTO approvalStatusDTO = new ApprovalStatusDTO();
        approvalStatusDTO.setApplicationCategory(newApplication);
        approvalStatusDTO.setDecisionStatus("승인대기중");
        approvalStatusDTO.setDecisionDate(null);

        honeyPotMapper.insertApprovalStatus(approvalStatusDTO);

        return newApplication;
    }

    // 해당 허니팟 참가신청 조회
    @Transactional(readOnly = true)
    public List<ApprovalStatusDTO> findApplicationsByHoneypotCode(int honeypotCode) {
        return honeyPotMapper.findApplicationsByHoneypotCode(honeypotCode);
    }


    public ApprovalStatusDTO findApplicationByHoneypotCodeAndApplicationCode(int honeypotCode, int applicationCode) {
        return honeyPotMapper.findApplicationByHoneypotCodeAndApplicationCode(honeypotCode, applicationCode);
    }

    public ApprovalStatusDTO updateApplicationData(int honeypotCode, int applicationCode, ApprovalStatusDTO approvalStatusDTO) {
        // 기존 신청 정보 가져오기
        ApprovalStatusDTO updateApprovalStatus = honeyPotMapper.findApplicationByHoneypotCodeAndApplicationCode(honeypotCode, applicationCode);

        // 수정할 항목들 설정
        updateApprovalStatus.setDecisionStatus(approvalStatusDTO.getDecisionStatus());
        updateApprovalStatus.setDecisionDate(approvalStatusDTO.getDecisionDate());

        // 데이터베이스 업데이트
        honeyPotMapper.updateApplicationData(honeypotCode, applicationCode, updateApprovalStatus);

        return updateApprovalStatus;
    }

    public List<HoneypotAndApplicationAndApprovalStatusDTO> getHoneyPotApprovedList() {
        return honeyPotMapper.getHoneyPotApprovedList();
    }

    // 허니팟 Status 변경(모임날짜가 지났을 때 진행완료로 변경)
    public void updateClosureStatus() {
        honeyPotMapper.updateClosureStatus();
    }

    public void deleteHoneypotByHoneypotCode(int honeypotCode) {
        honeyPotMapper.deleteHoneypotByHoneypotCode(honeypotCode);
        System.out.println("서비스 삭제 됨?");
    }

    public HoneypotDTO reportHoneypot(int honeypotCode) {
        // 신고 횟수 증가
        honeyPotMapper.reportHoneypot(honeypotCode);

        // 업데이트된 허니팟 정보 가져오기
        return honeyPotMapper.temporaryFindByHoneypotCode(honeypotCode);
    }
}
