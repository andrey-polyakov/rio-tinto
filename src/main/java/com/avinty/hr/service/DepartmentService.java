package com.avinty.hr.service;

import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.exceptions.DepartmentNotFoundException;
import com.avinty.hr.repository.DepartmentRepository;
import com.avinty.hr.repository.EmployeeRepository;
import mappers.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired DepartmentRepository departmentRepository;

    @Autowired EmployeeRepository employeeRepository;

    public List<DepartmentDto> getDepartmentsWithEmployees(){
        return DepartmentMapper.INSTANCE.entityToDto(departmentRepository.findAll());
    }

    public DepartmentDto findByName(final String name){
        return DepartmentMapper.INSTANCE.entityToDto(departmentRepository.findByName(name).orElseThrow(()-> new DepartmentNotFoundException(String.format("Failed to find department with name: '%s'.", name))));
    }

    public void delete(final int id){
        employeeRepository.setDepIdWhereDepIdEquals(id);
        departmentRepository.deleteById(id);
    }

}
