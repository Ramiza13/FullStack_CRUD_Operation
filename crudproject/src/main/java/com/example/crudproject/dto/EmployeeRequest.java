package com.example.crudproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class EmployeeRequest {
    public Long id;
    public String name;
    public String designation;
    public  String email;
    public String mobileno;
    public Long nid;

}
