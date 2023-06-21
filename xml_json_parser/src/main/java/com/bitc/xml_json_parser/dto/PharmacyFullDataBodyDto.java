package com.bitc.xml_json_parser.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "body")
public class PharmacyFullDataBodyDto {
  private int numOfRows;
  private int pageNo;
  private int totalCount;
  private PharmacyFullDataItemsDto items;

  @XmlElement
  public int getNumOfRows() {
    return numOfRows;
  }

  public void setNumOfRows(int numOfRows) {
    this.numOfRows = numOfRows;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  @XmlElement(name = "items")
  public PharmacyFullDataItemsDto getItems() {
    return items;
  }

  public void setItems(PharmacyFullDataItemsDto items) {
    this.items = items;
  }
}
