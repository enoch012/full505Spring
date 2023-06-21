package com.bitc.xml_json_parser.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "items")
public class PharmacyFullDataItemsDto {
  private List<PharmacyFullDataItemDto> itemList;

  /* 이름을 item으로 지정하면서 itemList와 itemDto와 연동시킴 */
  @XmlElement(name = "item")
  public List<PharmacyFullDataItemDto> getItemList() {
    return itemList;
  }

  public void setItemList(List<PharmacyFullDataItemDto> itemList) {
    this.itemList = itemList;
  }
}
