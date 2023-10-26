package com.example.crudproject.service;

import com.example.crudproject.dto.EmployeeRequest;
import com.example.crudproject.dto.EmployeeResponse;
import com.example.crudproject.model.Employee;
import com.example.crudproject.repository.EmployeeRepository;
import com.example.crudproject.validation.ExtensionValidator;
import lombok.experimental.ExtensionMethod;
import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ExtensionMethod(ExtensionValidator.class)
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
//    public List<Employee> findAllEmployee() {
//        return employeeRepository.findAll();
//    }

    public List<EmployeeResponse> findAllEmployee() {   ///List<Employee>
//        return employeeRepository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
        List<Employee> employeeList =  employeeRepository.findAll();
        List<EmployeeResponse> responseList = new ArrayList<>();
        for(int i=0; i<employeeList.size(); i++){
            responseList.add(convertToDTO(employeeList.get(i)));
        }

        return responseList;
//        return employeeList;
    }

    @Override
    public Optional<EmployeeResponse> findById(Long id) {
        if(!employeeRepository.existsById(id))
        {
            throw new IllegalStateException("employee with id " + id + " does not exist");
        }
        return employeeRepository.findById(id)
                .map(this::convertToDTO);
                //.orElseThrow(() -> new ConfigDataResourceNotFoundException("Id [%s] not found".id)
                //);
    }

//    private EmployeeResponse convertToDTO(Employee employee) {
//        EmployeeResponse employeeResponse = new EmployeeResponse();
//
//        employeeResponse.setId(employee.getId());
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setDesignation(employee.getDesignation());
//        employeeResponse.setEmail(employee.getEmail());
//        employeeResponse.setMobileno(employee.getMobileno());
//        employeeResponse.setNid(employee.getNid());
//
//        return employeeResponse;
//    }

    private EmployeeResponse convertToDTO(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }

    @Override
    public ResponseEntity<?> saveEmployee(EmployeeRequest employeeRequest) {

        String mobileNumber = employeeRequest.getMobileno();
//        if (mobileNumber != null && mobileNumber.length() == 11) {
//            // Mobile number is already 11 digits, no modification needed
//        }
        ///else if (mobileNumber != null && mobileNumber.matches("\\d{11}")) {
        // Mobile number is not 11 digits but consists of 11 digits, add "+88"
        //    employeeRequest.setMobileno("+88" + mobileNumber);
        //}
//        else {
//            // Invalid mobile number, handle the error or validation as needed
//            throw new IllegalArgumentException("Invalid mobile number");
//        }
        if (mobileNumber.isNullOrEmptyOrNotEl()) {
            // Mobile number is already 11 digits, no modification needed
            throw new IllegalArgumentException("Invalid mobile number");
        }


        int nidLength = String.valueOf(employeeRequest.getNid()).length();
        String nid = String.valueOf(employeeRequest.getNid());

//        if (nidLength != 0 && (nidLength == 10 || nidLength == 15 || nidLength == 17)) {
//            // NID number length is valid
//        } else {
//            // Invalid NID number, handle the error or validation as needed
//            throw new IllegalArgumentException("Invalid NID number");
//        }

        if (nid.isNullOrEmptyOrNotCorrect()) {
//            // NID number length is valid
            throw new IllegalArgumentException("Invalid NID number");
        }

//        Employee employee = new Employee();
//        employee.setName(employeeRequest.getName());
//        employee.setDesignation(employeeRequest.getDesignation());
//        employee.setEmail(employeeRequest.getEmail());
//        employee.setMobileno(employeeRequest.getMobileno());
//        employee.setNid(employeeRequest.getNid());

//        Employee employee = Employee.build(0L, employeeRequest.getName(), employeeRequest.getDesignation(),
//                employeeRequest.getEmail(), employeeRequest.getMobileno(), employeeRequest.getNid());

//        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getDesignation(),
//                employeeRequest.getEmail(), employeeRequest.getMobileno(), employeeRequest.getNid());

        Employee employee = new Employee();
        employee = modelMapper.map(employeeRequest, Employee.class);

        //Optional<Employee> employeeOptional = EmployeeRepository
        //        .findEmployeeByEmail(employeeRequest.getEmail());

        //if(employeeOptional.isPresent()) {
        //    throw new IllegalStateException("email already taken");
        //}
        Employee employee1 = employeeRepository.save(employee);
        return new ResponseEntity<> (employee1, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateEmployee(EmployeeRequest employeeRequest) {  //Employee
        Employee employee = employeeRepository.findById(employeeRequest.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + employeeRequest.getId() + " does not exist")
                );

//        employee.setId(employeeRequest.getId());
        employee.setName(employeeRequest.getName());
        employee.setDesignation(employeeRequest.getDesignation());
        employee.setEmail(employeeRequest.getEmail());
        employee.setMobileno(employeeRequest.getMobileno());
        employee.setNid(employeeRequest.getNid());
        Employee employee1 = employeeRepository.save(employee);
        return new ResponseEntity<> (employee1, HttpStatus.OK);
    }

    @Override
    public void deleteEmployee(Long id) {
        boolean exists = employeeRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("employee with id " + id + " does not exist");
        }
        employeeRepository.deleteById(id);
    }
}
