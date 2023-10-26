package com.example.crudproject.service;

import com.example.crudproject.dto.EmployeeRequest;
import com.example.crudproject.dto.EmployeeResponse;
import com.example.crudproject.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

public interface EmployeeService {
    List<EmployeeResponse> findAllEmployee();  //List<Employee> findAllEmployee();
    Optional<EmployeeResponse> findById(Long id);
    ResponseEntity<?> saveEmployee(EmployeeRequest employeeRequest);
    ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest);

    void deleteEmployee(Long id);
}
