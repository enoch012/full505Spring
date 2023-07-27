package com.bitc.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="jpa_product")
@Getter
@Setter
@ToString
@NoArgsConstructor
// @AllArgsConstructor// 모든 필드에 기본값 넣어서 작성해줌 (빼도되긴함)
public class ProductEntity {

  /* 기본키 */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long number; // 상품번호

  @Column(nullable = false)
  private String name; // 상품이름

  @Column(nullable = false)
  private Integer price; // 가격

  @Column(nullable = false)
  private Integer stock; // 재고수량
}
