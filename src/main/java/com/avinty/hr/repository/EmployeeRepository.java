package com.avinty.hr.repository;

import com.avinty.hr.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {

    List<EmployeeEntity> findAllByOrderByFullNameAsc();

    EmployeeEntity findByEmail(String email);
}