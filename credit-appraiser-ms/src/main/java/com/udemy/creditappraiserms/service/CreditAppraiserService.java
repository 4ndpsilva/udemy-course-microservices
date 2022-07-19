package com.udemy.creditappraiserms.service;

import com.udemy.creditappraiserms.domain.dto.ApprovedCardDTO;
import com.udemy.creditappraiserms.domain.dto.CardDTO;
import com.udemy.creditappraiserms.domain.dto.CustomerCardDTO;
import com.udemy.creditappraiserms.domain.dto.CustomerDataDTO;
import com.udemy.creditappraiserms.domain.dto.CustomerEvaluationReturnDTO;
import com.udemy.creditappraiserms.domain.dto.CustomerStatusDTO;
import com.udemy.creditappraiserms.exception.CommunicationErrorException;
import com.udemy.creditappraiserms.exception.CustomerDataNotFoundException;
import com.udemy.creditappraiserms.infra.CardResourceClient;
import com.udemy.creditappraiserms.infra.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    }

    public CustomerEvaluationReturnDTO appraise(String cpf, Long income) throws CustomerDataNotFoundException, CommunicationErrorException {
        try{
            ResponseEntity<CustomerDataDTO> customerDataResponse = customerResourceClient.findByCPF(cpf);
            ResponseEntity<List<CardDTO>> cardsResponse = cardResourceClient.findCardsIncomeLessEqualThan(income);

            List<CardDTO> cards = cardsResponse.getBody();
            var cardList = cards.stream().map(c -> {
                CustomerDataDTO customerData = customerDataResponse.getBody();

                BigDecimal basicLimit = c.getBasicLimit();
                BigDecimal age = BigDecimal.valueOf(customerData.getAge());

                var factor = age.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicLimit);

                ApprovedCardDTO approvedCardDTO = new ApprovedCardDTO();
                approvedCardDTO.setCard(c.getName());
                approvedCardDTO.setFlag(c.getFlag());
                approvedCardDTO.setLimit(approvedLimit);

                return approvedCardDTO;
            }).collect(Collectors.toList());

            return new CustomerEvaluationReturnDTO(cardList);
        }
        catch (FeignException.FeignClientException ex){
            int status = ex.status();

            if(status == HttpStatus.NOT_FOUND.value()){
                throw new CustomerDataNotFoundException(cpf);
            }

            throw new CommunicationErrorException(ex.getMessage(), status);
        }
    }
}