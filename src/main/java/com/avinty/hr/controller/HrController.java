package com.avinty.hr.controller;

import com.avinty.hr.dto.*;
import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.security.JwtTokenUtil;
import com.avinty.hr.service.DepartmentService;
import com.avinty.hr.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import mappers.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.avinty.hr.config.WebSecurityConfig.LOGIN_URL;

@Slf4j
@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
public class HrController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dep-emp")
    public ResponseEntity<List<DepartmentDto>> getDepartmentsWithEmployees() {
        return ResponseEntity.ok(departmentService.getDepartmentsWithEmployees());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> create(@RequestBody final EmployeeInDto employeeEntity) {
        final EmployeeEntity employee = employeeService.create(employeeEntity);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(EmployeeMapper.INSTANCE.entityToDto(employee));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/department/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        departmentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUserDto loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthTokenDto(token));
    }

}
