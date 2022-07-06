package com.emidsClient.employee.controller;

import com.emidsClient.employee.entity.Employee;
import com.emidsClient.employee.entity.EmployeeRequest;
import com.emidsClient.employee.entity.EmployeeResponse;
import com.emidsClient.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping(
            value = "/saveEmployees",
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<EmployeeResponse> saveEmployeesResource(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.accepted().body(employeeService.saveEmployees(employeeRequest));
    }

    @GetMapping(
            value = "/getEmployees",
            produces  = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<EmployeeResponse> getEmployeesWithHighSalaryResource(){
        return ResponseEntity.accepted().body(employeeService.getEmployeesWithHighSalary());
    }

}
