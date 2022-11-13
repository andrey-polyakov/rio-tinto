package com.avinty.hr.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "department", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

}
