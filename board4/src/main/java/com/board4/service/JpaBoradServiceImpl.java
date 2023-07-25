package com.board4.service;

import com.board4.entity.JpaBoardEntity;
import com.board4.repository.JpaBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class JpaBoradServiceImpl implements JpaBoardService{

  // 데이터베이스에 명령어를 대신 전달하는 인터페이스
  // mybatis 에서 Mapper 인터페이스 및 xml 파일과 동일한 역할을 함
  private final JpaBoardRepository jpaBoardRepository;

  @Override
  public List<JpaBoardEntity> selectBoardList() throws Exception {

    return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
  }
}
