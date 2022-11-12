package com.avinty.hr.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(length = 66, nullable = false)
    private String password;

    @Column(name = "full_name", length = 200, nullable = false)
    private String fullName;

    @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id")
    private DepartmentEntity department;

}
