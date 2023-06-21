package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.dto.DailyBoxOfficeDto;
import com.bitc.xml_json_parser.dto.PharmacyFullDataItemDto;

import java.util.List;

public interface ParserService {
  List<PharmacyFullDataItemDto> getItemListFile(String filePath) throws Exception;
  List<PharmacyFullDataItemDto> getItemListUrl(String url) throws Exception;

  List<DailyBoxOfficeDto> getDailyBoxOfficeList(String url) throws Exception;
}
