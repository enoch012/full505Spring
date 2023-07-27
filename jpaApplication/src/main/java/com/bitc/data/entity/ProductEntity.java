package com.bitc.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
* callSuper : 부모 클래스의 필드를 해당 클래스에 포함하는 역할을 하는 속성, 롬복에서 제공 */

@Entity
@Table(name="jpa_product")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
// @AllArgsConstructor// 모든 필드에 기본값 넣어서 작성해줌 (빼도되긴함)
public class ProductEntity extends BaseEntity{

  /* 기본키 */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long number; // 상품번호

  @Column(nullable = false)
  private String name; // 상품이름

  @Column(nullable = false)
  private Integer price; // 가격

  @Column(nullable = false)
  private Integer stock; // 재고수량

  // 아래의 필드는 부모 클래스인 BaseEntity 로부터 상속 받아 사용하기 때문에 삭제함
  /*@Column(nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now(); //등록시간

  private LocalDateTime updatedAt; // 최종 수정 시간*/

  // mappedBy 속성을 @OneToOne 어노테이션 사용 시 양방향 매핑이 아니면 사용하지 않아도됨
  @OneToOne(mappedBy = "product")
  @ToString.Exclude // 자식 테이블과 동일한게 있으면 없애줌
  private ProductDetailEntity productDetailEntity;

  @ManyToOne
  @JoinColumn(name = "provider_id")
  @ToString.Exclude
  private ProviderEntity provider;
}
