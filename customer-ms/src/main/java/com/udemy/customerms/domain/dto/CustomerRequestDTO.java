package com.udemy.customerms.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {
    private String cpf;
    private String name;
    private int age;
}