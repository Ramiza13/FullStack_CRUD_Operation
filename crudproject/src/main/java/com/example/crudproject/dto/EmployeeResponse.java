package com.example.crudproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponse {
    public Long id;
    public String name;
    public String designation;
    public  String email;
    public String mobileno;
    public Long nid;
}
