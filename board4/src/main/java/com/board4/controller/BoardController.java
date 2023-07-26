package com.board4.controller;

import com.board4.entity.JpaBoardEntity;
import com.board4.service.JpaBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    return "index";
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

  // 게시판 글쓰기 페이지 (뷰)
  @RequestMapping(value = "/board/write", method = RequestMethod.GET)
  public String insertBoardView() throws Exception {
    return "board/jpaBoardInsertView";
  }

  // 게시판 글 쓰기 페이지(Process)
  @RequestMapping(value = "/board/write", method = RequestMethod.POST)
  public String insertBoardProcess(JpaBoardEntity board) throws Exception {
    jpaBoardService.saveBoard(board);
    return "redirect:/board";
  }

  // 게시판 상세 글 보기
  @GetMapping(value = "/board/{boardIdx}")
  public ModelAndView selectBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("/board/jpaSelectBoardDetail");

    JpaBoardEntity board = jpaBoardService.selectBoardDetail(boardIdx);
    mv.addObject("board", board);

    return mv;
  }

  // 게시판 글 수정
  @PutMapping(value = "/board/{boardIdx}")
  public String updateBoard(JpaBoardEntity board) throws Exception{
    jpaBoardService.saveBoard(board);

    return "redirect:/board";
  }

  // 게시판 글 수정
  @DeleteMapping(value = "/board/{boardIdx}")
  public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
    jpaBoardService.deleteBoard(boardIdx);

    return "redirect:/board";
  }


}
