package com.example.crudproject.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EmployeeInformation")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Employee {
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    public Long id;

    @Column(name = "name")
    public String name;
    @Column(name = "designation")
    public String designation;
    @Column(name = "email")
    public String email;
    @Column(name = "mobileno")
    public String mobileno;
    @Column(name = "nid")
    @NotNull
    public Long nid;
}
