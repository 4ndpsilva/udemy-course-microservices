package com.udemy.creditappraiserms.infra;

import com.udemy.creditappraiserms.domain.dto.CustomerCardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "card-ms", path = "/api/cards")
public interface CardResourceClient {
    @GetMapping("/customer-cards/cpf/{cpf}")
    ResponseEntity<List<CustomerCardDTO>> findByCPF(@PathVariable String cpf);
}