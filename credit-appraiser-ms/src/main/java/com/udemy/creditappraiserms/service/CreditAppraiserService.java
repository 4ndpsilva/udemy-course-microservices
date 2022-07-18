package com.udemy.creditappraiserms.service;

import com.udemy.creditappraiserms.domain.entity.CustomerData;
import com.udemy.creditappraiserms.domain.entity.CustomerStatus;
import com.udemy.creditappraiserms.infra.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {
    private final CustomerResourceClient customerResourceClient;
    public CustomerStatus findCustomerStatus(String cpf){
        ResponseEntity<CustomerData> customerData = customerResourceClient.findByCPF(cpf);

        return CustomerStatus
                .builder()
                .customerData(customerData.getBody())
                .build();
    }
}