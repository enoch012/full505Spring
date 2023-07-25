package com.board4.controller;

import com.board4.entity.JpaBoardEntity;
import com.board4.service.JpaBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class BoardController {

  private final JpaBoardService jpaBoardService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() throws Exception {
    return "Hello World";
  }

  @RequestMapping(value = "/board", method = RequestMethod.GET)
  public ModelAndView selectBoardList() throws Exception {
    ModelAndView mv = new ModelAndView("/board/jpaBoardList");

    // 데이터 리스트를 테이블을 나타내는 entity 클래스를 통해서 가져옴
    // service 객체는 mybatis 사용 시와 동일한 역할을 함
    List<JpaBoardEntity> boardList = jpaBoardService.selectBoardList();

    mv.addObject("boardList", boardList);
    return mv;
  }

}
