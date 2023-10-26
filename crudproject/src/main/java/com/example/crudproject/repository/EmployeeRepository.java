package com.example.crudproject.repository;

import com.example.crudproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //@Query("SELECT e FROM Employee e WHERE e.email=?1")
    Optional<Employee> findEmployeeByEmail(String email);
}
