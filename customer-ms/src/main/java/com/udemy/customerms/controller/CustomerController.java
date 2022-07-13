package com.udemy.customerms.controller;

import com.udemy.customerms.domain.Customer;
import com.udemy.customerms.domain.dto.CustomerRequestDTO;
import com.udemy.customerms.domain.dto.CustomerResponseDTO;
import com.udemy.customerms.mapper.CustomerMapper;
import com.udemy.customerms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    private final CustomerMapper mapper;

    @GetMapping
    public String status(HttpServletRequest request){
        log.info("#### MICROSERVICES ONLINE: {}", request.getRemoteHost());
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody CustomerRequestDTO requestDTO){
        final CustomerResponseDTO responseDTO = mapper.toDTO(service.save(mapper.map(requestDTO)));
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/cpf/{cpf}")
                .buildAndExpand(responseDTO.getCpf())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Customer> findByCPF(@PathVariable String cpf){
        Optional<Customer> opCustomer = service.findByCPF(cpf);

        if(opCustomer.isPresent()){
            return ResponseEntity.ok(opCustomer.get());
        }

        return ResponseEntity.notFound().build();
    }
}