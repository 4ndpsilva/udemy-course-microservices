package com.udemy.creditappraiserms.infra;

import com.udemy.creditappraiserms.domain.dto.CustomerDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-ms", path = "/api/customers")
public interface CustomerResourceClient {
    @GetMapping("/cpf/{cpf}")
    ResponseEntity<CustomerDataDTO> findByCPF(@PathVariable String cpf);
}