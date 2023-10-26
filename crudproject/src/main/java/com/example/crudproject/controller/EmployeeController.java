package com.example.crudproject.controller;

import com.example.crudproject.constants.Constants;
import com.example.crudproject.dto.EmployeeRequest;
import com.example.crudproject.dto.EmployeeResponse;
import com.example.crudproject.model.Employee;
import com.example.crudproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping(path = Constants.home)  // /employee
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = Constants.getData)
    public List<EmployeeResponse> findAllEmployee() {  ///List<Employee>
        return employeeService.findAllEmployee();
    }

    @GetMapping(path = Constants.getData + "/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponse employee = employeeService.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + id + " does not exist"));
        return ResponseEntity.ok(employee);
    }

    @PostMapping(path = Constants.postData)
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        return employeeService.saveEmployee(employeeRequest);

//        return "User " + employeeRequest.getName() + " added successfully";
    }
//    public Employee saveEmployee(@RequestBody EmployeeRequest employeeRequest)
//    {
//        return employeeService.saveEmployee(employeeRequest);
//    }



    @PutMapping(path = Constants.putData)
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployee(employeeRequest);
//        return "User with id " + employeeRequest.getId() + " updated successfully";
    }
//    public void updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
//        employeeService.updateEmployee(employeeRequest);
//    }


    @DeleteMapping(path = Constants.deleteData + "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    ///@GetMapping(Constants.FIND_ALL_USER)
    ///public List<> findAllUser() {
    ///    // Call the service to fetch PracticeUser entities and convert them to DTOs
    ///    List<> responseDTOs = convertToResponseDTOs(practiceUserService.findAllUser());
    ///    return responseDTOs;
    ///}
}
