package com.emidsClient.employee.service;

import com.emidsClient.employee.entity.Employee;
import com.emidsClient.employee.entity.EmployeeRequest;
import com.emidsClient.employee.entity.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    ResponseEntity<EmployeeResponse> saveEmployees(EmployeeRequest employeeRequest);

    ResponseEntity<EmployeeResponse> getEmployeesWithHighSalary();
}
