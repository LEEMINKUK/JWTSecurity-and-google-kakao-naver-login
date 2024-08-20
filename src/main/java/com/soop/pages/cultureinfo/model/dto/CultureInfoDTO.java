package com.soop.pages.cultureinfo.model.dto;

import java.util.Date;

public class CultureInfoDTO {
  private Integer earlyBirdCode; //얼리버드 번호
  private Integer interestCode; // 관심사(장르)번호
  private String ebTitle; // 제목
  private String ebContent; // 내용
  private String region; // 지역
  private String poster; // 포스터
  private String seller; // 판매처
  private String sellerLink; // 판매처 링크
  private Integer regularPrice; // 일반가격
  private Integer discountPrice; // 할인가격
  private Date saleStartDate; // 판매시작일
  private Date saleEndDate; // 판매종료일
  private Date usageStartDate; // 사용시작일
  private Date usageEndDate; // 사용종료일
  private String ageLimit; // 관람연령
  private Date dateWritten; // 작성일자
  private String place; // 관람장소

  public CultureInfoDTO(){}

  public CultureInfoDTO(Integer earlyBirdCode, Integer interestCode, String ebTitle, String ebContent, String region, String poster, String seller, String sellerLink, Integer regularPrice, Integer discountPrice, Date saleStartDate, Date saleEndDate, Date usageStartDate, Date usageEndDate, String ageLimit, Date dateWritten, String place) {
    this.earlyBirdCode = earlyBirdCode;
    this.interestCode = interestCode;
    this.ebTitle = ebTitle;
    this.ebContent = ebContent;
    this.region = region;
    this.poster = poster;
    this.seller = seller;
    this.sellerLink = sellerLink;
    this.regularPrice = regularPrice;
    this.discountPrice = discountPrice;
    this.saleStartDate = saleStartDate;
    this.saleEndDate = saleEndDate;
    this.usageStartDate = usageStartDate;
    this.usageEndDate = usageEndDate;
    this.ageLimit = ageLimit;
    this.dateWritten = dateWritten;
    this.place = place;
  }

  public Integer getEarlyBirdCode() {
    return earlyBirdCode;
  }

  public void setEarlyBirdCode(Integer earlyBirdCode) {
    this.earlyBirdCode = earlyBirdCode;
  }

  public Integer getInterestCode() {
    return interestCode;
  }

  public void setInterestCode(Integer interestCode) {
    this.interestCode = interestCode;
  }

  public String getEbTitle() {
    return ebTitle;
  }

  public void setEbTitle(String ebTitle) {
    this.ebTitle = ebTitle;
  }

  public String getEbContent() {
    return ebContent;
  }

  public void setEbContent(String ebContent) {
    this.ebContent = ebContent;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getSellerLink() {
    return sellerLink;
  }

  public void setSellerLink(String sellerLink) {
    this.sellerLink = sellerLink;
  }

  public Integer getRegularPrice() {
    return regularPrice;
  }

  public void setRegularPrice(Integer regularPrice) {
    this.regularPrice = regularPrice;
  }

  public Integer getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(Integer discountPrice) {
    this.discountPrice = discountPrice;
  }

  public Date getSaleStartDate() {
    return saleStartDate;
  }

  public void setSaleStartDate(Date saleStartDate) {
    this.saleStartDate = saleStartDate;
  }

  public Date getSaleEndDate() {
    return saleEndDate;
  }

  public void setSaleEndDate(Date saleEndDate) {
    this.saleEndDate = saleEndDate;
  }

  public Date getUsageStartDate() {
    return usageStartDate;
  }

  public void setUsageStartDate(Date usageStartDate) {
    this.usageStartDate = usageStartDate;
  }

  public Date getUsageEndDate() {
    return usageEndDate;
  }

  public void setUsageEndDate(Date usageEndDate) {
    this.usageEndDate = usageEndDate;
  }

  public String getAgeLimit() {
    return ageLimit;
  }

  public void setAgeLimit(String ageLimit) {
    this.ageLimit = ageLimit;
  }

  public Date getDateWritten() {
    return dateWritten;
  }

  public void setDateWritten(Date dateWritten) {
    this.dateWritten = dateWritten;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  @Override
  public String toString() {
    return "CultureInfoDTO{" +
            "earlyBirdCode=" + earlyBirdCode +
            ", interestCode=" + interestCode +
            ", ebTitle='" + ebTitle + '\'' +
            ", ebContent='" + ebContent + '\'' +
            ", region='" + region + '\'' +
            ", poster='" + poster + '\'' +
            ", seller='" + seller + '\'' +
            ", sellerLink='" + sellerLink + '\'' +
            ", regularPrice=" + regularPrice +
            ", discountPrice=" + discountPrice +
            ", saleStartDate=" + saleStartDate +
            ", saleEndDate=" + saleEndDate +
            ", usageStartDate=" + usageStartDate +
            ", usageEndDate=" + usageEndDate +
            ", ageLimit='" + ageLimit + '\'' +
            ", dateWritten=" + dateWritten +
            ", place='" + place + '\'' +
            '}';
  }
}
