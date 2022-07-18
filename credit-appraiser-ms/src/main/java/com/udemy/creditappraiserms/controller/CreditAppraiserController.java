package com.udemy.creditappraiserms.controller;

import com.udemy.creditappraiserms.domain.dto.CustomerStatusDTO;
import com.udemy.creditappraiserms.exception.CustomerDataNotFoundException;
import com.udemy.creditappraiserms.exception.CommunicationErrorException;
import com.udemy.creditappraiserms.service.CreditAppraiserService;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity findCustomerStatus(@PathVariable String cpf){
        try {
            CustomerStatusDTO status = service.findCustomerStatus(cpf);
            return ResponseEntity.ok(status);
        }
        catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (CommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
        catch (RetryableException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.status())).body(e.getMessage());
        }
    }
}