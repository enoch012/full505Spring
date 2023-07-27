package com.bitc.data.repository;

import com.bitc.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<EmployeeEntity, Integer> {
  Optional<EmployeeEntity> findByEmpNo(int empNo);
  List<EmployeeEntity> findAllByFirstName(String firstName);
}
