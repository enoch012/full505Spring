package com.bitc.controller;

import com.bitc.service.EmployeesService;
import com.bitc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RequiredArgsConstructor
@RestController
public class JpaTestController {

  private final ProductService productService;

  private final EmployeesService empservice;

  @RequestMapping("/")
  public String index() throws Exception{
    /*productService.finds();*/
    /*productService.exists();*/
    /*productService.count();*/
    productService.and();
    return "success";
  }

  @RequestMapping("/query")
  public String query() throws Exception {
    productService.querySelectAll();
    productService.querySelectName();
    return "query success";
  }

  @RequestMapping("/emp")
  public String empInfo() throws Exception {
    empservice.getEmployeeInfo(10001);
    empservice.getEmployeeInfoList("Elvis");

    return "success";
  }
}
