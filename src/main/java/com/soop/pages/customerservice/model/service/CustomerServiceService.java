package com.soop.pages.customerservice.model.service;

import com.soop.pages.customerservice.model.dao.CustomerServiceMapper;
import com.soop.pages.customerservice.model.dto.FileDTO;
import com.soop.pages.customerservice.model.dto.InquiryDTO;
import com.soop.pages.customerservice.model.dto.NoticeMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceService {

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    // 공지사항 메인 목록 조회
    public List<NoticeMemberDTO> csMainNoticeList() {
        return customerServiceMapper.csMainNoticeListAll();
    }

    // 모든 공지사항 목록 조회
    public List<NoticeMemberDTO> noticeList() {
        return customerServiceMapper.noticeListAll();
    }

    // 공지사항 상세 조회
    public NoticeMemberDTO noticeDetail(String code) {
        return customerServiceMapper.noticeDetail(code);
    }

    // 문의 등록
    public void inquiry(InquiryDTO newInquiry) {
        customerServiceMapper.inquiry(newInquiry);
    }

    // 공지사항 파일 조회
    public FileDTO noticeFile(String code) {
        int id = Integer.parseInt(code);
        return customerServiceMapper.noticeFile(id);
    }
}
