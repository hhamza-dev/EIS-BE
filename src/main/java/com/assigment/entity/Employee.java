package com.assigment.entity;



import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "employee_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name")
    @Size(max=20)
    private String firstName;

    @Column(name = "last_name")
    @Size(max=25)
    private String lastName;

    @Column(name = "email")
    @Size(max=25)
    private String email;

    @Column(name = "phone_number")
    @Size(max=20)
    private String phoneNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hire_date")
    private Date hireDate;


    @Column(name = "salary", nullable = false,precision=10, scale=2)
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    private Departments department;

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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = null;
    }
}
