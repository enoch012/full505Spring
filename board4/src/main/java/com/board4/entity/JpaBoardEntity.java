package com.board4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_board")
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class JpaBoardEntity {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int boardIdx;

  @Column(name = "title", length =  100, nullable = false)
  private String title;

  @Column(nullable = false, length = 500)
  private String contents;

  @Column(nullable = false)
  private String createId;

  @Column(nullable = false)
  private LocalDateTime createDt;

  // 기본값으로 다 사용시 @ColLum
  private String UpdateId;
  private LocalDateTime updateDt;

  private int hitCnt;
}
