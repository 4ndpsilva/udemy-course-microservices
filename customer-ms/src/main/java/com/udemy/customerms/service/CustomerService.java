package com.udemy.customerms.service;

import com.udemy.customerms.domain.Customer;
import com.udemy.customerms.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    @Transactional
    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public Optional<Customer> findByCPF(String cpf){
        return repository.findByCpf(cpf);
    }
}