package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.dto.*;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService{
  @Override
  public List<PharmacyFullDataItemDto> getItemListFile(String filePath) throws Exception {
    /* jaxb : 자바에서 xml 데이터 파싱을 도와주는 라이브러리
    * jaxb가 자바 9부터 자바 기본 라이브러리에서 제외됨 그래서 gradle에 추가하고 써야함
    * Marshal(마샬) : 자바 클래스를 XML 데이터로 변환
    * UnMarshal(언마샬) : XML 데이터를 자바 클래스 타입의 객체로 변환
    *
    * JAXB 라이브러리 사용 선언
    * PharmacyFullDataDto 클래스 타입으로 xml 데이터를 파싱  */
    JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataDto.class);

    /* JAXB 라이브러리를 사용하여 XML 데이터를 자바 클래스 타입의 객체로 변환하는 언마샬 생성 */
    Unmarshaller um = jc.createUnmarshaller();

    // 기존에 제공된 xml 데이터를 기반으로 PharmacyFullDataDto 클래스의 객체를 생성하므로 xml 데이터를 파싱하여 가져온 데이터를 PharmarcyFullDataDto 클래스 타입의 객체에 형변환하여 저장
    // unmarshal() : 언마샬을 실행하는 메소드, 매개변수로 파일이나 URL을 받음
    PharmacyFullDataDto fullData = (PharmacyFullDataDto) um.unmarshal(new File(filePath));

    PharmacyFullDataHeaderDto header = fullData.getHeader();
    PharmacyFullDataBodyDto body = fullData.getBody();

    PharmacyFullDataItemsDto items = body.getItems();
    List<PharmacyFullDataItemDto> itemList = items.getItemList();

    // 위의 선언 없이 한번에 쓰는 방법
    /*List<PharmacyFullDataItemDto> itemList2 = fullData.getBody().getItems().getItemList();*/

    return itemList;
  }

  @Override
  public List<PharmacyFullDataItemDto> getItemListUrl(String strUrl) throws Exception {
    // 반환할 데이터를 저장할 List 객체
    List<PharmacyFullDataItemDto> itemList = null;

    // HTTP URL 주소를 저장하는 객체
    URL url = null;
    // HTTP 프로토콜을 사용하여 지정된 주소로 통신을 하는 클래스
    HttpURLConnection urlConn = null;

    try {

      url = new URL(strUrl); // 매개변수로 받아온 서비스의 URL을 지정함
      urlConn = (HttpURLConnection) url.openConnection(); // 지정된 URL로 http 연결
      urlConn.setRequestMethod("GET"); // 서버로 통신 방식을 GET으로 설정

      // xml 데이터 파싱을 위한 DTO 클래스 지정
      JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataDto.class);
      // 언 마샬러 객체 생성
      // marshal((특정 목적을 위해 사람이나 생각 등을) 모으다, 집결시키다)
      Unmarshaller um = jc.createUnmarshaller();

      // xml 데이터 언마셜 시 url을 사용하여 원격 서버의 xml 데이터를 파싱함
      PharmacyFullDataDto fullData = (PharmacyFullDataDto) um.unmarshal(url);
      itemList = fullData.getBody().getItems().getItemList();

    } catch (JAXBException e){

      e.printStackTrace();

    } catch (Exception e) {

      e.printStackTrace();

    } finally {
      if(urlConn != null){
        urlConn.disconnect();
      }
    }

    return itemList;
  }

  @Override
  public List<DailyBoxOfficeDto> getDailyBoxOfficeList(String strUrl) throws Exception {
    /* build gradle에 gson 추가*/
    List<DailyBoxOfficeDto> itemList = null;

    URL url = null;
    HttpURLConnection urlConn = null;

    /* 파일 혹은 네트워크 통신 시 가져온 데이터를 임시 버퍼에 저장하면서 데이터를 읽는 클래스 */
    BufferedReader reader = null;

    try {

      url = new URL(strUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("GET");

      // BufferReader를 사용하여 HttpURLConnection을 통해서 데이터를 읽어옴
      // JAXB 라이브러리는 클래스 내부에 네트워크 데이터 처리 부분이 이미 존재함
      // 현재는 JSON 데이터를 가져옴
      // 네트워크 통신 시 사용하는 JSON 데이터는 JavaScript의 Object 타입을 흉내낸 문자열
      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

      /* 자바에서 문자열을 표현하기 위한 클래스인 String 클래스는 하나의 문자열을 저장하고
      * 해당 문자열에 다른 문자열을 연결할 경우 기존 문자열에 다른 문자열이 추가되는 것이 아니라
      * 기존 문자열과 추가된 문자열이 합쳐진 새 문자열을 생성함
      * 네트워크 통신을 통해서 들어오는 문자열 데이터를 처리하기에는 기존 String 클래스는 비효율적이므로 StringBuilder 클래스를 사용함*/
      StringBuilder sb = new StringBuilder(); // 네트워크 데이터로 문자열을 만들기 위한 변수 선언
      String line; // 네트워크에서 가져온 문자열을 저장할 변수

      // 버퍼에 저장된 네트워크 데이터를 데이터가 없을 때까지 반복해서 가져옴
      while ((line = reader.readLine()) != null ){
        sb.append(line);
      }

      // Gson 라이브러리를 사용하여 json을 파싱하기 위한 객체 생성
      Gson gson = new Gson();
      // json 문자열을 자바의 Object 타입으로 변경
      // 첫번째 매개변수는 변경할 문자열, 두번째 매개변수는 변경할 자바 클래스 타입
      BoxOfficeDto boxOffice = gson.fromJson(sb.toString(), BoxOfficeDto.class);
      itemList = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();


    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if(reader != null) reader.close();
      if (urlConn != null) urlConn.disconnect();

    }
    return itemList;
  }
}
