package com.avinty.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeInDto extends EmployeeDto {

    private String password;

    private Integer departmentId;

    private Boolean active;

    private String roles;
}
