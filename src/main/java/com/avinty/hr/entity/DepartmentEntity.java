package com.avinty.hr.entity;

import com.avinty.hr.dto.EmployeeDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

    @Column(length=100, nullable = false)
    private String name;

    @Column(name = "manager_id")
    private Integer managerId;

    @OneToMany(mappedBy = "department", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

}
