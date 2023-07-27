package com.bitc.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
* JPA Audit : 감시하다라는 의미로 각 데이터 마다 누가 데이터를 생성 했고 변경했는지 감시하는 기능
* JPA Audit 기능 활성화
* 1번 방식 : Application 클래스에 @EnableJpaAuditing 어노테이션 추가
* 2번 방식 : Configuration 클래스 생성 후 @EnableJpaAuditing 어노테이션 추가
*
* @MappedSuperClass : JPA 의 Entity 클래스가 상속 받을 경우 자식 클래스에 매핑 정보를 전달하는 어노테이션
* @EntityListeners : Entity 를 데이터베이스에 적용하기 전후로 콜백을 요청할 수 있도록 하는 어노테이션
* @AuditingEntityListener : Entity 의 Auditing 정보를 주입하는 JPA Entity 리스너 클래스  */

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  // @CreatedDate : 데이터 생성 날짜 및 시간을 자동으로 주입하는 어노테이션
  // JPA Audit 기능이 활성화 되어 있어야 동작함
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  // @LastModifiedDate : 데이터 수정 날짜 및 시간을 자동으로 주입하는 어노테이션
  // Auditing 이 동작하고 있어야 어노테이션 둘 다 작동한다
  @LastModifiedDate
  private LocalDateTime updatedAt;

  /* @CreateBy : JPA Audit 에서 제공하는 어노테이션으로 누가 데이터를 생성했는지 값을 자동으로 주입함
  * @LastModifiedBy : JPA Audit 에서 제공하는 어노테이션을 누가 데이터를 수정했는지 값을 자동으로 주입함
  * */
  /* @CreatedBy
  @Column(nullable = false)
  private String createdId;

  @LastModifiedBy
  private String updatedId;*/

}
