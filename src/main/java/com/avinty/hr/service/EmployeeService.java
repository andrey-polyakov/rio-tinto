package com.avinty.hr.service;

import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeDao;
import com.avinty.hr.security.MyUserPrincipal;
import mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<EmployeeDto> findAllEmployees() {
        final List<EmployeeEntity> employees = employeeDao.findAllByOrderByFullNameAsc();
        final List<EmployeeDto> employeeDtos = EmployeeMapper.INSTANCE.entityToDto(employees);
        return employeeDtos;
    }

    public EmployeeEntity create(final EmployeeDto employeeDto) {
        final EmployeeEntity employee = EmployeeMapper.INSTANCE.dtoToEntity(employeeDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        employee.setUpdatedBy(((MyUserPrincipal) authentication.getPrincipal()).getId());
        return employeeDao.saveAndFlush(employee);
    }

}
