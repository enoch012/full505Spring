package com.board4.service;

import com.board4.entity.JpaBoardEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JpaBoardService {
  List<JpaBoardEntity> selectBoardList() throws Exception;
}
