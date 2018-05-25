package com.epam.controller;

import com.epam.model.Employee;
import com.epam.repository.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/allemployees")
    public List<Employee> getAllEmployees() {
        logger.info("EmployeeController getAllEmployees called");
        return employeeRepository.findAll();
    }

    @GetMapping(value="/getemployeebyid/{empId}")
    public Employee getEmployeeById (@PathVariable Long empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);

        if (!employee.isPresent())
            throw new RuntimeException(" Employee id " + empId + " not found");

        return employee.get();
    }

    @DeleteMapping(value="/deleteemployeebyid/{empId}")
    public Employee deleteEmployeeById(@PathVariable Long empId) {
        logger.info("EmployeeController.deleteEmployeeById called");
        Optional<Employee> employee = employeeRepository.findById(empId);
        employeeRepository.deleteById(empId);
        if (!employee.isPresent())
            throw new RuntimeException(" Employee id " + empId + " not found");

        return employee.get();
    }

    @PostMapping(value="/addemployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        logger.info("EmployeeController.addEmployee called");
        logger.info("newEmployee name = "+employee.getempName());
        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee;
    }

    @PutMapping(value="/updateemployee/{empId}")
    public Employee updateEmployee(@PathVariable Long empId, @RequestBody Employee employee) {
        logger.info("EmployeeController.updateEmployee called");
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if (optionalEmployee.isPresent()) {
            Employee employeeToUpdate = optionalEmployee.get();
            employeeToUpdate.setempName(employee.getempName());
            employeeRepository.save(employeeToUpdate);
            return employeeToUpdate;
        }
        else {
            throw new RuntimeException(" Employee id " + empId + " not found");
        }

    }
}
