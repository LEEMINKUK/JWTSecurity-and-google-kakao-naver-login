package com.soop.pages.cultureinfo.model.service;

import com.soop.pages.cultureinfo.model.dao.CultureInfoMapper;
import com.soop.pages.cultureinfo.model.dto.CultureInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureInfoService {

  // 데이터베이스와 상호작용하는 Mapper 객체를 사용하기 위한 변수
  private final CultureInfoMapper cultureInfoMapper;

  // CultureInfoMapper를 주입받는 생성자
  public CultureInfoService(CultureInfoMapper cultureInfoMapper) {
    this.cultureInfoMapper = cultureInfoMapper;
  }

  // 얼리버드 공연/전시 정보 전체 조회
  public List<CultureInfoDTO> selectAllCultureInfo() {
    // Mapper를 사용하여 전체 얼리버드 공연/전시 정보를 조회
    return cultureInfoMapper.selectAllCultureInfo();
  }

  // 코드에 해당하는 얼리버드 공연/전시 정보 상세 조회
  public CultureInfoDTO selectEarlyInfoByEarlyBirdCode(int earlyCode) {
    // Mapper를 사용하여 코드에 해당하는 상세 정보를 조회
    return cultureInfoMapper.selectEarlyInfoByEarlyBirdCode(earlyCode);
  }
}
