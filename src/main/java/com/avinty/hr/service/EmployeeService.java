package com.avinty.hr.service;

import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.dto.EmployeeInDto;
import com.avinty.hr.entity.DepartmentEntity;
import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.exceptions.DepartmentNotFoundException;
import com.avinty.hr.exceptions.EmployeeAlreadyExistsException;
import com.avinty.hr.repository.DepartmentRepository;
import com.avinty.hr.repository.EmployeeRepository;
import mappers.EmployeeInMapper;
import mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<EmployeeDto> findAllEmployees() {
        final List<EmployeeEntity> employees = employeeRepository.findAllByOrderByFullNameAsc();
        return EmployeeMapper.INSTANCE.entityToDto(employees);
    }

    public EmployeeEntity create(final EmployeeInDto employeeDto) {
        final EmployeeEntity employee = EmployeeInMapper.INSTANCE.dtoToEntity(employeeDto);
        if( employeeRepository.findByEmail(employee.getEmail()) != null) {
            throw new EmployeeAlreadyExistsException(employee.getEmail());
        }
        if (employeeDto.getDepartmentId() != null) {
            final Optional<DepartmentEntity> department = departmentRepository.findById(employeeDto.getDepartmentId());
            department.ifPresentOrElse(d -> employee.setDepId(employeeDto.getDepartmentId()), () -> {
                throw new DepartmentNotFoundException(String.format("Department ID '%d' not found.", employeeDto.getDepartmentId()));
            });
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        employee.setCreatedBy(employeeRepository.findByEmail(((User)authentication.getPrincipal()).getUsername()).getId());
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.saveAndFlush(employee);
    }

}
