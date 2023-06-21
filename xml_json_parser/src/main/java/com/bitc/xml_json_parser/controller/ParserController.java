package com.bitc.xml_json_parser.controller;

import com.bitc.xml_json_parser.dto.DailyBoxOfficeDto;
import com.bitc.xml_json_parser.dto.PharmacyFullDataItemDto;
import com.bitc.xml_json_parser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/parse")
public class ParserController {

  @Autowired
  private ParserService parserService;

  @Value("${full505.kobis.json.DailyBoxOfficeResultUrl}")
  private String serviceUrl;

  @Value("${full505.kobis.json.key}")
  private String serviceKey;

  @RequestMapping({"/", ""})
  public String index() throws Exception{
    return "index";
  }

  @GetMapping("/pharmacy/fullDataFile")
  public ModelAndView getFullDataFile() throws Exception{
    ModelAndView mv = new ModelAndView("pharmacy/fullDataFile");

    List<PharmacyFullDataItemDto> itemList = parserService.getItemListFile("C:\\smart505\\pharmarcy.xml");
    mv.addObject("itemList", itemList);

    return mv;
  }

  @GetMapping("/pharmacy/fullDataUrl")
  public String getFullDataUrl() throws Exception{
    return "pharmacy/fullDataUrl";
  }

  /* json으로 전송하기 위한 Object 타입 설정 */
  @ResponseBody
  @PostMapping("/pharmacy/fullDataUrl")
  public Object getFullDataUrl(@RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows) throws Exception{

    /* https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=DEqOIp3U7%2FlBoKa7ICWR9O6CTJAYJIhye16e1ZyNO%2B2g2PJJ2WVVfXOdtSn3UwnGaKHyDhtj9MqBiWk4yJyxVw%3D%3D&pageNo=1&numOfRows=10 */

    String serviceUrl = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
    String serciveKey = "?serviceKey=";
    serciveKey += "DEqOIp3U7%2FlBoKa7ICWR9O6CTJAYJIhye16e1ZyNO%2B2g2PJJ2WVVfXOdtSn3UwnGaKHyDhtj9MqBiWk4yJyxVw%3D%3D";

    String opt1 = "&pageNo=";
    String opt2 = "&numOfRows=";


    List<PharmacyFullDataItemDto> itemList = parserService.getItemListUrl(serviceUrl + serciveKey + opt1 + pageNo + opt2 + numOfRows);

    return itemList;
  }

  // json 파일로 영화진흥원 일일 박스오피스 출력 View

  @GetMapping("/kobis/dailyBoxOffice")
  public String dailyBoxOfficeView() throws Exception {
    return "kobis/dailyBoxOffice";
  }

  // 영화 진흥원 일일 박스오피스 json 데이터 가져오기
  @ResponseBody
  @PostMapping("/kobis/dailyBoxOffice")
  public Object getDailyBoxOfficeProcess(@RequestParam("targetDt") String targetDt) throws Exception {

    targetDt = targetDt.replace("-", "");
    String url = serviceUrl + "?key=" + serviceKey +"&targetDt=" + targetDt;

    List<DailyBoxOfficeDto> dailyBoxOfficeDtoList = parserService.getDailyBoxOfficeList(url);

    return dailyBoxOfficeDtoList;

  }
}
