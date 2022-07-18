package com.udemy.creditappraiserms.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerCardDTO {
    private String name;
    private String flag;
    private BigDecimal freeLimit;
}