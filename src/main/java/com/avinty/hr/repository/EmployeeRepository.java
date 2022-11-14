package com.avinty.hr.repository;

import com.avinty.hr.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {

    List<EmployeeEntity> findAllByOrderByFullNameAsc();

    EmployeeEntity findByEmail(String email);

    @Modifying
    @Query("update EmployeeEntity u set u.depId = null where u.depId = :depId")
    void setDepIdWhereDepIdEquals(@Param("depId") int depId);
}