package com.example.company.repository;


import com.example.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepos extends JpaRepository<Employee, Long> {

}
