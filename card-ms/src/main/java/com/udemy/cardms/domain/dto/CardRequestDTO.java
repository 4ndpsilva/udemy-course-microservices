package com.udemy.cardms.domain.dto;

import com.udemy.cardms.domain.entity.Flag;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardRequestDTO {
    private String name;
    private Flag flag;
    private BigDecimal income;
    private BigDecimal basicLimit;
}