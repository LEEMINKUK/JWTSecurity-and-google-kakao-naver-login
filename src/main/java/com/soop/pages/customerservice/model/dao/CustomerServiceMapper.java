package com.soop.pages.customerservice.model.dao;

import com.soop.pages.customerservice.model.dto.FileDTO;
import com.soop.pages.customerservice.model.dto.InquiryDTO;
import com.soop.pages.customerservice.model.dto.NoticeMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerServiceMapper {

    // 고객센터 메인 페이지에 표시할 최신 공지사항 5개를 조회하는 메서드
    List<NoticeMemberDTO> csMainNoticeListAll();

    // 전체 공지사항을 조회하는 메서드
    List<NoticeMemberDTO> noticeListAll();

    // 특정 공지사항의 상세 내용을 조회하는 메서드
    NoticeMemberDTO noticeDetail(String code);

    // 1:1 문의를 데이터베이스에 저장하는 메서드
    void inquiry(InquiryDTO inquiryDTO);

    // 특정 공지사항에 첨부된 파일 정보를 조회하는 메서드
    FileDTO noticeFile(int id);
}
