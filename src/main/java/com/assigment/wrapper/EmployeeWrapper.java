package com.assigment.wrapper;

import com.assigment.entity.Employee;

import java.util.Date;

public class EmployeeWrapper {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private double salary;
    private Date hireDate;

    public EmployeeWrapper()
    {

    }

    public EmployeeWrapper(Long id, String firstName, String lastName, String email, String phoneNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public EmployeeWrapper(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phoneNo = employee.getPhoneNo();
        this.salary = employee.getSalary();
        this.hireDate =employee.getHireDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
