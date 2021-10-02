package com.assigment.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "department_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    // Right now We don't have manager class so that's why I commented here

    /*@Column(name = "manager_id")
    private String manager;*/
}
