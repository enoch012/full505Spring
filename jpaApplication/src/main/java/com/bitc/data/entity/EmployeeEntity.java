package com.bitc.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeEntity extends EmpBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 계속 올라가야 하니까
  private int empNo;

  @Column(nullable = false)
  private LocalDateTime birthDate;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private char gender;

  @Column(nullable = false)
  private LocalDateTime hireDate;

  // @ToString.Exclude // 양방향 관계 설정 시 toString 을 사용할 경우 상호참조 (서로가 서로를 참조) 가 발생함, Exclude는 그것을 방지
  @OneToMany(mappedBy = "employees")
  @ToString.Exclude
  private List<SalariesEntity> salariesList = new ArrayList<>();

  @OneToMany(mappedBy = "employees")
  @ToString.Exclude
  private List<TitlesEntity> titlesList = new ArrayList<>();

}
