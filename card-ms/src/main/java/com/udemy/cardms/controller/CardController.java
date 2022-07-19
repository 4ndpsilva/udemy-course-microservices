package com.udemy.cardms.controller;

import com.udemy.cardms.domain.dto.CardRequestDTO;
import com.udemy.cardms.domain.dto.CardResponseDTO;
import com.udemy.cardms.domain.dto.CustomerCardResponseDTO;
import com.udemy.cardms.mapper.CardMapper;
import com.udemy.cardms.service.CardService;
import com.udemy.cardms.service.CustomerCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService service;
    private final CustomerCardService customerCardService;
    private final CardMapper mapper;

    @PostMapping
    public ResponseEntity<CardResponseDTO> save(@RequestBody CardRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(service.save(mapper.map(requestDTO))));
    }

    @GetMapping("/income/{income}")
    public ResponseEntity<List<CardResponseDTO>> findCardsIncomeLessEqualThat(@PathVariable Long income){
        return ResponseEntity.ok(mapper.map(service.getCardsIncomeLessEqual(income)));
    }

    @GetMapping("/customer-cards/cpf/{cpf}")
    public ResponseEntity<List<CustomerCardResponseDTO>> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(mapper.mapCustomerCards(customerCardService.findByCpf(cpf)));
    }
}
