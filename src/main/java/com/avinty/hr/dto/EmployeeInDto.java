package com.avinty.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeInDto extends BaseDto {

    private String email;

    @JsonIgnore
    private String password;

    private String fullName;

    private Long departmentId;
}
