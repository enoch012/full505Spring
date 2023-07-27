package com.bitc.service;

import com.bitc.data.dto.EmployeesDto;
import com.bitc.data.entity.EmployeeEntity;

import java.util.List;

public interface EmployeesService {
  EmployeeEntity getEmployeeInfo(int empNo);

  List<EmployeeEntity> getEmployeeInfoList(String firstName);

  /* Dto 를 활용한 메소드 구현 */

  // 사원 1명의 정보를 사원 번호로 가져옴
  EmployeesDto getMemberInfoEmpNo(int empNo);

  // 사원 1명의 정보를 사원 이름을 기반으로 해서 가져옴
  List<EmployeesDto> getMemberInfoEmpName(String empName);

  // 사원 정보를 사원 성별을 기반으로 해서 모두 가져옴
  List<EmployeesDto> getMemberInfoListEmpGender(String gender);
}
