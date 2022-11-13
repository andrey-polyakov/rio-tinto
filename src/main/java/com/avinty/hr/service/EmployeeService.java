package com.avinty.hr.service;

import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.dto.EmployeeInDto;
import com.avinty.hr.entity.DepartmentEntity;
import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.exceptions.DepartmentNotFoundException;
import com.avinty.hr.repository.DepartmentRepository;
import com.avinty.hr.repository.EmployeeRepository;
import com.avinty.hr.security.MyUserPrincipal;
import mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private DepartmentRepository departmentRepo;

    public List<EmployeeDto> findAllEmployees() {
        final List<EmployeeEntity> employees = employeeRepo.findAllByOrderByFullNameAsc();
        final List<EmployeeDto> employeeDtos = EmployeeMapper.INSTANCE.entityToDto(employees);
        return employeeDtos;
    }

    public EmployeeEntity create(final EmployeeInDto employeeDto) {
        final EmployeeEntity employee = EmployeeMapper.INSTANCE.dtoToEntity(employeeDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<DepartmentEntity> department = departmentRepo.findById(employeeDto.getDepartmentId());
        department.ifPresentOrElse(d -> employee.setDepartment(d), () -> { throw new DepartmentNotFoundException(); });
        employee.setUpdatedBy(((MyUserPrincipal) authentication.getPrincipal()).getId());
        return employeeRepo.saveAndFlush(employee);
    }

}
