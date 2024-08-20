package com.soop.pages.main.model.dao;

import com.soop.pages.main.model.dto.UserInterestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {

  // 회원 코드에 해당하는 관심사 리스트를 조회합니다.
  List<UserInterestDTO> selectUserInterestByCode(int userCode);
}
