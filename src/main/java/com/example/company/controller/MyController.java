package com.example.company.controller;

import com.example.company.entity.Department;
import com.example.company.entity.Employee;
import com.example.company.entity.Position;
import com.example.company.entity.Sub_department;
import com.example.company.enums.Departments;
import com.example.company.enums.Positions;
import com.example.company.enums.SubDepartments;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class MyController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showAllEmployees(Model model) {

        List<Employee> employees = employeeService.getEmployees();
        List<Employee> purchasingEmployees = getEmployeesBySubDepartments(employees, SubDepartments.Purchasing);
        List<Employee> salesEmployees = getEmployeesBySubDepartments(employees, SubDepartments.Sales);
        List<Employee> accountsEmployees = getEmployeesBySubDepartments(employees, SubDepartments.Accounts);
        List<Employee> managementsEmployees = getEmployeesBySubDepartments(employees, SubDepartments.Managements);
        List<Employee> manufacturingEmployees = getEmployeesBySubDepartments(employees, SubDepartments.Manufacturing);
        List<Employee> distributionEmployees = getEmployeesBySubDepartments(employees, SubDepartments.Distribution);

        Map<String, List<Employee>> map = new HashMap<>();
        map.put("employees", employees);
        map.put("purchasingEmployees", purchasingEmployees);
        map.put("salesEmployees", salesEmployees);
        map.put("accountsEmployees", accountsEmployees);
        map.put("managementsEmployees", managementsEmployees);
        map.put("manufacturingEmployees", manufacturingEmployees);
        map.put("distributionEmployees", distributionEmployees);

        model.addAllAttributes(map);

        return "structureAndEmployees";
    }
    @GetMapping("/addEmp")
    public String saveEmployees() {

        employeeService.saveEmployees(createEmployees());

        return "addEmployees";
    }

    public List<Employee> getEmployeesBySubDepartments(List<Employee> employees, SubDepartments subDepartment) {
        List<Employee> employeeListOfSubDep = new ArrayList<>();
        Set<Position> positions;
        for (int i = 0; i < employees.size(); i++) {
            positions = new HashSet<>(employees.get(i).getPositions());

            for (Position pos : positions) {
                if (pos.getSub_department().getName().equals(subDepartment) && !employeeListOfSubDep.contains(employees.get(i))) {
                    employeeListOfSubDep.add(employees.get(i));
                }
            }
        }
        return employeeListOfSubDep;
    }

    public List<Employee> createEmployees() {

        Department dep1 = new Department(Departments.Marketing);
        Department dep2 = new Department(Departments.HR);
        Department dep3 = new Department(Departments.Production);

        Sub_department subdep1 = new Sub_department(SubDepartments.Purchasing, dep1);
        Sub_department subdep2 = new Sub_department(SubDepartments.Sales, dep1);
        Sub_department subdep3 = new Sub_department(SubDepartments.Accounts, dep2);
        Sub_department subdep4 = new Sub_department(SubDepartments.Managements, dep2);
        Sub_department subdep5 = new Sub_department(SubDepartments.Manufacturing, dep3);
        Sub_department subdep6 = new Sub_department(SubDepartments.Distribution, dep3);

        Position pos1 = new Position(Positions.Buyer, subdep1);
        Position pos2 = new Position(Positions.PurchasingAgent, subdep1);
        Position pos3 = new Position(Positions.ProcurementOfficer, subdep1);

        Position pos4 = new Position(Positions.SalesManager, subdep2);
        Position pos5 = new Position(Positions.SalesEngineer, subdep2);
        Position pos6 = new Position(Positions.SDR, subdep2);

        Position pos7 = new Position(Positions.AccountExecutive, subdep3);
        Position pos8 = new Position(Positions.Accountant, subdep3);
        Position pos9 = new Position(Positions.Analyst, subdep3);

        Position pos10 = new Position(Positions.RecruitingManager, subdep4);
        Position pos11 = new Position(Positions.Recruiter, subdep4);
        Position pos12 = new Position(Positions.HRIS, subdep4);

        Position pos13 = new Position(Positions.ManufacturingManager, subdep5);
        Position pos14 = new Position(Positions.LineProducer, subdep5);
        Position pos15 = new Position(Positions.Worker, subdep5);

        Position pos16 = new Position(Positions.LocationManager, subdep6);
        Position pos17 = new Position(Positions.Distributor, subdep6);
        Position pos18 = new Position(Positions.Coordinator, subdep6);

        Employee emp1 = new Employee("Mark", "Smith", pos1, pos8, pos3);
        Employee emp2 = new Employee("Kate", "Jones", pos2, pos5);
        Employee emp3 = new Employee("Peter", "Leaf", pos6, pos7);
        Employee emp4 = new Employee("Jone", "Sun", pos18, pos5, pos16);
        Employee emp5 = new Employee("Mickael", "Moon", pos13, pos9);
        Employee emp6 = new Employee("Cris", "Patt", pos4, pos12);
        Employee emp7 = new Employee("Serg", "Trim", pos17, pos15, pos2);
        Employee emp8 = new Employee("Kirill", "Lake", pos10, pos12, pos3);
        Employee emp9 = new Employee("Dmitriy", "Big", pos9, pos14);
        Employee emp10 = new Employee("Ivan", "Petrov", pos11, pos17);

        return List.of(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10);
    }



}
