package com.bitc.data.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "jpa_salaries")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SalariesEntity extends EmpBaseEntity{
  /*@Id
  private int empNo;*/ // 조인해서 받아올 거기 때문에 주석 처리

  @Id
  @CreatedDate
  private LocalDateTime fromDate;

  @Column(nullable = false)
  private int salary;

  @Id
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "emp_no") // 아래는 들고올 테이블 이름 name은 어느 컬럼을 쓸 건지 지정
  private EmployeeEntity employees;
}
