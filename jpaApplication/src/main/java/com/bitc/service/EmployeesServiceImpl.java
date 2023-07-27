package com.bitc.service;

import com.bitc.data.dto.EmployeesDto;
import com.bitc.data.entity.EmployeeEntity;
import com.bitc.data.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService{

  private final EmployeesRepository empRepo;

  @Override
  public EmployeeEntity getEmployeeInfo(int empNo) {
    Optional<EmployeeEntity> empInfo = empRepo.findByEmpNo(empNo);

    if (empInfo.isPresent()){
      EmployeeEntity emp = empInfo.get();
      return emp;
    }else {
      return null;
    }

  }

  /* dto 를 활용한 메소드 구현 */
  /* 보통은 테이블을 만드는 entity 를 직접 사용하지 않고 dto로 따로 받아서 사용하는 경우가 많다 */

  @Override
  public List<EmployeeEntity> getEmployeeInfoList(String firstName) {
    List<EmployeeEntity> empList = empRepo.findAllByFirstName(firstName);

    return empList;
  }

  @Override
  public EmployeesDto getMemberInfoEmpNo(int empNo) {
    Optional<EmployeeEntity> empInfo = empRepo.findByEmpNo(empNo);

    EmployeesDto member = new EmployeesDto();
    member.setEmpNo(empInfo.get().getEmpNo());
    member.setEmpName(empInfo.get().getFirstName() + empInfo.get().getLastName());
    member.setEmpGender(empInfo.get().getGender());

    return member;
  }

  @Override
  public List<EmployeesDto> getMemberInfoEmpName(String empName) {

    List<EmployeesDto> memberList = new ArrayList<>();

    List<EmployeeEntity> empList = empRepo.findAllByFirstName(empName);

    for (EmployeeEntity item : empList) {
      EmployeesDto member = new EmployeesDto();
      member.setEmpNo(item.getEmpNo());
      member.setEmpName(item.getFirstName() + item.getLastName());
      member.setEmpGender(item.getGender());

      memberList.add(member);
    }

    return memberList;
  }

  @Override
  public List<EmployeesDto> getMemberInfoListEmpGender(String gender) {
    return null;
  }

}
