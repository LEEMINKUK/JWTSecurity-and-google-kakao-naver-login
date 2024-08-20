package com.soop.pages.cultureinfo.model.dao;

import com.soop.pages.cultureinfo.model.dto.CultureInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CultureInfoMapper {

  // 전체 얼리버드 공연/전시 정보를 조회하는 메서드
  List<CultureInfoDTO> selectAllCultureInfo();

  // 코드에 해당하는 얼리버드 공연/전시 정보를 조회하는 메서드
  CultureInfoDTO selectEarlyInfoByEarlyBirdCode(int earlyCode);
}
