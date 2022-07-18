package com.udemy.creditappraiserms.controller;

import com.udemy.creditappraiserms.domain.entity.CustomerStatus;
import com.udemy.creditappraiserms.service.CreditAppraiserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit-appraisers")
@RequiredArgsConstructor
public class CreditAppraiserController {
    private final CreditAppraiserService service;

    @GetMapping("/customer-status/cpf/{cpf}")
    public ResponseEntity<CustomerStatus> findCustomerStatus(@PathVariable String cpf){
        CustomerStatus status = service.findCustomerStatus(cpf);
        return ResponseEntity.ok(status);
    }
}