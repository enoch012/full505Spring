package com.bitc.data.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "jpa_titles")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TitlesEntity extends EmpBaseEntity {

  /*@Id
  private int empNo;*/

  @Id
  private String title;

  @Id
  @CreatedDate
  private LocalDateTime fromData;

  @Id
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "emp_no") // 아래는 들고올 테이블 이름 어느 컬럼을 쓸 건지 지정
  private EmployeeEntity employees;
}
