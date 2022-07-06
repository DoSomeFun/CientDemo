package com.emidsClient.employee.service;

import com.emidsClient.employee.entity.Employee;
import com.emidsClient.employee.entity.EmployeeRequest;
import com.emidsClient.employee.entity.EmployeeResponse;
import com.emidsClient.employee.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public ResponseEntity<EmployeeResponse> saveEmployees(EmployeeRequest employeeRequest) {

        employeeRepo.saveAll(employeeRequest.getEmployees());
        EmployeeResponse employeeResponse = EmployeeResponse.builder().
                employees(employeeRequest.getEmployees()).
                build();

        log.info(employeeResponse.toString());

        return ResponseEntity.accepted().body(employeeResponse);
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeesWithHighSalary() {

        List<Employee> employeesAboveHundred = employeeRepo.findBySalaryGreaterThan(100L);

        EmployeeResponse employeeResponse = EmployeeResponse.
                builder().
                employees(employeesAboveHundred).
                build();

        log.info("Employees with salary above 100: " + employeeResponse.toString());

        return ResponseEntity.accepted().body(employeeResponse);
    }
}
