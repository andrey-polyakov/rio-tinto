package com.avinty.hr.service;

import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeDao;
import mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<EmployeeDto> findAllEmployees(){
        final List<EmployeeEntity> employees = employeeDao.findAllByOrderByFullNameAsc();
        final List<EmployeeDto> employeeDtos = EmployeeMapper.INSTANCE.entityToDto(employees);
        return employeeDtos;
    }

    public EmployeeEntity create(final EmployeeDto employeeDto){
        final EmployeeEntity employee = EmployeeMapper.INSTANCE.dtoToEntity(employeeDto);
        return employeeDao.saveAndFlush(employee);
    }

}
