package com.assigment.service;

import com.assigment.entity.Employee;
import com.assigment.repository.EmployeeRepository;
import com.assigment.wrapper.EmployeeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * This method returns the List of Employee after fetching from DB.
     * @return List<EmployeeWrapper>
     */

    public List<EmployeeWrapper> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeWrapper> employeeWrapperList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeWrapper branchResponseWrapper = new EmployeeWrapper(employee);
            employeeWrapperList.add(branchResponseWrapper);
        }

        return employeeWrapperList;
    }


    /**
     * This method saves Employee Information.
     * @param employeeWrapper EmployeeWrapper
     * @return Employee
     */

    public Employee saveBranch(EmployeeWrapper employeeWrapper) {

        Employee employee = new Employee();
        employee.setFirstName(employeeWrapper.getFirstName());
        employee.setLastName(employeeWrapper.getLastName());
        employee.setEmail(employeeWrapper.getEmail());
        employee.setPhoneNo(employeeWrapper.getPhoneNo());
        employee.setSalary(employeeWrapper.getSalary());
        employee.setHireDate(employeeWrapper.getHireDate());
        employeeRepository.save(employee);
        return employee;
    }

    /**
     * This method return  Employee Information.
     * @param id long
     * @return Employee
     */

    public Employee findById(long id) {
        Employee employeeObj=new Employee();
        employeeObj  = employeeRepository.findById(id).get();
        return employeeObj;
    }

    /**
     * This method return  Employee Information.
     * @param id long
     * @return Employee
     */
    public EmployeeWrapper findDetailsById(long id) {
        Employee employeeObj=new Employee();
        EmployeeWrapper employeeWrapper = null;
        employeeObj  = employeeRepository.findById(id).get();
        if(employeeObj!=null)
        {
            employeeWrapper = new EmployeeWrapper(employeeObj);
        }
        return employeeWrapper;
    }

    /**
     * This method deletes  Employee Information.
     * @param employee Employee
     */
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    /**
     * This method update  Employee Information.
     * @param employee Employee
     * @param employeeRequestWrapper EmployeeWrapper
     */
    public Employee updateEmployee(EmployeeWrapper employeeRequestWrapper, Employee employee) {

        employee.setFirstName(employeeRequestWrapper.getFirstName());
        employee.setLastName(employeeRequestWrapper.getLastName());
        employee.setSalary(employeeRequestWrapper.getSalary());
        employee.setPhoneNo(employeeRequestWrapper.getPhoneNo());
        employee.setEmail(employeeRequestWrapper.getEmail());
        employeeRepository.save(employee);
        return employee;
    }

}
