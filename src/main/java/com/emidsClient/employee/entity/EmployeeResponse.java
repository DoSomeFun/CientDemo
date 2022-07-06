package com.emidsClient.employee.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private List<Employee> employees;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Employee employee : employees){
            stringBuilder.append(
                    String.format("\n EmployeeId: %s | First Name: %s | Last Name: %s | Salary: %s |",
                            employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), employee.getSalary()));
        }
        return stringBuilder.toString();
    }
}
