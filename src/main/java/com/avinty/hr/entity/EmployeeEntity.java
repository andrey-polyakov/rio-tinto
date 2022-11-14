package com.avinty.hr.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

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

    @JoinColumn(name = "dep_id")
    private Integer depId;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column
    private String roles;

}
