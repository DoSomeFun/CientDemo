package service;

import com.emidsClient.employee.entity.Employee;
import com.emidsClient.employee.entity.EmployeeRequest;
import com.emidsClient.employee.entity.EmployeeResponse;
import com.emidsClient.employee.repository.EmployeeRepo;
import com.emidsClient.employee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup(){
        employeeRepo = mock(EmployeeRepo.class);
        employeeService = new EmployeeServiceImpl(employeeRepo);
    }

    @Test
    public void testGetEmployeesWithHighSalary(){
        List<Employee> employeesResult = new ArrayList<>();
        employeesResult.add(Employee.builder().employeeId(1L).firstName("anna").lastName("marie").salary(110L).build());
        employeesResult.add(Employee.builder().employeeId(4L).firstName("stephen").lastName("hawkings").salary(130L).build());

        EmployeeResponse expectedResponse = EmployeeResponse.builder().employees(employeesResult).build();

        when(employeeRepo.findBySalaryGreaterThan(100L)).thenReturn(employeesResult);

        EmployeeResponse response = employeeService.getEmployeesWithHighSalary();

        assertThat(response, is(notNullValue()));
        assertThat(response, is(expectedResponse));
    }

    @Test
    public void testSaveEmployee(){
        EmployeeRequest request = new EmployeeRequest();
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder().employeeId(1L).firstName("anna").lastName("marie").salary(110L).build());
        employees.add(Employee.builder().employeeId(2L).firstName("peter").lastName("johns").salary(90L).build());
        employees.add(Employee.builder().employeeId(3L).firstName("tim").lastName("lewis").salary(100L).build());
        employees.add(Employee.builder().employeeId(4L).firstName("stephen").lastName("hawkings").salary(130L).build());

        request.setEmployees(employees);
        EmployeeResponse expectedResponse = EmployeeResponse.builder().employees(employees).build();

        when(employeeRepo.saveAll(request.getEmployees())).thenReturn(employees);

        EmployeeResponse actualResponse = employeeService.saveEmployees(request);

        assertThat(actualResponse, is(notNullValue()));
        assertThat(actualResponse, is(expectedResponse));


    }
}
