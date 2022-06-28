package com.example.company.service;

import com.example.company.entity.Employee;
import com.example.company.repository.EmployeeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepos employeeRepos;

    public List<Employee> getEmployees(){
        return employeeRepos.findAll();
    }

    public void saveEmployees(List<Employee> employees) {
        employeeRepos.saveAllAndFlush(employees);
    }

}
