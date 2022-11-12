package com.avinty.hr.controller;

import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.dto.EmployeeDto;
import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.service.DepartmentService;
import com.avinty.hr.service.EmployeeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("API/V1")
public class HrController {

    private static final Gson GSON = new Gson();

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("/dep-emp")
    public ResponseEntity<List<DepartmentDto>> getDepartmentsWithEmployees() {
        return ResponseEntity.ok(departmentService.getDepartmentsWithEmployees());
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> create(final EmployeeDto employeeEntity) {
        final EmployeeEntity employee = employeeService.create(employeeEntity);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
