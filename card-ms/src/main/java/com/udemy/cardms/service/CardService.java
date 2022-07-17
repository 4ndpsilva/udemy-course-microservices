package com.udemy.cardms.service;

import com.udemy.cardms.domain.entity.Card;
import com.udemy.cardms.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;

    @Transactional
    public Card save(Card card){
        return repository.save(card);
    }

    public List<Card> getCardsIncomeLessEqual(Long income){
        return repository.findByIncomeLessThanEqual(new BigDecimal(income));
    }
}