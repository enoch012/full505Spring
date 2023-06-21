package com.bitc.xml_json_parser.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class PharmacyFullDataDto {
  private PharmacyFullDataHeaderDto header;
  private PharmacyFullDataBodyDto body;

  // @XmlRootElement : xml 데이터에서 부모가 되는 태그를 뜻하는 어노테이션
  // @XmlElement : xml 데이터에서 실제 데이터가 들어있는 태그를 뜻하는 어노테이션
  // @XmlAttribute : xml 데이터에서 지정한 태그의 속성을 뜻함

  @XmlElement(name = "header")
  public PharmacyFullDataHeaderDto getHeader() {
    return header;
  }

  public void setHeader(PharmacyFullDataHeaderDto header) {
    this.header = header;
  }

  @XmlElement(name = "body")
  public PharmacyFullDataBodyDto getBody() {
    return body;
  }

  public void setBody(PharmacyFullDataBodyDto body) {
    this.body = body;
  }
}
