package com.avinty.hr.repository;

import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.entity.DepartmentEntity;
import com.avinty.hr.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentDao extends JpaRepository<DepartmentEntity, Integer>, JpaSpecificationExecutor<DepartmentEntity> {

    Optional<DepartmentEntity> findByName(String name);
}