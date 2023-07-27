package com.bitc.service;

import com.bitc.data.entity.ProductEntity;
import com.bitc.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;

  @Override
  public void finds() throws Exception {
    System.out.println("\n ----- findBy() 쿼리 메소드 실행 ----- \n");
    Optional<ProductEntity> prod1 = productRepository.findByNumber((long) 1);
    Optional<ProductEntity> prod2 = productRepository.findProductEntityByNumber((long)2);
    List<ProductEntity> prod3 = productRepository.findAllByName("볼펜");
    ProductEntity prod4 = productRepository.queryByName("볼펜");
    System.out.println("\n ----- findBy() 쿼리 메소드 실행 완료 ----- \n");
  }

  @Override
  public void exists() throws Exception {
    System.out.println("\n ----- exists() 쿼리 메소드 실행 ----- \n");
    boolean isProductNumber = productRepository.existsByNumber((long)200);
    System.out.println("\n ----- exists() 쿼리 메소드 실행 완료 ----- \n");
  }

  @Override
  public void count() throws Exception {
    System.out.println("\n ----- count() 쿼리 메소드 실행 ----- \n");
    int count = productRepository.countByName("노아");
    System.out.println("\n ----- count() 쿼리 메소드 실행 완료 ----- \n");
  }

  @Override
  public void delete() throws Exception {

  }

  @Override
  public void limit() throws Exception {

  }

  @Override
  public void equals() throws Exception {

  }

  @Override
  public void isNot() throws Exception {

  }

  @Override
  public void isNull() throws Exception {

  }

  @Override
  public void isNotNull() throws Exception {

  }

  @Override
  public void and() throws Exception {
    System.out.println("\n ----- and() 쿼리 메소드 실행 ----- \n");
    ProductEntity prod1 = productRepository.findByNumberAndName((long)6, "noah");
    ProductEntity prod2 = productRepository.findByNumberAndName((long)6, "노아");
    System.out.println("\n ----- and() 쿼리 메소드 실행 완료 ----- \n");
  }

  @Override
  public void or() throws Exception {

  }

  @Override
  public void between() throws Exception {

  }

  @Override
  public void like() throws Exception {

  }

  @Override
  public void orderBy() throws Exception {

  }

  @Override
  public void querySelectAll() {
    List<ProductEntity> list = productRepository.querySelectAll();
  }

  @Override
  public void querySelectName() {
    ProductEntity prod1 = productRepository.querySelectName();
    // ProductEntity prod2= productRepository.querySelectName("노아");
    ProductEntity prod3 = productRepository.querySelectNamePrice("유다", 7000);
  }
}
