package com.bitc.data.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EmpBaseEntity {
  // fromdate 는 pk 여서 base로 사용할 수 X

  @LastModifiedBy // 수정해줘야 하니까 해당 어노테이션 부여
  private LocalDateTime toDate;
}
