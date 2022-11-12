package com.avinty.hr.service;

import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.exceptions.DepartmentNotFoundException;
import com.avinty.hr.repository.DepartmentDao;
import com.avinty.hr.repository.EmployeeDao;
import mappers.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public List<DepartmentDto> getDepartmentsWithEmployees(){
        return DepartmentMapper.INSTANCE.entityToDto(departmentDao.findAll());
    }

    public DepartmentDto findByName(final String name){
        return DepartmentMapper.INSTANCE.entityToDto(departmentDao.findByName(name).orElseThrow(()-> new DepartmentNotFoundException()));
    }

    public void delete(final int id){
        departmentDao.deleteById(id);
    }

}
