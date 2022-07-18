package com.udemy.creditappraiserms.service;

import com.udemy.creditappraiserms.domain.dto.CustomerCardDTO;
import com.udemy.creditappraiserms.domain.dto.CustomerDataDTO;
import com.udemy.creditappraiserms.domain.dto.CustomerStatusDTO;
import com.udemy.creditappraiserms.exception.CustomerDataNotFoundException;
import com.udemy.creditappraiserms.exception.CommunicationErrorException;
import com.udemy.creditappraiserms.infra.CardResourceClient;
import com.udemy.creditappraiserms.infra.CustomerResourceClient;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {
    private final CustomerResourceClient customerResourceClient;
    private final CardResourceClient cardResourceClient;

    public CustomerStatusDTO findCustomerStatus(String cpf) throws CustomerDataNotFoundException, CommunicationErrorException {
        try{
            ResponseEntity<CustomerDataDTO> customerData = customerResourceClient.findByCPF(cpf);
            ResponseEntity<List<CustomerCardDTO>> customerCard = cardResourceClient.findByCPF(cpf);

            return CustomerStatusDTO
                    .builder()
                    .customerData(customerData.getBody())
                    .cards(customerCard.getBody())
                    .build();
        }
        catch (FeignException.FeignClientException ex){
            int status = ex.status();

            if(status == HttpStatus.NOT_FOUND.value()){
                throw new CustomerDataNotFoundException(cpf);
            }

            throw new CommunicationErrorException(ex.getMessage(), status);
        }
        catch (RetryableException ex){
            int status = ex.status();
            throw new CommunicationErrorException(ex.getMessage(), status);
        }
    }
}