package com.soop.pages.main.model.dto;

public class UserInterestDTO {
  private int userCode;
  private int interestCode;

  public UserInterestDTO() {}

  public UserInterestDTO(int userCode, int interestCode) {
    this.userCode = userCode;
    this.interestCode = interestCode;
  }

  public int getUserCode() {
    return userCode;
  }

  public void setUserCode(int userCode) {
    this.userCode = userCode;
  }

  public int getInterestCode() {
    return interestCode;
  }

  public void setInterestCode(int interestCode) {
    this.interestCode = interestCode;
  }

  @Override
  public String toString() {
    return "userInterestDTO{" +
            "userCode=" + userCode +
            ", interestCode=" + interestCode +
            '}';
  }
}
