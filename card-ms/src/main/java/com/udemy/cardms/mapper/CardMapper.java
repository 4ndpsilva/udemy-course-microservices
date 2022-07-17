package com.udemy.cardms.mapper;

import com.udemy.cardms.domain.dto.CardRequestDTO;
import com.udemy.cardms.domain.dto.CardResponseDTO;
import com.udemy.cardms.domain.dto.CustomerCardResponseDTO;
import com.udemy.cardms.domain.entity.Card;
import com.udemy.cardms.domain.entity.CustomerCard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card map(CardRequestDTO cardRequestDTO);

    CardResponseDTO map(Card card);

    List<CardResponseDTO> map(List<Card> cards);

    List<CustomerCardResponseDTO> mapCustomerCards(List<CustomerCard> customerCards);
}