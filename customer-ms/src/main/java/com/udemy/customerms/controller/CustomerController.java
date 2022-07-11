package com.udemy.customerms.controller;

import com.udemy.customerms.domain.Customer;
import com.udemy.customerms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public String status(){
        return "Customer-MS OK";
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customer));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Customer> findByCPF(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findByCPF(cpf));
    }
}