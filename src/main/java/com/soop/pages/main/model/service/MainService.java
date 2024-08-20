package com.soop.pages.main.model.service;

import com.soop.pages.main.model.dao.MainMapper;
import com.soop.pages.main.model.dto.UserInterestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

  private final MainMapper mainMapper;

  // MainMapper를 주입받는 생성자
  public MainService(MainMapper mainMapper) {
    this.mainMapper = mainMapper;
  }

  // 주어진 사용자 코드에 따라 관심사 리스트를 조회하는 메서드
  public List<UserInterestDTO> selectUserInterestByCode(int userCode) {
    // Mapper 를 통해 사용자 관심사 리스트를 조회
    return mainMapper.selectUserInterestByCode(userCode);
  }
}
