package controller;

import com.emidsClient.employee.controller.EmployeeController;
import com.emidsClient.employee.entity.Employee;
import com.emidsClient.employee.entity.EmployeeRequest;
import com.emidsClient.employee.entity.EmployeeResponse;
import com.emidsClient.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    private EmployeeController employeeController;

    @BeforeEach
    public void setup(){
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    public void saveEmployeesTest(){
        EmployeeRequest request = new EmployeeRequest();

        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder().employeeId(1L).firstName("anna").lastName("marie").salary(110L).build());
        employees.add(Employee.builder().employeeId(2L).firstName("peter").lastName("johns").salary(90L).build());
        employees.add(Employee.builder().employeeId(3L).firstName("tim").lastName("lewis").salary(100L).build());
        employees.add(Employee.builder().employeeId(4L).firstName("stephen").lastName("hawkings").salary(130L).build());

        request.setEmployees(employees);

        EmployeeResponse expectedResponse = EmployeeResponse.builder().employees(employees).build();

        when(employeeService.saveEmployees(request)).
                thenReturn(expectedResponse);

        ResponseEntity<EmployeeResponse> actualResponse = employeeController.saveEmployeesResource(request);

        assertThat(actualResponse.getBody(), is(expectedResponse));
    }


    @Test
    public void getEmployeeTest(){

        EmployeeRequest request = new EmployeeRequest();

        List<Employee> employees = new ArrayList<>();

        employees.add(Employee.builder().employeeId(1L).firstName("anna").lastName("marie").salary(110L).build());
        employees.add(Employee.builder().employeeId(4L).firstName("stephen").lastName("hawkings").salary(130L).build());

        EmployeeResponse expectedResponse = EmployeeResponse.builder().employees(employees).build();

        when(employeeService.getEmployeesWithHighSalary()).
                thenReturn(expectedResponse);

        ResponseEntity<EmployeeResponse> actualResponse = employeeController.getEmployeesWithHighSalaryResource();

        assertThat(actualResponse.getBody(),is(expectedResponse));

    }


}
