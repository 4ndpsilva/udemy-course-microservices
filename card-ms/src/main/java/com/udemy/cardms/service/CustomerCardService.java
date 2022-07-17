package com.udemy.cardms.service;

import com.udemy.cardms.domain.entity.CustomerCard;
import com.udemy.cardms.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardService {
    private final CustomerCardRepository repository;

    public List<CustomerCard> findByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}